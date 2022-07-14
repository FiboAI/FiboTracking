<!-- 图表 -->
<template>
    <div class="echart-container" ref="chart">
    </div>
</template>

<script lang='ts' setup>
import * as echarts from 'echarts/core';
import {
    GridComponent,
    GridComponentOption,
    LegendComponent,
    LegendComponentOption,
    ToolboxComponent,
    ToolboxComponentOption,
    TooltipComponent,
    TooltipComponentOption
} from 'echarts/components';
import { LineChart, LineSeriesOption, BarChart, BarSeriesOption } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';

const props = withDefaults(defineProps<{
    loading?: boolean
}>(), {
    loading: false
})

let { loading } = toRefs(props)

echarts.use([
    GridComponent,
    LegendComponent,
    LineChart,
    BarChart,
    ToolboxComponent,
    CanvasRenderer,
    UniversalTransition,
    TooltipComponent
]);

type EChartsOption = echarts.ComposeOption<
    | GridComponentOption
    | LineSeriesOption
    | LegendComponentOption
    | ToolboxComponentOption
    | BarSeriesOption
    | TooltipComponentOption
>;

const chart = ref();

let myEchart: echarts.ECharts

const init = (options: EChartsOption) => {
    myEchart.clear()
    myEchart.setOption(options)
}


watch(loading, (val) => {
    if (val) {
        myEchart.showLoading('default', {})
    } else {
        myEchart.hideLoading()
    }
})

onMounted(() => {
    myEchart = echarts.init(<HTMLElement>chart.value)
})

onUnmounted(() => {
    myEchart.dispose()
})

defineExpose({
    init,
    loading
})
</script>

<style lang='less' scoped>
.echart-container {
    height: 100%;
    width: 100%;
    overflow: hidden;
}
</style>