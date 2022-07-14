<template>
    <range-picker
        @change="rangePickerChangeFunc"
        :model-value="[props.startTime, props.endTime]"
        shortcuts-position="left"
        :shortcuts="rangeShortcuts"
        :style="{ width: '300px', marginLeft: '10px' }"
    />
    <div class="chartCont">
        <a-spin  :loading="loading">
            <div class="chartLeft">
                <div v-for="(value,index) in conversionList" class="conversionList" :key="index">
                    <div class="conversion" :style="{backgroundColor:currentStep==index?'#EE5A46':'#f2f3f5',color:currentStep==index?'#fff':'unset'}" @click="changeStep(index)">
                        <p v-if="!index">总转化率</p>
                        {{value.scale}}%
                    </div>
                    <div class="conversionPeopleNumber">
                        <p>第{{index+1}}步</p>
                        <p>{{currentFunnel.funnelStepDataEntityList[index]?.name}}</p>
                        <p>{{value.num}}人</p>
                    </div>
                </div>
            </div>
        </a-spin>
        <div class="chartRight">
            <chart class="chart" :loading="loading" v-bind="$attrs" ref="my_chart" />
        </div>
    </div>

</template>

<script lang="ts" setup>
import type { CalculateData ,AddfunnelData} from "_types/analysis";
import { Ref } from "vue";
import constant from "_hooks/constant";
const props = defineProps<{
    startTime: string
    endTime: string
    dataList?: CalculateData[]
    conversionList:Array<{
        num: number, scale: number
    }>
    currentFunnel:AddfunnelData,
    option:{grid:[{width:string}]}
    currentStep:Ref<number>
    loading:boolean
}>();

var {conversionList,currentFunnel,option,currentStep} = props
var {currentStep,loading } = toRefs(props)
// console.log(currentStep)
const { rangeShortcuts} = constant();
const emits = defineEmits<{
    (event: "update:startTime", data: string): void
    (event: "update:endTime", data: string): void
    (event: "update:timeGranularity", data: string): void
    (event: "changeStep", data: number): void
    (event:"update:currentStep", data: number): void
}>()

watch(option,(e)=>{
    option.grid[0].width = "70%";
    my_chart.value.init(e)
})

const rangePickerChangeFunc = (value: unknown): void => {
    emits('update:currentStep',0)
    emits('update:startTime', (<string[]>value)[0])
    emits('update:endTime', (<string[]>value)[1])
}


const my_chart = ref();

    // console.log(option)
    // my_chart.value.init(option)

const changeStep = (e:number)=>{
    emits('update:currentStep',e)
}

</script>

<style lang="less" scoped>
.chart {
    width: 940px !important;
}
.chartLeft{
    width: 300px;
}
.chartRight{
    display: flex;
    justify-content: left;
    flex: 1;
}
.conversionList{
display: flex;
    flex-direction: column;
    align-items: center;
}
.conversion {
    display:flex;
    flex-direction: column;
    align-items: center;
    height: 80px;
    justify-content: space-around;
    // background-color: #f2f3f5;
    margin: 10px;
    width: 100px;
}
.conversionPeopleNumber{
    background-color: #f2f3f5;
    width: 250px;
    padding: 5px;
    display: flex;
    justify-content: space-around;

}
p{ margin: 0;}
.chartCont{
    display: flex;
}
</style>
