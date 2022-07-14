<!-- 事件分析 -->
<template>
    <div class="event-container">
        <analysis-header :title="page_title" @on-save="saveAnalysisModal" :set="!!modelId" />
        <div class="event-content">
            <div>
                <div class="event-settings modular">
                    <template v-for="(item, index) in condition_list" :key="`${index}`">
                        <event-condition-item v-model:data="condition_list[index]" :index="index"
                            @on-delete="delConditionFunc" />
                    </template>
                    <a-button type="text" style="width: 100px;" long @click="addConditionFunc">
                        <icon-plus />
                        添加指标
                    </a-button>
                    <a-divider />
                    <template v-for="(item, index) in global_screens" :key="`${index}`">
                        <event-global-screen v-model:data="global_screens[index]" :index="index"
                            ref='eventGlobalScreenRef' @on-delete="delGlobalScreenFunc" />
                    </template>
                    <a-button type="text" style="width: 100px;" long @click="addGlobalScreenFunc">
                        <icon-plus />
                        全局筛选
                    </a-button>
                    <a-divider />
                    <div style="position: relative;">
                        <span :style="{ marginRight: '10px' }">按</span>
                        <template v-for="(_, index) in group_property_params" :key="index">
                            <group-property-item v-model:propertyEn="group_property_params[index].propertyEn"
                                :index="index" @on-delete="delGroupPropertyParamsFunc" />
                        </template>
                        <add-group-property @on-add="addGroupPropertyParamsFunc" />
                        <span :style="{ marginLeft: '10px' }">查看</span>
                        <a-button type="primary"
                            style="position: absolute;right: 0;top: -10px;width: 60px;margin-left: 10px;" long
                            @click="getDataListFunc">
                            搜索
                        </a-button>
                    </div>
                    <!-- <a-divider /> -->
                    <div>

                    </div>
                </div>

                <data-chart :loading="data_loading" :times="times" :data-list="data_list"
                    v-model:startTime="calculate_data_date[0]" :selectViewNames="condition_list"
                    :groupViewNames="group_property_params" v-model:endTime="calculate_data_date[1]"
                    v-model:timeGranularity="time_granularity" />
                <data-table :data-list="data_list" :columns="columns" />
            </div>
        </div>
    </div>
    <save-event-analysis ref="save_event_analysis" v-model:visible="save_visible" :width="600"
        :oldSave_event_analysis="oldSave_event_analysis" />
</template>

<script lang='ts' setup>
import { getCalculateData } from "_apis/analysis";
import useDate from "_hooks/useDate";
import { ManipulateType } from "dayjs"
import useMessage from "_hooks/useMessage";
import { getModelListByOverviewId } from "_apis/overview";
import eventAnalysisStore from '@/stores/useEventList'
import type {
    SelectPropertyParams,
    WherePropertyParams,
    GroupPropertyParams,
    CalculateDataRequest,
    CalculateData,
    SavaEventAnalysisRequest
} from "_types/analysis"
import type { TableColumnData } from "@arco-design/web-vue"
import { Ref } from "vue";

const eventGlobalScreenRef = ref()

const route = useRoute()

const { getTime, getAddTime } = useDate();
const { MessageError } = useMessage();
const page_title: string = "事件分析";
const save_event_analysis = ref();
let condition_list = reactive<Array<SelectPropertyParams>>([]);
let global_screens = reactive<Array<WherePropertyParams>>([]);
let group_property_params = reactive<Array<GroupPropertyParams>>([]);
let times = reactive<Array<string>>([]);
let data_list = reactive<CalculateData[]>([]);
let calculate_data_date = reactive<string[]>([
    getAddTime(-7),
    getAddTime(-1)
]);
let save_visible = ref<boolean>(false)
let time_granularity = ref<string>('day')
let data_loading = ref<boolean>(false)
const Store = eventAnalysisStore()

let event_list = computed(() => {
    return Store.event_list
})
if (JSON.parse(JSON.stringify(event_list.value)) == '') {
    Store.event_list = JSON.parse(localStorage.getItem('eventList') as string);
}


// ==================================更新漏斗===================
var modelId: Ref<number | null> = ref(null)
var oldSave_event_analysis: Ref<SavaEventAnalysisRequest | null> = ref(null)
if (route.query.model) {
    modelId.value = Number(route.query.model)
    getModelListByOverviewId({
        modelId: Number(route.query.model)
    }).then((res: any) => {
        if (res.datas) {
            let { datas } = res
            oldSave_event_analysis.value = datas
            condition_list.length = 0
            condition_list.push(...(datas.selectPropertyParams || []))
            global_screens.length = 0
            global_screens.push(...(datas.wherePropertyParams || []))
            group_property_params.length = 0
            group_property_params.push(...(datas.groupPropertyParams || []))
            calculate_data_date[0] = datas.startTime
            calculate_data_date[1] = datas.endTime
            time_granularity.value = datas.timeGranularity

        }
    })
} else {
    if (event_list.value.length > 0) {
        let obj = (event_list.value as Array<any>)[0].children[0];
        condition_list.push({
            name: obj.label,
            eventEn: obj.value.split("-")[0],
            isVirtual: obj.is_virtual,
            sort: 'A',
            propertyEn: '',
            calcType: "total"
        } as SelectPropertyParams)
    }
}

// 添加指标
const addConditionFunc = (): void => {
    // console.log(condition_list)
    let obj = (event_list.value as Array<any>)[0].children[0];
    condition_list.push({
        // name: `指标标题-${condition_list.length + 1}`,
        name: obj.label + "-" + (condition_list.length + 1),
        eventEn: obj.value.split("-")[0],
        isVirtual: obj.is_virtual,
        sort: 'A',
        propertyEn: '',
        calcType: "total"
    } as SelectPropertyParams)
}
// 添加全局筛选
const addGlobalScreenFunc = (): void => {
    global_screens.push({
        propertyEn: "",
        fieldType: "",
        conditions: "",
        value: '',
    })
}
// 添加查看类型
const addGroupPropertyParamsFunc = (value: string, text: string) => {
    group_property_params.push({ propertyEn: value, propertyCn: text })
}
// 删除指标
const delConditionFunc = (index: number): void => {
    condition_list.splice(index, 1)
}
// 删除全局筛选
const delGlobalScreenFunc = (index: number): void => {
    global_screens.splice(index, 1)
}
// 删除查看类型
const delGroupPropertyParamsFunc = (index: number): void => {
    group_property_params.splice(index, 1)
}
// 获取分析数据
const getDataListFunc = () => {
    if (condition_list.length == 0) {
        MessageError('请先选择指标')
        return
    }
    var GlobalScreenVerification = eventGlobalScreenRef.value && eventGlobalScreenRef.value.find((value: { verification: Function }) => {
        return value.verification()
    })
    if (GlobalScreenVerification) {
        MessageError('全局筛选中有未填项')
        return
    }

    let params: CalculateDataRequest = {
        type: 0,
        timeGranularity: time_granularity.value,
        startTime: calculate_data_date[0],
        endTime: calculate_data_date[1],
        selectPropertyParams: condition_list,
        wherePropertyParams: global_screens,
        groupPropertyParams: group_property_params,
    };
    data_loading.value = true;
    getCalculateData(params).then(res => {
        data_list.length = 0;
        times.length = 0
        if (res.times) {
            times.push(...res.times)
        }
        // console.log(times);
        if (res.datas) {
            data_list.push(...res.datas)
            columns.value = getColumns(condition_list)
        }
        data_loading.value = false;
        // console.log(data_list);

    }).catch(err => {
        data_loading.value = false;
    })
}
var columns: Ref<Array<TableColumnData>> = ref([])

function getColumns(arr: Array<SelectPropertyParams>): Array<TableColumnData> {
    let subarr: Array<TableColumnData> = [{
        title: '日期',
        dataIndex: 'dates',
        fixed: 'left',
        sortable: {
            sortDirections: ['ascend', 'descend']
        }
    }]
    group_property_params.forEach(value => {
        subarr.push({
            title: value.propertyCn,
            dataIndex: value.propertyCn
        })
    })
    arr.forEach(value => {
        subarr.push({
            title: value.name,
            dataIndex: value.name
        })
    })
    return subarr
}
// 保存当前分析模型
let names = reactive<Array<string>>([]);
let save_same_names = ref<boolean>(false)
const saveAnalysisModal = (): void => {
    save_same_names.value = false
    names.length = 0
    condition_list.forEach((item) => {
        names.push(item.name);
    })
    var s = names.join(",") + ",";
    for (var i = 0; i < names.length; i++) {
        if (s.replace(names[i] + ",", "").indexOf(names[i] + ",") > -1) {
            save_same_names.value = true
            break;
        }
    }
    if (save_same_names.value == true) {
        MessageError('指标名不可重复请修改')
    } else {
        var GlobalScreenVerification = eventGlobalScreenRef.value && eventGlobalScreenRef.value.find((value: { verification: Function }) => {
            return value.verification()
        })
        if (GlobalScreenVerification) {
            MessageError('全局筛选中有未填项')
            return
        }

        if (condition_list.length > 0) {
            save_event_analysis.value.init({
                timeGranularity: time_granularity.value,
                startTime: calculate_data_date[0],
                endTime: calculate_data_date[1],
                selectPropertyParams: condition_list,
                wherePropertyParams: global_screens,
                groupPropertyParams: group_property_params,
            })
            save_visible.value = true
        } else {
            MessageError("请先添加指标")
        }
    }

}

// watch
watch([time_granularity, calculate_data_date], () => {
    getDataListFunc();
})

onMounted(() => {
    if (!modelId.value) {
        getDataListFunc();
    }
})
</script>

<style lang='less' scoped>
.echart-container {
    width: 100%;
}

.modular {
    background-color: #fff;
    border-radius: 6px;
    box-shadow: 0 2px 4px @shadow-color-1;
    box-sizing: border-box;
    padding: 10px;
    margin-bottom: 16px;
    min-height: 200px;
    width: 100%;

    &:last-child {
        margin-bottom: 0;
    }
}

.event-settings {

    &>div {
        &.condition-item {
            height: 50px;
            background-color: green;
            margin-top: 10px;

            &:first-child {
                margin-top: 0;
            }
        }
    }
}

.event-container {
    height: 100%;
    box-sizing: border-box;
    padding: 16px 0 16px 16px;
    overflow: hidden;

    &>.event-content {
        height: calc(100% - 62px);
        width: 100%;
        overflow: hidden;

        &>div {
            height: 100%;
            width: 100%;
            box-sizing: border-box;
            padding-right: 16px;
            overflow-y: auto;
        }
    }
}
</style>
