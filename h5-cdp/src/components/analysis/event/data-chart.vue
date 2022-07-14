<!-- 数据图表 -->
<template>
    <div class="event-chart modular" ref="chart_container">
        <div class="tools">
            <a-select :model-value="props.timeGranularity" placeholder="请选择" :style="{ width: '140px' }"
                @change="timeGranularityChangeFunc">
                <template v-for="(item, index) in time_granularity_list" :key="index">
                    <a-option :value="item.value">{{ item.label }}</a-option>
                </template>
            </a-select>
            <range-picker @change="rangePickerChangeFunc" :model-value="[props.startTime, props.endTime]"
                :style="{ width: '300px', marginLeft: '10px' }" shortcuts-position="left"
                          :shortcuts="rangeShortcuts"/>
        </div>
        <chart v-bind="$attrs" ref="my_chart" />
    </div>
</template>

<script lang='ts' setup>
import ComputedChart from "_workers/computed-line-chart?worker"
import type { CalculateData } from "_types/analysis";
import {GroupPropertyParams, SelectPropertyParams} from "_types/analysis";
import useDate from "_hooks/useDate";
import constant from "_hooks/constant";
const props = defineProps<{
    startTime: string
    endTime: string
    timeGranularity: string
    dataList: CalculateData[]
    times:Array<string>
    selectViewNames:Array<SelectPropertyParams>
    groupViewNames:Array<GroupPropertyParams>
}>();
// console.log(props.dataList)

const emits = defineEmits<{
    (event: "update:startTime", data: string): void
    (event: "update:endTime", data: string): void
    (event: "update:timeGranularity", data: string): void
}>()

const my_chart = ref();

const chart_container = ref()
// console.log(props.selectViewNames)
// console.log(props.groupViewNames)
const { getTime,getAddTime} = useDate();
const { rangeShortcuts} = constant();
const time_granularity_list = [
    {
        label: "天",
        value: "day"
    },
    // {
    //     label: "周",
    //     value: "week"
    // },
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

watch(props.dataList, (value) => {
  let selectBy = props.selectViewNames.map(x => {return x.name});
  let groupByCn =props.groupViewNames.map(x => {return x.propertyCn});

  let serirs: any[] = [];
  let serirs_map = new Map<string, Map<string, string>>();
    // console.log(value);
    (value).forEach((item) => {
      let groupStr = "";
      groupByCn.forEach(function(g,i){
        groupStr+=item[g]+"-";
      })
      selectBy.forEach(function(s,i){
        let fieldName = groupStr+s;
        let datesMap=new Map<string, string>();
        if(serirs_map.has(fieldName)){
          datesMap = serirs_map.get(fieldName) as Map<string, string>;
        }
        datesMap.set(item.dates as string,item[s] as string);

        serirs_map.set(fieldName,datesMap);
      })
    })

  // console.log(serirs_map);

  serirs_map.forEach(function(value,key){
    let seriesObject:{name:string,type:string,data:string[]} = {
      name: key,
      type: 'line',
      data:[]
    };
    let fieldValue : any[] = [];
    props.times.forEach(function(time){
      if(value.has(time)){
        fieldValue.push(value.get(time));
      }else{
        fieldValue.push("0");
      }
    })
    seriesObject.data=fieldValue
    // console.log(fieldValue, seriesObject);

    serirs.push(seriesObject);
  })

    let option = {
      legend: {
        width:'30%',
        type: 'scroll',
        orient: 'vertical',
        align:'left',
        right: 0,
        data: [...serirs_map.keys()]
      },
        tooltip: {
            trigger: 'axis'
        },
        toolbox: {
            show: true,
            top: "10px",
            right:"30%",
            feature: {
                magicType: {
                    type: ['line', 'bar'],
                    title: {
                        line: "切换为折线图",
                        bar: "切换为柱状图"
                    }
                }
            }
        },
        xAxis: {
            type: 'category',
            data: props.times
        },
        yAxis: {
            type: 'value',
          axisLabel: {
            show:true,
            margin: 2,
            formatter: function (value:number, index:number) {
              //   console.log(index)
              let valueStr = "";
              if (value >= 10000 && value < 10000000) {
                valueStr = value / 10000 + "万";
              } else if (value >= 10000000) {
                valueStr = value / 10000000 + "千万";
              } else{
                valueStr=value.toString()
              }
              return valueStr;
            }
          }
        },
        grid: [
            {
                left: '50px',
                top: '50px',
                bottom: '50px',
                width: "70%",
                height: '300px'
            }
        ],
        series: serirs
    };
    // console.log(option)
    my_chart.value.init(option)

})

const rangePickerChangeFunc = (value: unknown): void => {
    emits('update:startTime', (<string[]>value)[0])
    emits('update:endTime', (<string[]>value)[1])
}

const timeGranularityChangeFunc = (value: unknown): void => {
    emits('update:timeGranularity', <string>value)
}

</script>

<style lang='less' scoped>
</style>
