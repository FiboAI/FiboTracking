<!-- 漏斗分析 -->
<template>
  <div class="event-container">
    <analysis-header :title="page_title" @on-save="saveAnalysisModal" :set="!!modelId" />
    <div class="event-content">
      <div>
        <div class="event-settings  modular">
          <div class="showFunnel">
            <div style="display: flex;align-items: center;">
              当前漏斗
              <a-select :style="{ width: '320px', marginLeft: '10px' }" v-model="currentFunnelId" :options="funnelList"
                :field-names="{ value: 'id', label: 'name' }" placeholder="请选择漏斗"
                :virtual-list-props="{ height: 200 }" />
              <a-button type="text" @click="updateFunnel" style="margin-left:10px;">
                <template #icon>
                  <icon-edit />
                </template>
              </a-button>
              <a-button type="text" @click="deleteFunnel" style="margin-left:10px">
                <template #icon>
                  <icon-delete />
                </template>
              </a-button>
            </div>
            <div>
              <a-button type="text" style="width: 100px" long @click="addConditionFunc" :disabled="!!modelId">
                <icon-plus />
                {{ modelId ? '更新模式下不能添加漏斗' : '添加漏斗' }}
              </a-button>

            </div>
          </div>
          <a-divider />
          <div>
            <template v-for="(item, index) in global_screens" :key="`${index}`">
              <event-global-screen v-model:data="global_screens[index]" :index="index" @on-delete="delGlobalScreenFunc"
                ref="eventGlobalScreenRef" />
            </template>
            <div style="display: flex;position: relative;">
              <a-button type="text" style="width: 100px;" long @click="addGlobalScreenFunc">
                <icon-plus />
                全局筛选
              </a-button>
              <a-button type="primary" style="position: absolute;right: 0;width: 60px;margin-left: 10px;" long
                @click="searchGlobal_screens">
                搜索
              </a-button>
            </div>

          </div>
        </div>
        <div class="event-settings modular">
          <funnelChart v-model:currentStep="currentStep" :loading="data_loading" :data_list="chartTabledata"
            v-model:startTime="timeWindow[0]" :option="option" :currentFunnel="currentFunnel"
            :conversionList="conversionList" v-model:endTime="timeWindow[1]"></funnelChart>
        </div>
        <div class="event-settings modular">
          <a-table :columns="columns" :data="chartTabledata">
            <template #total="{ record, column }">
              <span>{{record[column.dataIndex]}}%</span>
            </template>
            <template #optional="{ record, column }">
              <span>{{record[column.dataIndex]}}<br/>
                <span class="rateFontColor" v-if="record['rate'+column.dataIndex]">{{record['rate'+column.dataIndex]}}%</span>
              </span>
            </template>
          </a-table>
<!--          <data-table :data-list="chartTabledata" :columns="columns"></data-table>-->
        </div>
      </div>
    </div>
  </div>

  <funnelDrawer v-model:visible="showDrawer" :data="TempDrawerDate" @reset="setTempDrawerDateDefault"
    @addOver="getFunnelList" v-model:isUpdate="isUpdate"></funnelDrawer>

  <save-funnel-analysis ref="save_funnel_analysis" v-model:visible="save_visible" :width="600" :modelId="modelId" />
</template>

<script lang="ts" setup>
import type { AddfunnelData, FunnelList, CalculateData, CalculateDataResponseAddTotil } from "_types/analysis";
import { getFunnelListData, getCalculateData, deleteFunnelUrl } from "_apis/analysis";
import { getModelListByOverviewId } from "_apis/overview";
import useDate from "_hooks/useDate";
import ComputedChart from "_workers/computed-funnel-chart?worker";
// import type { SelectOptionData, SelectFieldNames } from "@arco-design/web-vue";
import type {
  WherePropertyParams,
} from "_types/analysis"
import useMessage from "_hooks/useMessage";
import { Modal } from '@arco-design/web-vue';
import { number } from "echarts/core";
import { Ref } from 'Vue'
import type { TableColumnData } from "@arco-design/web-vue"
const { MessageError, MessageSuccess } = useMessage();


const route = useRoute()
let isUpdate = ref<boolean>(false);


// 页面
const page_title: string = "漏斗分析";
// 当前漏斗
let currentFunnelId = ref(0);
let currentFunnel = reactive<AddfunnelData>({
  name: '',
  windowPeriod: NaN,
  funnelStepDataEntityList: []
})

watch(currentFunnelId, (e) => {
  Object.assign(currentFunnel, funnelList.find(x => x.id == currentFunnelId.value))
})

// ==========获取所有漏斗
var funnelList = reactive<FunnelList>([]);
const getFunnelList = () => {
  getFunnelListData().then((res) => {
    funnelList.length = 0
    funnelList.push(...(<FunnelList>res.datas));
    // if (!route.query.model) {
    //   currentFunnelId.value = funnelList[0].id || 0
    // } else if (currentFunnelId.value) {
    //   Object.assign(currentFunnel, funnelList.find(x => x.id == currentFunnelId.value))
    // }

    if (currentFunnelId.value) {
      Object.assign(currentFunnel, funnelList.find(x => x.id == currentFunnelId.value))
    } else if (!route.query.model) {
      currentFunnelId.value = funnelList[0].id || 0
    }
    if (isUpdate.value == true) {
      currentFunnelId.value = funnelList[funnelList.length - 1].id || 0
      isUpdate.value = false
    }

  });
}

getFunnelList()

// ==========创建相关
// 抽屉显示
var showDrawer = ref<boolean>(false);

// 点击添加事件
const addConditionFunc = (): void => {
  showDrawer.value = true;
};
// 点击更新按钮按钮
const updateFunnel = () => {
  showDrawer.value = true;

  Object.assign(
    TempDrawerDate,
    JSON.parse(
      JSON.stringify(funnelList.find((x) => x.id == currentFunnelId.value))
    )
  );

};

var TempDrawerDate = reactive<AddfunnelData>({
  name: "",
  windowPeriod: 7,
  funnelStepDataEntityList: [{ eventEn: "visit_xn", name: "全站流量", isVirtual: 1 }],
});

const setTempDrawerDateDefault = (): void => {
  Object.assign(TempDrawerDate, {
    name: "",
    windowPeriod: 7,
    funnelStepDataEntityList: [{ eventEn: "visit_xn", name: "全站流量" }],
  });
  TempDrawerDate.id = 0;
};
// 全局筛选
let global_screens = reactive<Array<WherePropertyParams>>([]);
// 删除全局筛选
const delGlobalScreenFunc = (index: number): void => {
  global_screens.splice(index, 1)
}
// 添加全局筛选
const addGlobalScreenFunc = (): void => {
  global_screens.push({
    propertyEn: "",
    fieldType: "",
    conditions: "",
    value: '',
  })
}

// 时间窗口

const { getTime, getAddTime } = useDate();
var timeWindow = reactive([getAddTime(-7), getAddTime(-1)])


// ==================================更新漏斗===================
var modelId: Ref<number | null> = ref(null)
var overViewId: Ref<number | null> = ref(null)
var oldName: Ref<string | null> = ref(null)
if (route.query.model) {
  modelId.value = Number(route.query.model)
  getModelListByOverviewId({
    modelId: Number(route.query.model)
  }).then(res => {
    if (res.datas) {
      let { datas } = res
      global_screens.length = 0
      oldName.value = datas.name
      global_screens.push(...(datas.wherePropertyParams || []))
      overViewId.value = datas.overviewId
      timeWindow[0] = datas.startTime
      timeWindow[1] = datas.endTime
      currentFunnelId.value = datas.funnelId || 0
    }
  })
}

// 监听改变事件切换表格数据
let chartData: CalculateDataResponseAddTotil
watch([timeWindow, currentFunnelId], () => {
  getChrteDate()
})
const searchGlobal_screens = () => {
  getChrteDate()
}
var chartTabledata = reactive<Array<any>>([])
// 全局筛选的ref
const eventGlobalScreenRef = ref()

var columns: Ref<Array<TableColumnData>> = ref([])
const getChrteDate = () => {

  var GlobalScreenVerification = eventGlobalScreenRef.value && eventGlobalScreenRef.value.find((value: { verification: Function }) => {
    return value.verification()
  })
  if (GlobalScreenVerification) {
    MessageError('全局筛选中有未填项')
    return
  }

  data_loading.value = true
  getCalculateData({
    type: 1,            //分析模型类型
    timeGranularity: 'day', //事件粒度
    startTime: timeWindow[0],       //开始事件
    endTime: timeWindow[1],      //结束时间
    wherePropertyParams: global_screens,
    funnelId: currentFunnelId.value
  }).then(res => {
    if (res.datas) {
      currentStep.value = 0
      computedChartDataFunc(res)
      chartData = res
      chartTabledata.length = 0
      chartTabledata.push(...res.datas)
      // chartTabledata.forEach((item,index)=>{
      //   for(let k = 0;k<(currentFunnel.funnelStepDataEntityList as any).length;k++){
      //       item["step"+(k+2)] = item["step"+(k+2)] + "("+item["ratestep"+(k+2)]+"%)";
      //   }
      //   //总体，最后一步除以第一步
      //   item.total =item.total+"%";
      // })
      // columns.length=0
      columns.value = getColumns(res.datas)
    }
    data_loading.value = false
  })
}

function getColumns(arr: CalculateData[]): Array<TableColumnData> {
  let subarr: Array<TableColumnData> = [{
    title: '日期',
    dataIndex: 'dates',
    fixed: 'left',
    sortable: {
      sortDirections: ['ascend', 'descend']
    }
  },{
    title: '总体',
    dataIndex: 'total',
    slotName:"total",
    align:"center"
  }]

  if(currentFunnel.funnelStepDataEntityList){
    currentFunnel.funnelStepDataEntityList.forEach((item,index) =>{
      if(index>=1){
        subarr.push({
          title: item.name+"(第"+(index)+"步转化)",
          dataIndex: 'step' + (index+1),
          slotName:"optional",
          align:"center"
        })
      }else{
        subarr.push({
          title: item.name,
          dataIndex: 'step' + (index+1),
          slotName:"optional",
          align:"center"
        })
      }

    })
  }

  //
  // for (let i = 1; i < Object.keys(arr[0]).length; i++) {
  //
  //
  // }

  return subarr

}

var conversionList = reactive<Array<{ num: number, scale: number }>>([])

var option = reactive({})
var currentStep = ref(0)

watch(currentStep, () => {
  computedChartDataFunc(chartData)
})

const computedChartDataFunc = (res: CalculateDataResponseAddTotil) => {
  const computed_line_data = new ComputedChart();
  computed_line_data.onmessage = (event) => {
    let { conversion, serirs } = event.data;
    conversionList.length = 0
    conversionList.push(...conversion)
    Object.assign(option, {
      legend: {
        top: "10px",
        // data: legend
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: res.times
      },
      yAxis: {
        type: 'value'
      },
      grid: [
        {
          left: '50px',
          top: '50px',
          bottom: '30px',
          right: "20px"
        }
      ],
      series: serirs
    })
    // chart.value.init(option)
  }
  computed_line_data.postMessage({ datas: res.datas, total: res.total, type: currentStep.value });



}
// 表格loading
let data_loading = ref<boolean>(false)
// =================保存逻辑=================
var save_visible = ref(false)
const save_funnel_analysis = ref()
const saveAnalysisModal = () => {

  var GlobalScreenVerification = eventGlobalScreenRef.value && eventGlobalScreenRef.value.find((value: { verification: Function }) => {
    return value.verification()
  })
  if (GlobalScreenVerification) {
    MessageError('全局筛选中有未填项')
    return
  }
  let obj = {
    type: 1,            //分析模型类型
    timeGranularity: 'day', //事件粒度
    startTime: timeWindow[0],       //开始事件
    endTime: timeWindow[1],      //结束时间
    wherePropertyParams: global_screens,
    funnelId: currentFunnelId.value,
    overviewId: NaN,
    name: ''
  }

  if (overViewId.value) {
    obj.overviewId = overViewId.value
  } else {
    obj.overviewId = Number(route.query.oi) || -1;
  }
  if (oldName.value) {
    obj.name = oldName.value
  }

  save_funnel_analysis.value.init(obj)
  save_visible.value = true
}

// =======================删除漏斗===========================

const deleteFunnel = () => {
  Modal.error({
    title: '确认删除',
    content: '确定删除当前选中漏斗？',
    closable: true,
    okText: '删除',
    cancelText: '取消',
    hideCancel: false,
    onOk: () => {
      deleteFunnelUrl({
        funnelId: currentFunnelId.value
      }).then(res => {
        // getFunnelList()
        MessageSuccess('删除成功')
        history.go(0)
      })
    }
  })
}


</script>

<style lang="less" scoped>
.modular {
  background-color: #fff;
  border-radius: 6px;
  box-shadow: 0 2px 4px @shadow-color-1;
  box-sizing: border-box;
  padding: 10px;
  margin-bottom: 16px;
  min-height: 140px;
  width: 100%;

  &:last-child {
    margin-bottom: 0;
  }
}

.showFunnel {
  display: flex;
  justify-content: space-between;
}

.event-settings {
  &>div {
    &.condition-item {
      height: 50px;
      background-color: green;
      margin-top: 10px;

      &:first-child {
        margin-top: 0;
      }
    }
  }
}

.event-container {
  height: 100%;
  box-sizing: border-box;
  padding: 16px 0 16px 16px;
  overflow: hidden;
  // min-width: 1220px;

  &>.event-content {
    height: calc(100% - 62px);
    width: 100%;
    overflow: hidden;

    &>div {
      height: 100%;
      width: 100%;
      box-sizing: border-box;
      padding-right: 16px;
      overflow-y: auto;
      overflow-x: hidden;
    }
  }
}
</style>
