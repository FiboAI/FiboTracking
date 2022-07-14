<template>
    <div>
        <div class="subtitle">
            <p class="subtitleIndex">{{ index }}</p>
            <p class="subtitleText"> {{ data.name }}</p>
            <a-button type="text" @click="deleteFunnel" style="margin-left:20px" size="small">
                <template #icon>
                    <icon-delete />
                </template>
            </a-button>
        </div>
        <div>
            <a-cascader @change="conditionSelectChangeFunc" :default-value="data.eventEn"
                :options="event_list_NoVirtual" placeholder="请选择" style="width: 260px;" />

            <!-- {{data.eventEn}} -->
        </div>


    </div>
</template>

<script lang="ts" setup>
import type { funnelStepDataEntity } from '_types/analysis'
import type { CascaderOption } from "@arco-design/web-vue";
import eventAnalysisStore from '@/stores/useEventList'
import { storeToRefs } from 'pinia'
import { Ref } from 'vue'
const { data, index } = defineProps<{
    data: funnelStepDataEntity,
    index: number
}>()
const emits = defineEmits<{
    (event: "update:data", data: funnelStepDataEntity): void;
    (event: 'deleteStep'): void
}>();

// let lastname = getenBycn(data.eventEn,event_list) 
const Store = eventAnalysisStore()
let { event_list_NoVirtual }: { event_list_NoVirtual: Ref<Array<CascaderOption>> } = storeToRefs(Store)



const conditionSelectChangeFunc = (e: any | string | number | (string | number | (string | number)[])[] | undefined) => {
    if (typeof e == 'string') {
        data.eventEn = e
        // data.isVirtual = Number(e.split('-')[1])
        data.name = getenBycn(<string>e, event_list_NoVirtual.value)
    }
    // emits("update:data",data)
}


// 删除步骤
const deleteFunnel = () => {
    emits('deleteStep')
}

// 通过en找cn
function getenBycn(en: string, arr: Array<CascaderOption>): string {

    let cn = ''
    arr.forEach(value => {
        if (value.value == en) {
            cn = value.label || ''
        } else if (value.children && value.children.length) {
            let tempcn = getenBycn(en, value.children)
            if (tempcn) {
                cn = tempcn
            }
        }
    })

    if (cn) {
        return cn
    }
    return ''


}




</script>

<style lang="less" scoped>
.subtitle {
    font-size: 14px;
    display: flex;
    align-items: center;

    &>.subtitleIndex {
        background-color: #EE5A46;
        border-radius: 50%;
        font-weight: bold;
        width: 30px;
        height: 30px;
        line-height: 30px;
        text-align: center;
        color: #fff;
    }

    &>.subtitleText {
        font-size: 14px;
        margin-left: 10px;
        // font-weight: bold;

    }

}
</style>