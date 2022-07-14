<!-- 折线图 -->
<template>
  <widget-container v-bind="$attrs" :widget-id="overviewId" :loading="data_loading" size="normal" :startTime="startTime"
    :endTime="endTime">
    <div class="widget-content" data-size="normal">
      <m-table :loading="chart_loading" :data-list="table" :columns="columns"></m-table>
      <!-- <m-table :loading="chart_loading" ref="table"></m-table> -->
    </div>
  </widget-container>
</template>

<script lang='ts' setup>
import useDate from "_hooks/useDate";
import { getCalculateDataByModelId } from "_apis/overview";
import type { GetCalculateDataByModelId, OverviewDetailsResponseData } from "_types/overview";
import type { CalculateData } from "_types/analysis";
import type { TableColumnData } from "@arco-design/web-vue"
const { parseTime } = useDate()
const props = defineProps<{
  overviewId: number | string
  startTime: string
  endTime: string
  data: OverviewDetailsResponseData
}>();
// let table = ref()
let table = reactive<CalculateData[]>([]);
let columns = reactive<TableColumnData[]>([]);
let chart_loading = ref<boolean>(false)
let data_loading = ref<boolean>(false)
// const computedChartDataFunc = (datas: CalculateData[]) => {
//   const computed_line_data = new ComputedChart();
//   computed_line_data.onmessage = (event) => {
//     chart_loading.value = false
//     let { Xdata, serirs } = event.data;
//     let option = {
//       legend: {
//         top: "10px",
//       },
//       tooltip: {
//         trigger: 'axis',
//
//         valueFormatter: (value: number) => value + '%',
//       },
//       xAxis: {
//         type: 'category',
//         data: Xdata
//       },
//       yAxis: {
//         type: 'value',
//         axisLabel: {
//           margin: 3,
//           formatter: function (value: number) {
//
//             return value + '%'
//           }
//         },
//       },
//       grid: [
//         {
//           left: '50px',
//           top: '50px',
//           bottom: '30px',
//           right: "20px"
//         }
//       ],
//       series: serirs
//     };
//     // chart.value.init(option)
//   }
//   computed_line_data.postMessage(datas);
// }

// 获取当前模块对应的数据
const getDataListFunc = () => {
  let params: GetCalculateDataByModelId.Request = {
    modelId: props.overviewId,
    startTime: parseTime(props.startTime),
    endTime: parseTime(props.endTime)
  }
  data_loading.value = true
  chart_loading.value = true
  getCalculateDataByModelId(params).then(res => {
    if (res.datas) {
      // table.value.init(res.datas)
      // console.log(res.datas, JSON.parse(JSON.stringify(table)));
      table.length = 0
      columns.length = 0
      res.datas.forEach((item, index) => {
        item.key = index
      })
      table.push(...res.datas)
      columns.push(...res.columns)
      columns.forEach((item, index) => {
        // item.width = 200;
        if (item.dataIndex?.startsWith("morrow")) {
          item.slotName = "optional";
          item.align = 'center';
        }
      })
      // console.log(columns, table);
      // computedChartDataFunc(res.datas)
    }
    data_loading.value = false
    chart_loading.value = false
  }).catch(err => {
    data_loading.value = false
    chart_loading.value = false
  })
}
onMounted(() => {
  getDataListFunc()
})
</script>

<style lang='less' scoped>
.widget-content {
  height: calc(100% - 40px);
}
</style>
