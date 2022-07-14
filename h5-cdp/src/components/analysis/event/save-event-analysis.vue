<!-- 用来保存事件分析 -->
<template>
    <a-modal v-bind="$attrs" title-align="start" :unmount-on-close="true" :closable="false">
        <template #title>
            添加到概览
        </template>
        <a-form :rules="form_rules" :model="event_analysis_data" label-align="left" :label-col-props="{ span: 4 }">
            <a-form-item field="name" label="名称" :rules="[{ required: true, message: '请输入名称' }]">
                <a-input v-model="event_analysis_data.name" placeholder="请输入名称"></a-input>
            </a-form-item>
            <a-form-item field="overviewId" label="选择概览">
                <a-select v-model="event_analysis_data.overviewId" placeholder="请选择"
                    :disabled="!!oldSave_event_analysis">
                    <template v-for="(item, index) in overview_list" :key="index">
                        <a-option :value="item.id">{{ item.name }}</a-option>
                    </template>
                </a-select>
            </a-form-item>
            <a-form-item label="时间粒度">
                <a-select v-model="event_analysis_data.timeGranularity" placeholder="请选择">
                    <template v-for="(item, index) in time_granularity_list" :key="index">
                        <a-option :value="item.value">{{ item.label }}</a-option>
                    </template>
                </a-select>
            </a-form-item>
            <a-form-item label="图表类型">
                <a-radio-group v-model="event_analysis_data.chartType">
                    <a-radio :value="0">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <icon-font :size="18" type="icon-linechart" />
                                <div>线图</div>
                            </a-card>
                        </template>
                    </a-radio>
                    <a-radio :value="1">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <icon-font :size="18" type="icon-barchart" />
                                <div>柱图</div>
                            </a-card>
                        </template>
                    </a-radio>
                    <a-radio :value="2" :disabled="true">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <icon-font :size="18" type="icon-piechart" />
                                <div>环图</div>
                            </a-card>
                        </template>
                    </a-radio>
                    <a-radio :value="3" :disabled="chart_flag">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <icon-font :size="18" type="icon-duijimianjitu" />
                                <div>积图</div>
                            </a-card>
                        </template>
                    </a-radio>
                    <a-radio :value="4" :disabled="true">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <icon-font :size="18" type="icon-table" />
                                <div>表格</div>
                            </a-card>
                        </template>
                    </a-radio>
                    <a-radio :value="5" :disabled="chart_flag">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <icon-font :size="18" type="icon-layer-number" />
                                <div>数值</div>
                            </a-card>
                        </template>
                    </a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item label="同时显示">
                <!-- 此处为多选 -->
                <template v-if="event_analysis_data.chartType != 4">
                    <!--                    <a-checkbox v-model="isAvg">均值</a-checkbox>-->
                    <!--                    <a-checkbox v-model="isSum">合计</a-checkbox>-->
                    <a-checkbox v-model="isYearToYear">同环比</a-checkbox>
                </template>
            </a-form-item>
            <a-form-item label="窗口尺寸">
                <a-radio-group v-model="event_analysis_data.windowSize">
                    <a-radio value="mini" :disabled="event_analysis_data.chartType != 5">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <div data-size="small"></div>
                                <div>小</div>
                            </a-card>
                        </template>
                    </a-radio>
                    <a-radio value="normal">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <div data-size="normal"></div>
                                <div>中</div>
                            </a-card>
                        </template>
                    </a-radio>
                    <a-radio value="large">
                        <template #radio="{ checked }">
                            <a-card hoverable :checked="checked" class="redio-card">
                                <div data-size="large"></div>
                                <div>大</div>
                            </a-card>
                        </template>
                    </a-radio>
                </a-radio-group>
            </a-form-item>
        </a-form>
        <template #footer>
            <a-button @click="cancelFunc">取消</a-button>
            <a-button :loading="save_loading" type="primary" @click="confrimFunc">确定</a-button>
        </template>
    </a-modal>
</template>

<script lang='ts' setup>
import MIconFont from "_hooks/useIconFont"
import useMessage from "_hooks/useMessage"
import { saveOrUpdate } from "_apis/analysis"
import { getOverviewList } from "_apis/overview"
import type { OverviewItem } from "_types/overview"
import type { SavaEventAnalysisRequest, CalculateDataRequest } from "_types/analysis"
const IconFont = MIconFont();
const { MessageSuccess, MessageError } = useMessage()
const emits = defineEmits<{
    (event: "update:visible", data: boolean): void
}>();

const route = useRoute()
const router = useRouter()
const props = defineProps<{
    oldSave_event_analysis: SavaEventAnalysisRequest | null
}>()

const form_rules: FromRules = {
    name: [
        {
            required: true,
            message: "请输入名称"
        }
    ],
    overviewId: [
        {
            required: true,
            message: "请选择概览"
        }
    ],
}


let event_analysis_data: SavaEventAnalysisRequest = reactive({
    name: "",
    startTime: "",
    endTime: "",
    chartType: 0,
    isAvg: 0,
    isSum: 0,
    isYearToYear: 0,
    overviewId: NaN,
    timeGranularity: "day",
    type: 0,
    windowSize: "normal",
    selectPropertyParams: [],
    wherePropertyParams: [],
    groupPropertyParams: []
})

let save_loading = ref<boolean>(false)

let overview_list = reactive<OverviewItem[]>([])

const time_granularity_list = [
    {
        label: "天",
        value: "day"
    },
    {
        label: "月",
        value: "month"
    },
    {
        label: "小时",
        value: "hour"
    },
    {
        label: "分钟",
        value: "minute"
    },
]

let isAvg = ref<boolean>(true)
let isSum = ref<boolean>(true)
let isYearToYear = ref<boolean>(false)

const chart_flag = computed(() => {
    let { groupPropertyParams, selectPropertyParams } = toRefs(event_analysis_data);
    let flag

    if (groupPropertyParams && groupPropertyParams.value && selectPropertyParams && selectPropertyParams.value) {
        flag = groupPropertyParams.value.length > 1 || selectPropertyParams.value.length > 1
    }

    return flag
})

// 获取所有概览
const getDataListFunc = (): void => {
    getOverviewList({}).then(res => {
        overview_list.length = 0
        if (res.datas) {
            overview_list.push(...res.datas)

            if (route.query.oi) {
                event_analysis_data.overviewId = Number(route.query.oi)
            }
        }
    }).catch(err => { })
}

// 保存当前分析
const confrimFunc = (): void => {
    let params: SavaEventAnalysisRequest = {
        ...event_analysis_data,
        isAvg: isAvg.value ? 1 : 0,
        isSum: isSum.value ? 1 : 0,
        isYearToYear: isYearToYear.value ? 1 : 0,
    }
    save_loading.value = true;
    // console.log(params);
    if (params.name == '') {
        MessageError("名称不可为空");
        save_loading.value = false
    } else {
        saveOrUpdate(params).then(res => {
            save_loading.value = false;
            MessageSuccess("操作成功");
            router.push('/overview/' + event_analysis_data.overviewId)
            cancelFunc()
        }).catch(err => {
            save_loading.value = false;
        })
    }

}

// 关闭当前弹窗
const cancelFunc = () => {
    isAvg.value = true
    isSum.value = true
    isYearToYear.value = false
    event_analysis_data.id = undefined
    event_analysis_data.name = '';
    event_analysis_data.endTime = ''
    event_analysis_data.startTime = ''
    event_analysis_data.chartType = 0
    event_analysis_data.overviewId = NaN
    event_analysis_data.windowSize = 'normal'
    event_analysis_data.timeGranularity = 'day'
    event_analysis_data.selectPropertyParams = []
    event_analysis_data.wherePropertyParams = []
    event_analysis_data.groupPropertyParams = []
    // console.log(event_analysis_data)
    emits('update:visible', false)
}

// 父级传递一些必要参数
const init = (data: CalculateDataRequest) => {
    // console.log(data);
    event_analysis_data.endTime = data.endTime
    event_analysis_data.startTime = data.startTime
    event_analysis_data.timeGranularity = data.timeGranularity
    event_analysis_data.selectPropertyParams = data.selectPropertyParams ? data.selectPropertyParams : []
    event_analysis_data.wherePropertyParams = data.wherePropertyParams
    event_analysis_data.groupPropertyParams = data.groupPropertyParams ? data.groupPropertyParams : []
    
    if (props.oldSave_event_analysis) {
        event_analysis_data.id = props.oldSave_event_analysis.id
        event_analysis_data.name = props.oldSave_event_analysis.name
        event_analysis_data.overviewId = props.oldSave_event_analysis.overviewId
        // event_analysis_data.timeGranularity = props.oldSave_event_analysis.timeGranularity
        event_analysis_data.chartType = props.oldSave_event_analysis.chartType
        event_analysis_data.isAvg = props.oldSave_event_analysis.isAvg
        event_analysis_data.isSum = props.oldSave_event_analysis.isSum
        event_analysis_data.isYearToYear = props.oldSave_event_analysis.isYearToYear
        isYearToYear.value = props.oldSave_event_analysis.isYearToYear == 1 ? true : false
        event_analysis_data.windowSize = props.oldSave_event_analysis.windowSize
    }
}

watch(() => event_analysis_data.chartType, (val, oldVal) => {
    // console.log(event_analysis_data);
    
    if (oldVal == 5 && event_analysis_data.windowSize == 'mini') {
        event_analysis_data.windowSize = 'normal'
    }
})

onMounted(() => {
    getDataListFunc()

})

defineExpose({
    init
})

</script>

<style lang='less' scoped>
.arco-radio {
    margin: 0px;
    padding: 0 10px 0 0;

    :deep(.arco-card-body) {
        padding: 5px 16px;
        display: flex;
        justify-content: center;
        flex-direction: column;
        align-items: center;

        &>div {
            font-size: 12px;
            margin-top: 3px;

            &[data-size='small'] {
                width: 60px;
                height: 20px;
                background-size: 100% 100%;
                background-repeat: no-repeat;
                background-position: center;
                background-image: url('@/assets/images/samll.svg');
            }

            &[data-size='normal'] {
                width: 60px;
                height: 20px;
                background-size: 100% 100%;
                background-repeat: no-repeat;
                background-position: center;
                background-image: url('@/assets/images/normal.svg');
            }

            &[data-size='large'] {
                width: 60px;
                height: 20px;
                background-size: 100% 100%;
                background-repeat: no-repeat;
                background-position: center;
                background-image: url('@/assets/images/large.svg');
            }
        }
    }

    &.arco-radio-checked {
        &>div.arco-card {
            border-color: @checked-border-color;

            &>div.arco-card-body {
                padding: 5px;

                &>svg {
                    color: @checked-border-color;
                }

                &>div {
                    color: @checked-border-color;
                }
            }
        }
    }

    &.arco-radio-disabled {

        &>div.arco-card {
            border-color: @disabled-border-color;
            background-color: @disabled-background-color;

            &>div.arco-card-body {

                &>svg,
                div {
                    color: @disabled-font-color;
                }
            }
        }
    }

}
</style>
