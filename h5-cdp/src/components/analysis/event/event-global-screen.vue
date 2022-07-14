<!-- 全局筛选 -->
<template>
    <div class="event-group-screen">
        <a-select :style="{ width: '260px' }" placeholder="请选择" v-model="property_en"  @change="propertyEnChangeFunc">
            <template v-for="item in property_list">
                <a-option :value="item.nameEn">{{ item.nameCn }}</a-option>
            </template>
        </a-select>
        <a-select :style="{ width: '120px', marginLeft: '10px' }" placeholder="请选择"   v-model="conditions"
            @change="conditionsChangeFunc">
            <template v-for="item in conditions_list">
                <a-option :value="item.value">{{ item.label }}</a-option>
            </template>
        </a-select>
        <template v-if="property_type == PropertyTypes[1] && number_input_types.includes(conditions)" >
            <a-input-number v-model="conditions_num_value" :style="{ width: '120px', marginLeft: '10px' }" 
                placeholder="请输入" @input="conditionsNumValueChangeFunc" />
        </template>
        <template v-if="property_type == PropertyTypes[0] && string_input_types.includes(conditions)">
            <a-input v-model="conditions_str_value" :style="{ width: '120px', marginLeft: '10px' }" placeholder="请输入"
                @input="conditionsStrValueChangeFunc" />
        </template>
        <template v-if="property_type == PropertyTypes[1] && range_input_types.includes(conditions)">
            <a-input-number v-model="conditions_num_value" :style="{ width: '120px', marginLeft: '10px' }"
                placeholder="请输入" @input="conditionsNumValueChangeFunc" />
            <span :style="{ padding: '5px' }">-</span>
            <a-input-number v-model="conditions_num_max_value" :style="{ width: '120px' }" placeholder="请输入"
                @input="conditionsNumMaxValueChangeFunc" />
        </template>
        <a-button type="text" style="margin-left:5px">
            <icon-delete size="16" @click="emits('on-delete', props.index)" />
        </a-button>
    </div>
</template>

<script lang='ts' setup>
import { getPropertyList } from "_apis/analysis";
import type { PropertyListRequest, PropertyList, WherePropertyParams } from "_types/analysis"



enum PropertyTypes {
    string,
    number,
    bool
}

interface Conditions {
    label: string
    value: string
}

const props = defineProps<{
    data: WherePropertyParams
    index: number
}>()

const emits = defineEmits<{
    (event: "update:data", data: WherePropertyParams): void
    (event: "on-delete", index: number): void
}>()

let number_enum: Conditions[] = [
    {
        label: "等于",
        value: "="
    },
    {
        label: "不等于",
        value: "!="
    },
    {
        label: "大于",
        value: "<"
    },
    {
        label: "小于",
        value: ">"
    },
    {
        label: "区间",
        value: "range"
    },
    {
        label: "有值",
        value: "isnull"
    },
    {
        label: "没值",
        value: "isnotnull"
    },
]

let string_enum: Conditions[] = [
    {
        label: "等于",
        value: "="
    },
    {
        label: "不等于",
        value: "!="
    },
    {
        label: "包含",
        value: "like"
    },
    {
        label: "不包含",
        value: "notlike"
    },
    {
        label: "有值",
        value: "isnull"
    },
    {
        label: "没值",
        value: "isnotnull"
    },
]

let boolean_enum: Conditions[] = [
    {
        label: "真",
        value: 'true'
    },
    {
        label: "假",
        value: 'false'
    },
    {
        label: "有值",
        value: "isnull"
    },
    {
        label: "没值",
        value: "isnotnull"
    },
]

let number_input_types = ['=', '!=', '>', '<']

let string_input_types = ['=', '!=', 'like', 'notlike']

let bool_input_types = ['true', 'false', 'isnull', 'isnotnull']

let range_input_types = ['range']

const conditions_list = computed((): Conditions[] => {
    let list: Conditions[] = [];
    if (property_type.value == PropertyTypes[0]) {
        list = [...string_enum]
    }
    if (property_type.value == PropertyTypes[1]) {
        list = [...number_enum]
    }
    if (property_type.value == PropertyTypes[2]) {
        list = [...boolean_enum]
    }
    return list
})
// 记录当前conditions
let conditions = ref<string>('')
// 记录筛选值
let conditions_num_value = ref<number>(0)
let conditions_num_max_value = ref<number>(0)
let conditions_str_value = ref<string>('')
// 记录当前选中的属性id
let property_en = ref<string>('')
// 记录属性列表
const property_list = reactive<PropertyList>([]);
// 用来判断属性的类型 string、number、boolean
const property_type = computed<string>((): string => {
    let type = property_list.filter((item) => {
        if (item.nameEn == property_en.value) {
            return item
        }
    })[0]
    return type && type['fieldType'] || ''
})
// 获取属性列表
const getDataListFunc = (): void => {
    let params: PropertyListRequest = {};
    getPropertyList(params).then(res => {
        property_list.length = 0;
        if (res.datas) {
            property_list.push(...res.datas)
        }
        editInit()
    }).catch(err => {

    })
}

const conditionsNumValueChangeFunc = (value?: number): void => {
    emits('update:data', { ...props.data, value: value })
}

const conditionsStrValueChangeFunc = (value?: string): void => {
    emits('update:data', { ...props.data, value: value })
}

const conditionsNumMaxValueChangeFunc = (value?: number): void => {
    emits('update:data', { ...props.data, maxValue: value })
}

const propertyEnChangeFunc = (value: unknown): void => {
    conditions.value = ''
    emits('update:data', { ...props.data, propertyEn: <string>value, fieldType: property_type.value })
}

const conditionsChangeFunc = (value: unknown): void => {
    // console.log('conditionsChangeFunc', value)
    let emit_data: WherePropertyParams = { ...props.data }
    if (bool_input_types.includes(<string>value)) {
        // if (range_input_types.includes(oldVal)) {
        //     emit_data.maxValue = 0
        //     conditions_num_max_value.value = 0
        // }
        conditions_str_value.value = ''
        conditions_num_value.value = 0
        emit_data.value = ''
    }
    emits('update:data', { ...emit_data, conditions: <string>value })
}

const editInit = (): void => {
    // 判断一下是否是修改
    if (props.data.fieldType != '') {
        let { conditions: Conditions, value, maxValue, fieldType, propertyEn } = toRefs(props.data)
        conditions.value = Conditions.value
        property_en.value = propertyEn.value
        if (fieldType.value == 'string') conditions_str_value.value = <string>value?.value
        if (fieldType.value == 'number') conditions_num_value.value = Number(value?.value)
        if (Conditions.value == 'range') conditions_num_max_value.value = Number(maxValue?.value)
       
    }
}


onMounted(() => {
    getDataListFunc()
})

// 验证
const verification=():Boolean=>{

if(!property_en.value||!conditions.value){
    return true
}

if((!conditions_num_value.value&&conditions_num_value.value!==0)&&((property_type.value == PropertyTypes[1] && number_input_types.includes(conditions.value))||(property_type.value == PropertyTypes[1] && range_input_types.includes(conditions.value)))){
    return true
}

if((property_type.value == PropertyTypes[0] && string_input_types.includes(conditions.value))&&!conditions_str_value.value){
    return true
}
if((property_type.value == PropertyTypes[1] && range_input_types.includes(conditions.value))&&(!conditions_num_max_value.value&&conditions_num_max_value.value!==0)){
    return true
}

return false
}
defineExpose({
    verification
})

</script>

<style lang='less' scoped>
.event-group-screen {
    margin-bottom: 10px;

    &:last-child {
        margin-bottom: 0;
    }
}
</style>