<!-- 选择事件分析的条件 -->
<template>
  <div class="event-container-item">
    <div class="content">
      <span style="margin-right: 10px;">后续行为 </span>
      <a-cascader :default-value="eventEn + '-' + isVirtual" @change="conditionSelectChangeFunc" :options="event_list"
        placeholder="请选择" style="width: 260px;" />
      <a-select v-if="propertyEnList.length > 0" allowClear @clear="clearEventProperty" v-model="propertyEn"
        :style="{ width: '120px' }" placeholder="不选择属性" @change="propertyEventSelectChangeFunc">
        <template v-for="(item, index) in propertyEnList" :key="index">
          <a-option :value="item.nameEn">{{ item.nameCn }}</a-option>
        </template>
      </a-select>
      <span style="padding: 0 10px;">的</span>
      <a-select v-model="calcType" :style="{ width: '120px' }" placeholder="请选择" @change="propertySelectChangeFunc">
        <template v-for="(item, index) in calc_global_types" :key="index">
          <a-option :value="item.value">{{ item.label }}</a-option>
        </template>
      </a-select>
      <a-button type="text" style="margin-left:5px">
        <icon-delete size="16" @click="emits('on-delete', props.index)" />
      </a-button>

    </div>
  </div>
</template>

<script lang='ts' setup>
import { getGroupEventList, getPropertyList } from "_apis/analysis";
import type { EventGroupData, SelectPropertyParams, PropertyListRequest, PropertyList } from "_types/analysis";
import type { CascaderFieldNames, CascaderOption } from "@arco-design/web-vue";
import eventAnalysisStore from '@/stores/useEventList'
import { storeToRefs } from 'pinia'
import { Ref } from 'Vue'
import { Prop } from "vue";
// props
const props = defineProps<{
  index: number
  data: SelectPropertyParams
}>()
const emits = defineEmits<{
  (event: "update:data", data: SelectPropertyParams): void
  (event: "on-delete", index: number): void
}>()
// console.log(props.data)
let { eventEn, calcType, isVirtual, propertyEn } = toRefs(props.data)
const Store = eventAnalysisStore()
// let event_list = reactive<Array<CascaderOption>>([]);
// let {event_list}:{event_list:Ref<Array<CascaderOption>>} = storeToRefs(Store)

let event_list = computed(() => {
  return Store.event_list
})



let propertyEnList = reactive<PropertyList>([]);
let calc_default_types = reactive([
  {
    label: "总次数",
    value: "total",
  },
  {
    label: "用户数量",
    value: "users",
  }
])
let calc_number_types = reactive([
  {
    label: "总和",
    value: "sum",
  },
  {
    label: "去重数",
    value: "count",
  },
  {
    label: "均值",
    value: "avg",
  },
  {
    label: "最大值",
    value: "max",
  },
  {
    label: "最小值",
    value: "min",
  },
  {
    label: "人均值",
    value: "peopleAvg",
  }
])
let calc_types = reactive([
  {
    label: "去重数",
    value: "count"
  }
]);
let calc_global_types = reactive<{ label: string, value: string }[]>([]);
let editor_title = ref<boolean>(false);
let event_title = ref<string>(props.data.name || '自定义事件');
let is_virtual = ref<number>(0);
// 获取所有事件


const getPropertyListFunc = (data: {}): void => {
  getPropertyList(data).then(res => {
    propertyEnList.length = 0;
    calc_global_types.length = 0;
    if (res.datas) {
      propertyEnList.push(...res.datas);
      propertyEventSelectChangeFunc(propertyEn ? propertyEn.value : '')
    }
  }).catch(err => {
    console.log(err)
  })
}

// const getDataListFunc = (): void => {
// getGroupEventList().then(res => {
// event_list.length = 0;
// if (res.datas) {
// event_list.push(...parseDatas(res.datas))

// }
// }).catch(err => {

// })
// }
// getDataListFunc()
// 把数据转换成级联选择对应格式
const parseDatas = (datas: EventGroupData): CascaderOption[] => {
  let list: Array<CascaderOption> = [];
  datas.forEach((item, index) => {
    let data: CascaderOption = {
      label: item.name,
      value: index,
    }
    data.children = item.eventDataDtoList.map((childItem): CascaderOption => {
      let child_data: CascaderOption = {
        label: childItem.nameCn,
        value: `${childItem.nameEn}-${childItem.isVirtual}`,
        is_virtual: childItem.isVirtual
      }
      return child_data
    })
    list.push(data)
  })
  return list
}

//属性清除
const clearEventProperty = () => {
  propertyEn.value = '';
  calc_global_types.length = 0;
  calc_global_types.push(...calc_default_types)
  calcType.value = calc_global_types[0].value;
  emits('update:data', { ...props.data, propertyEn: propertyEn.value, calcType: <string>calcType.value })
}

// 选择发生变化
const conditionSelectChangeFunc = (value: unknown) => {
  let [nameEn, isVirtual] = (<string>value).split('-');
  let newName = getenBycn(<string>value, event_list.value)
  event_title.value = newName
  emits('update:data', { ...props.data, eventEn: nameEn, name: newName, isVirtual: Number(isVirtual) })
  getPropertyListFunc({ eventNameEn: nameEn })
  propertyEn.value = '';
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

// 当统计量发生变化
const propertySelectChangeFunc = (value: unknown) => {
  emits('update:data', { ...props.data, calcType: <string>value })
}
// 当属性发生变化
const propertyEventSelectChangeFunc = (value: unknown) => {
  calc_global_types.length = 0;
  calc_global_types.push(...calc_default_types)
  propertyEnList.forEach((item, index) => {
    if (item.nameEn === value) {
      calc_global_types.length = 0;
      if (item.fieldType === "number") {
        calc_global_types.push(...calc_number_types)
      } else {
        calc_global_types.push(...calc_types)
      }
      return;
    }
  })
  let isEq = false;
  calc_global_types.forEach((calcItems, index) => {
    if (calcItems.value === calcType.value) {
      calcType.value = calcItems.value
      isEq = true;
      return;
    }
  })
  if (!isEq) {
    calcType.value = calc_global_types[0].value;
  }
  emits('update:data', { ...props.data, propertyEn: <string>value, calcType: <string>calcType.value })
}
// watch
watch(editor_title, (val) => {
  if (!val) {
    emits('update:data', { ...props.data, name: event_title.value })
  }
})

onMounted(() => {
  getPropertyListFunc({ eventNameEn: eventEn.value })
  // getDataListFunc()
})
</script>

<style lang='less' scoped>
.event-container-item {
  margin-bottom: 10px;

  &>div.title {
    margin-bottom: 10px;
    font-size: 14px;
    color: #1f2d3d;
    font-weight: 500;
    line-height: 26px;

    & .icon {
      color: rgb(197, 80, 63);

      &:hover {
        color: rgb(246, 125, 114);
        cursor: pointer;
      }
    }
  }
}
</style>
