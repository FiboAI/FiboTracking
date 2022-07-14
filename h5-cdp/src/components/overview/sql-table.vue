<!-- 折线图 -->
<template>
  <widget-container v-bind="$attrs" :widget-id="overviewId" :loading="data_loading" size="normal" :startTime="startTime"
    :endTime="endTime">
    <div class="widget-content" data-size="normal">
      <m-table-custom :loading="chart_loading" :data-list="table" :columns="columns"></m-table-custom>
    </div>
  </widget-container>
</template>

<script lang='ts' setup>
import useDate from "_hooks/useDate";
import { getCalculateDataByModelId } from "_apis/overview";
import ComputedChart from "_workers/overView-funnel-chart?worker";
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
let table = reactive<CalculateData[]>([]);
let columns = reactive<TableColumnData[]>([]);
let chart_loading = ref<boolean>(false)
let data_loading = ref<boolean>(false)

// 获取当前模块对应的数据
const getDataListFunc = () => {
  let params: GetCalculateDataByModelId.Request = {
    modelId: props.overviewId,
    startTime: parseTime(props.startTime),
    endTime: parseTime(props.endTime)
  }
  // console.log(params);
  data_loading.value = true
  chart_loading.value = true

  getCalculateDataByModelId(params).then((res: any) => {
    if (res.datas) {
      table.length = 0
      columns.length = 0
      table.push(...res.datas.data)
      res.datas.columns.map((item: any) => {
        item.ellipsis = true
        item.tooltip = true
      })
      columns.push(...res.datas.columns)
      // console.log(columns, table);
    }
    data_loading.value = false
    chart_loading.value = false
  }).catch(err => {
    data_loading.value = false
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
