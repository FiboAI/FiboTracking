<!-- 数据图表 -->
<template>
  <div class="event-chart modular" ref="chart_container">
    <!-- <div class="tools">
      <a-select :model-value="props.timeGranularity" placeholder="请选择" :style="{ width: '140px' }"
        @change="timeGranularityChangeFunc">
        <template v-for="(item, index) in time_granularity_list" :key="index">
          <a-option :value="item.value">{{ item.label }}</a-option>
        </template>
      </a-select>
      <range-picker @change="rangePickerChangeFunc" :model-value="[props.startTime, props.endTime]"
        :style="{ width: '300px', marginLeft: '10px' }" />
    </div> -->
    <chart v-bind="$attrs" ref="my_chart" />
  </div>
</template>

<script lang='ts' setup>
import type { CalculateData } from "_types/analysis";
import { GroupPropertyParams, SelectPropertyParams } from "_types/analysis";
const props = defineProps<{
  startTime: string
  endTime: string
  timeGranularity: string
  dataList: CalculateData[]
  times: Array<string>
  selectViewNames: Array<SelectPropertyParams>
  groupViewNames: Array<GroupPropertyParams>
  columns: Array<string>
  timeValue: number
}>();
// console.log('props', JSON.parse(JSON.stringify(props)));
// console.log('dataList', JSON.parse(JSON.stringify(props.dataList)));

const emits = defineEmits<{
  (event: "update:startTime", data: string): void
  (event: "update:endTime", data: string): void
  (event: "update:timeGranularity", data: string): void
}>()

const my_chart = ref();
const chart_container = ref();

// console.log(props.selectViewNames)
// console.log(props.groupViewNames)

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
let urlCur = ref<string>()
watch(props.dataList, (value: any) => {
  // console.log(JSON.parse(JSON.stringify(value[0])));
  let selectBy = props.selectViewNames.map(x => { return x.name });
  let groupByCn = props.groupViewNames.map(x => { return x.propertyCn });
  // console.log(props.selectViewNames)
  // console.log(selectBy);

  let serirs: any[] = [];
  let serirs_name: any[] = [];
  let serirs_map = new Map<string, Map<string, string>>();

  (value[0]).forEach((item: any) => {
    let Ydata: any[] = [];
    Ydata.length = 0
    for (let i = 0; i <= props.timeValue; i++) {
      Ydata.push(item["morrow" + i] as number)
    }
    serirs.push({ name: item.groupStr, type: 'line', data: Ydata })
    // let groupStr = "";
    // groupByCn.forEach(function (g, i) {
    //   // groupStr += item[g] + "-";
    //   groupStr += g + "-";
    // })
    // console.log(groupStr)

    // selectBy.forEach(function (s, i) {
    //   let fieldName = groupStr + s;
    //   let datesMap = new Map<string, string>();
    //   if (serirs_map.has(fieldName)) {
    //     datesMap = serirs_map.get(fieldName) as Map<string, string>;
    //   }
    //   datesMap.set(item.dates as string, item[s] as string);
    //   serirs_map.set(fieldName, datesMap);
    //   // console.log(fieldName, datesMap);

    // })serirs

  })
  serirs.forEach(item => {
    serirs_name.push(item.name)
  })

  // console.log(...serirs);
  // console.log(serirs_name);

  // serirs_map.forEach((value, key) => {
  //   // console.log(value);
  //   let seriesObject: { name: string, type: string, data: string[] } = {
  //     name: key,
  //     type: 'line',
  //     data: []
  //   };
  //   // let fieldValue: any[] = [];
  //   // props.times.forEach(function (time) {
  //   //   if (value.has(time)) {
  //   //     fieldValue.push(value.get(time));
  //   //   } else {
  //   //     fieldValue.push("0");
  //   //   }
  //   // })
  //   seriesObject.data = Ydata
  //   serirs.push(seriesObject);
  //   console.log(serirs);
  // })

  let option = {
    legend: {
      width: '30%',
      type: 'scroll',
      orient: 'vertical',
      align: 'left',
      top: 10,
      right: 0,
      data: serirs_name
    },
    tooltip: {
      trigger: 'axis'
    },
    toolbox: {
      show: true,
      top: "10px",
      right: "30%",
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
      data: props.columns
    },
    yAxis: {
      type: 'value'
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
