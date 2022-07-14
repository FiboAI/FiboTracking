<!-- 自定义查询 -->
<template>
  <div class="strstegies-container">
    <analysis-header :title="page_title" @on-save="saveAnalysisModal" :set="!!modelId" />
    <div class="strstegies-content">
      <div class="strstegies-top">
        <a-textarea style="min-height: 100px;margin-right: 20px;" allow-clear v-model="sql_value"
          placeholder="示例：select distinct_id,type from events e2 limit 0,10,为保证查询效率，建议使用date字段进行过滤，并避免使用SELECT * " />
        <a-button type="primary" @click="clickSql">点击执行</a-button>
      </div>
      <!-- <div class="strstegies-table" v-if="sql_table_data.length != 0"> -->
      <div class="strstegies-table" v-if="isData == true">
        <range-picker @change="rangePickerChangeFunc" :model-value="[queries_date[0], queries_date[1]]"
          :style="{ width: '300px', marginBottom: '10px', marginRight: '10px' }" />
        <a-button type="primary" @click="exportTable" style="margin-bottom:20px;">导出</a-button>
        <a-table style="margin-bottom: 20px;" :columns="columns" :data="sql_table_data" :scroll="scroll"
          :loading="loading" />
      </div>
    </div>
  </div>
  <save-queries ref="save_event_analysis" v-model:visible="save_visible" :width="600" :modelId="modelId" />
</template>

<script lang='ts' setup>
import { Ref } from 'Vue';
import useDate from "_hooks/useDate";
import useMessage from "_hooks/useMessage";
import { getModelListByOverviewId } from "_apis/overview";
import type {
  CalculateData,
  QueriesDataRequest,
  MetadataCustomsqlData
} from "_types/analysis";
import type { TableColumnData } from "@arco-design/web-vue";
import {
  getSqlData,
  exportData
} from "_apis/analysis";
import { isDeclareTypeAlias } from '@babel/types';

const route = useRoute();
const router = useRouter();
const { MessageError } = useMessage();
const { getAddTime } = useDate();
// 公共数据 select distinct_id,type from events e2 limit 0,10
let sql_value = ref('select distinct_id,type from events e2 limit 0,10'); // 默认空值
let sql_key = ref();
const page_title: string = "自定义查询";
var modelId: Ref<number | null> = ref(null);
var save_visible = ref(false);
let loading = ref(false);
let isData = ref(false);
let queries_date = reactive<string[]>([getAddTime(-7), getAddTime(-1)]);
var columns: Ref<Array<TableColumnData>> = ref([])
let sql_table_data = reactive<CalculateData[]>([]);
let sql_list = reactive<MetadataCustomsqlData>({
  querySql: ''
});
const scroll = {
  // x: 2000,
  y: 450
};
// 保存/更新 到概览
const save_event_analysis = ref();
var overViewId: Ref<number | null> = ref(null);
var oldName: Ref<string | null> = ref(null);
if (route.query.model) {
  console.log(route.query.model);
  modelId.value = Number(route.query.model)
  getModelListByOverviewId({
    modelId: Number(route.query.model)
  }).then((res: any) => {
    if (res.datas) {
      console.log(res.datas);
      let { datas } = res
      sql_value.value = datas.metadataCustomsqlData.querySql
      queries_date[0] = datas.startTime
      queries_date[1] = datas.endTime
      overViewId.value = datas.overviewId
      oldName.value = datas.name
      clickSql()
    }
  })
}
const saveAnalysisModal = () => {
  sql_list.querySql = sql_value.value
  let obj = {
    type: 3,
    startTime: queries_date[0],
    endTime: queries_date[1],
    overviewId: NaN,
    metadataCustomsqlData: sql_list,
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
  if (sql_table_data.length > 0) {
    save_event_analysis.value.init(obj)
    save_visible.value = true
  }
}
// 执行sql语句
const clickSql = () => {
  loading.value = true;
  isData.value = false;
  sql_list.querySql = sql_value.value;
  if (sql_value.value == '') {
    MessageError('请输入sql语句');
  } else {
    let params: QueriesDataRequest = {
      type: 3,
      startTime: queries_date[0],
      endTime: queries_date[1],
      metadataCustomsqlData: sql_list
    };
    sql_table_data.length = 0;
    getSqlData(params).then((res: any) => {
      // console.log(res.datas);
      loading.value = false;
      isData.value = true;
      res.datas.columns.forEach((item: any) => {
        console.log(item);
        item.sortable = { sortDirections: ['ascend', 'descend'] };
      })
      columns.value = res.datas.columns;
      console.log(res.datas.columns);

      sql_table_data.push(...res.datas.data);
    }).catch(err => {
      MessageError(err);
    })
  }

}
// 导出
const exportTable = () => {
  sql_list.querySql = sql_value.value
  let params: QueriesDataRequest = {
    type: 3,
    startTime: queries_date[0],
    endTime: queries_date[1],
    metadataCustomsqlData: sql_list
  };
  exportData(params).then((res: any) => {
    // console.log(res.datas);
    sql_key.value = res.datas
    console.log(sql_key.value);
    let url = 'http://192.168.50.131:8080/cdp-project/exportData/exportData?exportid=' + sql_key.value;
    window.location.href = url
  }).catch(err => {
    MessageError(err);
  })
}
// 修改日期
const rangePickerChangeFunc = (value: unknown): void => {
  queries_date.length = 0
  queries_date.push(...value as string);
  clickSql()
}
</script>

<style lang='less' scoped>
.header {
  font-size: 22px;
  font-weight: bold;
  flex: auto;
  margin-bottom: 10px;
}

.strstegies-container {
  height: 100%;
  box-sizing: border-box;
  padding: 16px 0 16px 16px;
  // overflow: hidden;

  &>.strstegies-content {
    height: calc(100% - 62px);
    width: 100%;
    overflow: scroll;

    &>.strstegies-top {
      width: 67%;
      margin: 0 10px;
      display: flex;
      align-items: center;
    }

    &>.strstegies-table {
      margin: 20px 0 0 10px;
      width: 98%;
      // height: 600px;
      // overflow: scroll;
    }
  }
}
</style>