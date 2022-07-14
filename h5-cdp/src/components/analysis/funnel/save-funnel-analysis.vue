<template>
    <a-modal v-bind="$attrs" title-align="start" :unmount-on-close="true" :closable="false">
        <template #title>
            添加到概览
        </template>
        <a-form :rules="form_rules" :model="funnel_analysis_data" label-align="left" :label-col-props="{ span: 4 }">
            <a-form-item field="name" label="名称">
                <a-input v-model="funnel_analysis_data.name" placeholder="请输入"></a-input>
            </a-form-item>
            <a-form-item field="overviewId" label="选择概览">
                <a-select v-model="funnel_analysis_data.overviewId" placeholder="请选择" :disabled="!!modelId">
                    <template v-for="(item, index) in overview_list" :key="index">
                        <a-option :value="item.id">{{ item.name }}</a-option>
                    </template>
                </a-select>
            </a-form-item>
        </a-form>
        <template #footer>
            <a-button @click="cancelFunc">取消</a-button>
            <a-button :loading="save_loading" type="primary" @click="confrimFunc">确定</a-button>
        </template>
    </a-modal>
</template>

<script lang="ts" setup>
import { saveOrUpdate } from "_apis/analysis"
import type { OverviewItem } from "_types/overview"
import { getOverviewList } from "_apis/overview"
import type {
  WherePropertyParams,
  CalculateDataRequest
} from "_types/analysis"
import useMessage from "_hooks/useMessage"
import router from "@/routers"
const { MessageSuccess, MessageError } = useMessage()

const props = defineProps<{
    modelId:number|null
}>()
var {modelId } = toRefs(props)


const emits = defineEmits<{
    (event: "update:visible", data: boolean): void
}>();

const route = useRoute()

let funnel_analysis_data = reactive<{
    name: string,
    overviewId: number,
    type:number,
    timeGranularity:string,
    startTime:string,
    endTime:string,
    wherePropertyParams:Array<WherePropertyParams>,
    funnelId:number
    id?:number
}>({
    name: "",
    overviewId: NaN,
    type:0,
    timeGranularity:'',
    startTime:'',
    endTime:'',
    wherePropertyParams:[],
    funnelId:NaN
})

// var {overViewId,oldName} = toRefs(props)
//
// watch(overViewId,()=>{
//     if(props.overViewId){
//         funnel_analysis_data.overviewId = props.overviewId
//     }
// })
// watch(oldName,()=>{
//     if(props.oldName){
//         funnel_analysis_data.name = props.oldName
//     }
// })

const form_rules = {
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

let save_loading = ref<boolean>(false)


const resetAnalysis_data =() =>{
    Object.assign(funnel_analysis_data,{
    name: "",
    type:0,
    timeGranularity:'',
    startTime:'',
    endTime:'',
    wherePropertyParams:[],
    funnelId:NaN
})
}

const  cancelFunc = ()=>{

    resetAnalysis_data()

    emits('update:visible', false)
}



const confrimFunc = ()=>{
    // console.log(funnel_analysis_data)
    if(funnel_analysis_data.overviewId!==undefined&&funnel_analysis_data.funnelId){

        if(modelId.value){
            funnel_analysis_data.id = modelId.value
        }
        if (funnel_analysis_data.name == '') {
            MessageError("名称不可为空");
        } else {
            saveOrUpdate(funnel_analysis_data).then(res=>{
                MessageSuccess((modelId.value?'更新':'添加') + '成功')
                emits('update:visible', false)
                router.push('/overview/'+funnel_analysis_data.overviewId)
                resetAnalysis_data()
            })
        }
        }
        
}


let overview_list = reactive<OverviewItem[]>([])

const getDataListFunc = (): void => {
    getOverviewList({}).then(res => {
        overview_list.length = 0
        if (res.datas) {
            overview_list.push(...res.datas)
            // debugger
            if (route.query.oi) {
                funnel_analysis_data.overviewId = Number(route.query.oi)
            }
        }
    }).catch(err => { })
}

onMounted(() => {
  getDataListFunc()
})



// 父级传递一些必要参数
const init = (data: CalculateDataRequest) => {
    Object.assign(funnel_analysis_data,data)
}

defineExpose({
    init
})



</script>
