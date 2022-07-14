<!-- 数字小组件 -->
<template>
    <widget-container :loading="data_loading" :size="size" :widget-id="overviewId" v-bind="$attrs"
        :startTime="startTime" :endTime="endTime">
        <div class="widget-content" :data-size="size">
            <div class="basis-data">
                <div>{{ now_data.nowDate }}</div>
                <div class="data-number">
                    <span class="number">{{ toDecimalMarkFunc(now_data.nowDateValue) }}</span>
                    <span class="measures-unit">
                        {{ now_data.calcType == 'total' ? '次'
                                : now_data.calcType == 'users' ? '人'
                                    : now_data.calcType == 'count' ? '个' : ''
                        }}
                    </span>
                </div>
            </div>
            <template v-if="isYearToYearFlag">
                <div class="is-year-to-year" :data-size="size">
                    <a-tooltip :content="pre_year_to_year_data.text" mini>
                        <div>
                            <span>同比:</span>
                            <span :data-type="pre_year_to_year_data_type">
                                <template v-if="pre_year_to_year_data_type == 'down'">
                                    <icon-caret-down />
                                </template>
                                <template v-else-if="pre_year_to_year_data_type == 'up'">
                                    <icon-caret-up />
                                </template>
                                {{ getMathAbs(pre_year_to_year_data.preYearToYearRate)
                                }}%</span>
                        </div>
                    </a-tooltip>
                    <a-tooltip :content="pre_chain_data.text" mini>
                        <div>
                            <span>环比:</span>
                            <span :data-type="pre_chain_data_type"><template v-if="pre_chain_data_type == 'down'">
                                    <icon-caret-down />
                                </template>
                                <template v-else-if="pre_chain_data_type == 'up'">
                                    <icon-caret-up />
                                </template>{{ getMathAbs(pre_chain_data.preChainRate) }}%</span>
                        </div>
                    </a-tooltip>
                </div>
            </template>
        </div>
    </widget-container>
</template>

<script lang='ts' setup>
import useDate from "_hooks/useDate";
import { toDecimalMarkFunc } from "_utils/utils"
import { getCalculateDataByModelId } from "_apis/overview";
import type { GetCalculateDataByModelIdRequest, OverviewDetailsResponseData, GetCalculateDataByModelId } from "_types/overview"

const { parseTime, getTime } = useDate()
const props = defineProps<{
    overviewId: number | string
    size: 'normal' | 'mini' | 'large'
    startTime: string
    endTime: string
    data: OverviewDetailsResponseData
}>()

const isYearToYearFlag = computed((): boolean => {
    let flag: boolean = !!props.data.isYearToYear
    return flag
})
// 是否处于加载中
let data_loading = ref<boolean>(false)

// 当前数值
let now_data = reactive<{
    nowDate: string
    nowDateValue: number
    calcType: string
}>({
    nowDate: getTime('MM-DD'),
    nowDateValue: 0,
    calcType:"total"
})
// 环比数据
let pre_chain_data = reactive<GetCalculateDataByModelId.PreChainMap>({
    preChainDate: "",
    preChainValue: 0,
    preChainRate: 0,
    text: '',
})
// 判断一下环比是增长还是下降
let pre_chain_data_type = ref<string>('')
// 同比数据
let pre_year_to_year_data = reactive<GetCalculateDataByModelId.PreYearToYearMap>({
    preYearToYearDate: "",
    preYearToYearValue: 0,
    preYearToYearRate: 0,
    text: '',
})
// 用来判断一下同比是增长还是下降
let pre_year_to_year_data_type = ref<string>('')
let calcType = ref<string>('')

// 获取当前模块对应的数据
const getDataListFunc = () => {
    let params: GetCalculateDataByModelIdRequest = {
        modelId: props.overviewId,
        startTime: parseTime(props.startTime),
        endTime: parseTime(props.endTime)
    }
    data_loading.value = true
    getCalculateDataByModelId(params).then(res => {
        // console.log(params);
        // 当前数值
        if (res.nowMap) {
            //小时  分钟
            if (res.modelData.timeGranularity === 'hour' || res.modelData.timeGranularity === 'minute') {
                now_data.nowDate = parseTime(res.nowMap.nowDate, 'MM-DD HH:mm');
                //月
            } else if (res.modelData.timeGranularity === 'month') {
                now_data.nowDate = parseTime(res.nowMap.nowDate, 'YYYY-MM');
            } else {
                //天
                now_data.nowDate = parseTime(res.nowMap.nowDate, 'MM-DD');
            }
            now_data.calcType = res.modelData.selectPropertyParams[0].calcType;
            now_data.nowDateValue = res.nowMap.nowDateValue;
            // console.log(now_data);

        }
        // 环比
        if (res.preChainMap) {
            if (res.modelData.timeGranularity === 'hour' || res.modelData.timeGranularity === 'minute') {
                pre_chain_data.preChainDate = parseTime(res.preChainMap.preChainDate, 'MM-DD HH:mm');
                //月
            } else if (res.modelData.timeGranularity === 'month') {
                pre_chain_data.preChainDate = parseTime(res.preChainMap.preChainDate, 'YYYY-MM');
            } else {
                //天
                pre_chain_data.preChainDate = parseTime(res.preChainMap.preChainDate, 'MM-DD');
            }
            pre_chain_data.preChainRate = res.preChainMap.preChainRate;
            pre_chain_data.preChainValue = res.preChainMap.preChainValue;
            if (res.preChainMap.preChainRate != 0) {
                pre_chain_data_type.value = res.preChainMap.preChainRate > 0 ? 'up' : 'down'
            }
            pre_chain_data.text = "对比" + pre_chain_data.preChainDate + (pre_chain_data_type.value === "up" ? "增长" : "下降") + getMathAbs(pre_chain_data.preChainRate) + "%";

        }
        // 同比
        if (res.preYearToYearMap) {
            if (res.modelData.timeGranularity === 'hour' || res.modelData.timeGranularity === 'minute') {
                pre_year_to_year_data.preYearToYearDate = parseTime(res.preYearToYearMap.preYearToYearDate, 'MM-DD HH:mm');
                //月
            } else if (res.modelData.timeGranularity === 'month') {
                pre_year_to_year_data.preYearToYearDate = parseTime(res.preYearToYearMap.preYearToYearDate, 'YYYY-MM');
            } else {
                //天
                pre_year_to_year_data.preYearToYearDate = parseTime(res.preYearToYearMap.preYearToYearDate, 'MM-DD');
            }
            pre_year_to_year_data.preYearToYearValue = res.preYearToYearMap.preYearToYearValue;
            pre_year_to_year_data.preYearToYearRate = res.preYearToYearMap.preYearToYearRate;
            if (res.preYearToYearMap.preYearToYearRate != 0) {
                pre_year_to_year_data_type.value = res.preYearToYearMap.preYearToYearRate > 0 ? 'up' : 'down'
            }
            pre_year_to_year_data.text = "对比" + pre_year_to_year_data.preYearToYearDate + (pre_year_to_year_data_type.value === "up" ? "增长" : "下降") + getMathAbs(pre_year_to_year_data.preYearToYearRate) + "%";
        }
        data_loading.value = false
    }).catch(err => {
        data_loading.value = false
    })
}

// 获取绝对值
const getMathAbs = (value: number): number => {
    return Math.abs(value)
}

// 提供给父级进行数据刷新
const refreshDataFunc = () => {
  getDataListFunc()
}

defineExpose({
    refreshDataFunc
})

onMounted(() => {
    getDataListFunc()
})
</script>

<style lang='less' scoped>
div.widget-content {
    display: flex;
    height: calc(100% - 40px);

    &[data-size='mini'] {
        flex-direction: column;
        justify-content: center;
    }

    &[data-size='normal'],
    &[data-size='large'] {
        align-items: center;
    }

    &>div.basis-data {
        margin-top: 12px;

        &>div {
            &:first-child {
                color: #8492A6;
                font-size: 13px;
                font-weight: 500;
            }

            &.data-number {
                line-height: 1.2;

                &>span {
                    color: #475669;

                    &.number {
                        font-size: 40px;
                    }

                    &.measures-unit {
                        margin-top: 30px;
                        font-size: 12px;
                        margin-left: 5px;
                    }
                }
            }
        }

    }

    &>div.is-year-to-year {

        &>div {
            min-width: 70px;

            &>span {
                line-height: 1;

                &:first-child {
                    margin-right: 5px;
                }

                &:last-child {
                    font-size: 12px;

                    &[data-type='up'] {
                        color: #2dca93;
                    }

                    &[data-type='down'] {
                        color: #fc6772;
                    }
                }
            }
        }

        &[data-size='mini'] {
            display: flex;
            font-size: 13px;
            color: #8492A6;
            margin-top: 8px;
            font-weight: 400;

            &>div {
                margin-right: 5px;

                &:last-child {
                    margin-right: 0;
                }
            }
        }

        &[data-size='normal'],
        &[data-size='large'] {
            padding: 16px 0 16px 16px;
            font-size: 13px;
            color: #8492A6;
            margin-top: 27px;

            &>div {
                margin-bottom: 5px;

                &:last-child {
                    margin-bottom: 0;
                }
            }
        }
    }
}
</style>
