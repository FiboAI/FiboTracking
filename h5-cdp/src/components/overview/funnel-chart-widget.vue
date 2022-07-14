<!-- 折线图 -->
<template>

    <widget-container v-bind="$attrs" :widget-id="overviewId"  :loading="data_loading" size="normal"
        :startTime="startTime" :endTime="endTime">
        <div class="widget-content" data-size="normal">
            <m-chart :loading="chart_loading" ref="chart"></m-chart>
        </div>
    </widget-container>
</template>

<script lang='ts' setup>
import useDate from "_hooks/useDate";
import { getCalculateDataByModelId } from "_apis/overview";
import ComputedChart from "_workers/overView-funnel-chart?worker";
import type { GetCalculateDataByModelId, OverviewDetailsResponseData } from "_types/overview";
import type { CalculateData } from "_types/analysis";

const { parseTime, getTime } = useDate()
const props = defineProps<{
    overviewId: number | string
    // size: 'normal' | 'mini' | 'large'
    startTime: string
    endTime: string
    data: OverviewDetailsResponseData
}>();

const chart = ref()

let chart_loading = ref<boolean>(false)
let data_loading = ref<boolean>(false)

const computedChartDataFunc = (datas: CalculateData[],times:string[],modelData:GetCalculateDataByModelId.modelData) => {
    const computed_line_data = new ComputedChart();
    computed_line_data.onmessage = (event) => {
        chart_loading.value = false
        let { Xdata, serirs } = event.data;
        let option = {
            legend: {
                top: "10px",
            },
            tooltip: {
                trigger: 'axis',

                valueFormatter:(value:number)=>value+'%',
            },
            xAxis: {
                type: 'category',
                data: Xdata
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                        margin:3,
                        formatter: function(value:number){

                            return value + '%'
                        }
                    },
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
        };
        chart.value.init(option)
    }
    let object = {
      datas:datas,
      times:times,
      modelData:modelData
    }
    computed_line_data.postMessage(object);
}

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
            computedChartDataFunc(res.datas,res.times,res.modelData)
        }
        data_loading.value = false
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
