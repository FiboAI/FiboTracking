<!-- 选择按什么查看 -->
<template>
    <a-select :unmount-on-close="true" :style="{ width: '120px' }" placeholder="请选择" @change="propertyEnChangeFunc">
        <template #trigger>
            <a-button size="mini">
                <icon-plus :size="12" />
            </a-button>
        </template>
        <template v-for="item in property_list">
            <a-option :value="item.nameEn">{{ item.nameCn }}</a-option>
        </template>
    </a-select>
</template>

<script lang='ts' setup>
import { getPropertyList } from "_apis/analysis";
import type { PropertyListRequest, PropertyList } from "_types/analysis";

const emits = defineEmits<{
    (event: "on-add", name: string,text:string): void
}>()

const property_list = reactive<PropertyList>([])

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

const propertyEnChangeFunc = (value?: unknown): void => {
  let text = "";
    property_list.forEach(item=>{
      if(item.nameEn === value){
        text=item.nameCn
      }
    })
    emits('on-add', <string>value, <string>text);
}

onMounted(() => {
    getDataListFunc();
})
</script>

<style lang='less' scoped>
:deep(.arco-trigger-popup) {
    width: 120px !important;
}
</style>
