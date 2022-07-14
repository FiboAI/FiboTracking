<!-- 基础概览 -->
<template>
    <div class="dashboard-container">
        <m-loading :loading="loading">
            <overview-header :editor="overview_id != '1'" :title="overview_name" @on-delete="delOvervierFunc"
                v-bind="$attrs" @on-add-component="addComponentFunc" @on-refresh="refreshWidgetDataFunc" />
            <overview-content>
                <!-- <number-widget size="small" /> -->
                <template v-for="(item, index) in analysis_list" :key="index">
                    <component :ref="'widget_' + item.id" :data="item" :name="item.name" :start-time="item.startTime"
                        :end-time="item.endTime" :modelType="item.type" :type="item.chartType"
                        :size="item.windowSize || 'normal'" :overview-id="item.id"
                        :is="WidgetEnum[item.chartType]"
                        @refresh="removeRefresh()" />
                </template>
            </overview-content>
        </m-loading>
    </div>
    <add-analysis-model-alert-modal ref="add_analysis_model_alert" @on-refrsh="getDataListFunc" />
</template>

<script lang='ts' setup>
import useDate from "_hooks/useDate";
import useMessage from "_hooks/useMessage";
import { getOverviewDetails, removeOverview } from "_apis/overview"
import type { ComponentInternalInstance, DefineComponent } from "vue"
import type { OverviewDetailsRequest, OverviewDetailsResponseData, RemoveOverview } from "_types/overview"
import { AddAnalysisModelAlertModal } from "_components/overview";
import useAnalysisListStore from "_stores/useAnalysisList";
const LineChartWidget = defineAsyncComponent(() => import('_components/overview/line-chart-widget.vue'));
const EventBarChartWidget = defineAsyncComponent(() => import('_components/overview/line-chart-widget.vue'));
const NumberWidget = defineAsyncComponent(() => import('_components/overview/number-widget.vue'));
const funnelChart = defineAsyncComponent(() => import('_components/overview/funnel-chart-widget.vue'));
const remainTable = defineAsyncComponent(() => import('_components/overview/remain-table.vue'));
const sqlTable = defineAsyncComponent(() => import('_components/overview/sql-table.vue'));
const WidgetEnum: { [key: string]: any } = {
    0: LineChartWidget,
    1: EventBarChartWidget,
    5: NumberWidget,
    6: funnelChart,
    7: remainTable,
    8: sqlTable
}
const { parseTime, getAddTime } = useDate()

let lastweek_date = reactive<string[]>([
    getAddTime(-7),
    getAddTime(-1)
]);


const props = defineProps<{
    overviewId: number | string
}>();

const emits = defineEmits<{
    (event: "update:overviewId", data: string | number): void
    (event: "on-refresh", data?: string | number): void
}>()

const { MessageSuccess } = useMessage();
const context = getCurrentInstance();
const router = useRouter();
// 预览id
const overview_id = computed((): string => <string>props.overviewId || '1')
// console.log(overview_id.value)
// 用来控制页面loading
let loading = ref<boolean>(false)
// 当前概览的名称
let overview_name = ref<string>('')
// 当前概览所有事件分析
let analysis_list = reactive<OverviewDetailsResponseData[]>([])
// 获取当前概览的详情
const getDataListFunc = (): void => {
    let params: OverviewDetailsRequest = {
        overviewId: overview_id.value || 1
    }
    loading.value = true
    getOverviewDetails(params).then((res: any) => {
        loading.value = false
        analysis_list.length = 0
        overview_name.value = res.name || '暂无标题'
        if (overview_id.value == '1') {
            res.datas.forEach((item: any) => {
                item.startTime = lastweek_date[0]
                item.endTime = lastweek_date[1]
            })
            // console.log('更改时间', res.datas);
        }
        if (res.datas) {
            analysis_list.push(...res.datas)
        }
    }).catch(err => {
        loading.value = false
    })
}
const removeRefresh = (): void => {
    location.reload();
}
// 删除当前概览
const delOvervierFunc = () => {

    let params: RemoveOverview.Request = {
        overviewId: Number(overview_id.value)
    }
    // console.log(params)
    removeOverview(params).then(res => {
        MessageSuccess("操作成功");
        // emits('update:overviewId', 1)
        router.push({
            path: "/overview/home"
        })
    }).catch(err => {

    })
}
const add_analysis_model_alert = ref()
// 添加组件
const addComponentFunc = () => {
    add_analysis_model_alert.value.openFunc({ id: overview_id.value });
    // router.push({
    //     path: "/analysis/event",
    //     query: {
    //         oi: overview_id.value
    //     }
    // })
}

// 刷新所有组件状态
const refreshWidgetDataFunc = () => {
    //清除后台缓存
}

//点击组件
// const Store = useAnalysisListStore()
// const analysisModelFunc = (modelId:number,type:number)=>{
//   let analysis = Store.$state.analysis_list.find(x=>x.modelType==type)
//   // console.log(props.modelType)
//   router.push({
//     path: analysis&&analysis.key,
//     query: {
//       model: modelId
//     }
//   })
// }
// 监听预览id变化
watch(overview_id, (val) => {
    getDataListFunc()
})

onMounted(() => {
    getDataListFunc()
})
</script>

<style lang='less' scoped>
.dashboard-container {
    height: 100%;
    width: 100%;
}
</style>
