<template>
  <a-drawer :visible="visible" @cancel="cancel" @ok="sure" unmountOnClose width="500px">
    <template #title>
      {{ title }}
    </template>
    <div>
      <subTitle title="基本信息" />
      <div class="subtitle">
        <p>漏斗名称:</p>
        <a-input :style="{ width: '320px' }" placeholder="请输入漏斗名称" v-model="data.name" allow-clear />
      </div>
      <div class="subtitle">
        <p>窗口期:</p>
        <a-select :style="{ width: '320px' }" placeholder="请选择窗口期" :options="options" v-model="data.windowPeriod"
          allow-clear />
      </div>
      <subTitle title="配置漏斗步骤" />
      <div style="padding-left: 15px">
        <template v-for="(value, index) in data.funnelStepDataEntityList" :key="index">
          <funnelStep :data="value" :index="index + 1" @deleteStep="deleteStep(index)" />
        </template>
        <a-button type="primary" style="width: 260px; margin-top: 20px" long @click="funnelStepData">
          <icon-plus />
          添加步骤
        </a-button>
      </div>
    </div>
  </a-drawer>
</template>
<script lang="ts" setup>
import type { AddfunnelData } from "_types/analysis";
// import dataChartVue from "../event/data-chart.vue";
import useMessage from "_hooks/useMessage";
import { saveOrUpdateFunnel } from "_apis/analysis";
import { Ref } from "vue";
const { MessageError, MessageSuccess } = useMessage();

let title: Ref<string> = ref('')

var props = defineProps<{
  visible: boolean;
  data: AddfunnelData;
  isUpdate: boolean
}>();
var { visible } = toRefs(props);
var { data } = props
const emits = defineEmits<{
  (event: "update:visible", status: boolean): void;
  (event: "reset"): void;
  (event: "addOver"): void;
  (event: "update:isUpdate", status: boolean): void;
}>();

watch(() => data.id, (val: number | undefined) => {
  changeTitle(data)
});
changeTitle(data)

function changeTitle(val: AddfunnelData): void {
  if (val.id) {
    title.value = "更新漏斗";
  } else {
    title.value = "创建漏斗";
  }
}

// 删除步骤
const deleteStep = (index: number) => {
  (data.funnelStepDataEntityList as any).splice(index, 1)
}

// 时间窗口
const options = [
  {
    label: "当天",
    value: 0,
  },
  {
    label: "3天",
    value: 3,
  },
  {
    label: "7天",
    value: 7,
  },
  {
    label: "30天",
    value: 30,
  },
];
// 添加步骤
const funnelStepData = (): void => {
  (data.funnelStepDataEntityList as any).push({ eventEn: "visit_xn", name: "全站流量", isVirtual: 1 });
};

// 抽屉方法
const cancel = (): void => {
  emits("update:visible", false);
  emits("reset");
};
const sure = (): void => {
  if (!data.name) {
    MessageError("请填写漏斗名称");
    return;
  }

  (data.funnelStepDataEntityList as any).forEach((value: any, index: any) => {
    value.sort = index;
  });

  saveOrUpdateFunnel(data).then((res) => {
    if (data.id) {
      MessageSuccess("更新成功");
    } else {
      MessageSuccess("添加成功");
      emits("update:isUpdate", true);
    }
    emits("update:visible", false);
    emits("reset");
    emits("addOver");

  })

};
</script>

<style lang="less" scoped>
.subtitle {
  display: flex;
  align-items: center;

  &>p:nth-of-type(1) {
    width: 100px;
    text-align: right;
    padding-right: 10px;
  }
}
</style>
