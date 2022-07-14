<!-- 数据表格 -->
<template>
  <div class="event-table modular">
    <div class="tools">
      <!-- <a-select :model-value="props.timeGranularity" placeholder="请选择" :style="{ width: '140px' }"
        @change="timeGranularityChangeFunc">
        <template v-for="(item, index) in time_granularity_list" :key="index">
          <a-option :value="item.value">{{ item.label }}</a-option>
        </template>
      </a-select> -->
      <range-picker @change="rangePickerChangeFunc" :model-value="[props.startTime, props.endTime]"
        :style="{ width: '300px', marginBottom: '10px' }" />
    </div>
    <a-table :width="200" :columns="columns" :data="data" :pagination="true" :loading="loading"
      :defaultExpandAllRows="false" :stripe="true" :scroll="scroll" row-class="common-row">
      <template #optional="{ record, column }">
        <div style="background: #ddf0ff;min-height: 40px;" v-if="record[column.dataIndex] != ''">
          {{ record[column.dataIndex] }}</div>
      </template>
    </a-table>
  </div>
</template>

<script lang='ts' setup>
import type { CalculateData } from "_types/analysis";
import type { TableColumnData } from "@arco-design/web-vue"
const props = defineProps<{
  dataList: CalculateData[],
  loading: boolean,
  columns: Array<TableColumnData>
  startTime: string
  endTime: string
  // x: number
}>()
// let x = 200 * props.columns.length
const emits = defineEmits<{
  (event: "update:startTime", data: string): void
  (event: "update:endTime", data: string): void
  // (event: "update:timeGranularity", data: string): void
}>()
let summary = ref();
let scroll = reactive<any>({
  x: 1500,
  y: 600
})
let data = computed(() => props.dataList)
const rangePickerChangeFunc = (value: unknown): void => {
  emits('update:startTime', (<string[]>value)[0])
  emits('update:endTime', (<string[]>value)[1])
}

</script>

<style lang='less' scoped>
.my-tr {
  background: red;
}

.event-table :deep(.arco-table .arco-table-body .arco-table-cell) {
  padding: 0 !important;
}
</style>
