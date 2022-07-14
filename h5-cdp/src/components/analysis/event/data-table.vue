<!-- 数据表格 -->
<template>
    <div class="event-table modular">
        <a-table :columns="columns" :data="data" :pagination="true" />
    </div>
</template>

<script lang='ts' setup>
import type { CalculateData } from "_types/analysis";
import type { TableSortable, TableColumnData } from "@arco-design/web-vue"
const props = defineProps<{
    dataList: CalculateData[],
    columns:Array<TableColumnData>
}>()
// console.log(props);



let data = computed(() => props.dataList)

</script>

<style lang='less' scoped>
</style>