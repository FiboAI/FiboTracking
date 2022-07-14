<!-- 图表 -->
<template>
  <div class="event-table modular">
    <a-table :width="200" :columns="columns" :data="data" :pagination="true" :loading="loading"
      :defaultExpandAllRows="false" :stripe="true" row-class="common-row" :scroll="scroll" :key="componentKey">
      <template #optional="{ record, column }">
        <div style="background: #ddf0ff;min-height: 40px;" v-if="record[column.dataIndex] != ''">
          {{ record[column.dataIndex] }}</div>
      </template>
    </a-table>
    <!-- {{data}} -->
  </div>
</template>


<script lang='ts' setup>
import type { CalculateData } from "_types/analysis";
import type { TableColumnData } from "@arco-design/web-vue";
const props = defineProps<{
  dataList: CalculateData[],
  loading: boolean,
  // columns: Array<TableColumnData>,
  columns: TableColumnData[],
}>()
let componentKey = ref<number>(0)
const scroll = {
  x: 200 * 5,
  y: 200
}
let data = computed(() => props.dataList)
// let columns = computed(() => props.columns)

watch(props.dataList, () => {
  // console.log(props.dataList)
  // console.log(props.columns, componentKey.value)
  componentKey.value +=  1
  // console.log(componentKey.value)
}, { deep: true });

</script>

<style lang='less' scoped>
.my-tr {
  background: red;
}

.event-table :deep(.arco-table .arco-table-body .arco-table-cell) {
  padding: 0 !important;
}
</style>
