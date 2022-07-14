<!-- 用来选择选择 -->
<template>
    <a-select size="mini" v-model="property_en" :style="{ width: '120px' }" placeholder="请选择"
        @change="propertyEnChangeFunc">
        <template #trigger>
            <a-tag closable :style="{ marginRight: '10px' }" @close="emits('on-delete', props.index)">{{ property_cn }}
            </a-tag>
        </template>
        <template v-for="item in property_list">
            <a-option :value="item.nameEn">{{ item.nameCn }}</a-option>
        </template>
    </a-select>
</template>

<script lang='ts' setup>
import { getPropertyList } from "_apis/analysis";
import type { PropertyListRequest, PropertyList } from "_types/analysis";

const props = defineProps<{
    propertyEn: string
    index: number
}>()

const emits = defineEmits<{
    (event: "update:propertyEn", str: string): void
    (event: "on-delete", index: number): void
}>()

const property_list = reactive<PropertyList>([])

let property_en = ref<string | undefined>(props.propertyEn || '')

let property_cn = computed(() => {
    let str = ''
    let filters = property_list.filter((item) => {
        if (item.nameEn == property_en.value) {
            return item
        }
    })
    if (filters.length > 0) {
        str = filters[0].nameCn
    }
    return str
})

const getDataListFunc = (): void => {
    let params: PropertyListRequest = {
        isGroupView: 1
    };
    getPropertyList(params).then(res => {
        property_list.length = 0;
        if (res.datas) {
            property_list.push(...res.datas)
        }
    }).catch(err => {

    })
}

const propertyEnChangeFunc = (value: unknown): void => {
    emits('update:propertyEn', <string>value)
}

onMounted(() => {
    getDataListFunc();
})
</script>

<style lang='less' scoped>
</style>