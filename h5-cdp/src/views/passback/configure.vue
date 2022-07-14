<template>
  <div class="strstegies-container">
    <div class="header">{{ page_title }}</div>
    <div class="strstegies-content">
      <div class="strstegies-top">
        <a-button type="primary" @click="go">添加回传管理事件</a-button>
      </div>
      <div class="strstegies-table">
        <a-table :columns="columns" :data="strstegies_table_data">
          <template #optional="{ record }">
            <a-button type="text" @click="go(record)">编辑</a-button>
          </template>
          <template #optional1="{ record }">
            <a-switch v-model="record.switch" @change="changeSwitch(record.switch)" />
          </template>
        </a-table>
      </div>
    </div>
  </div>
  <a-modal v-model:visible="show" :width="800">
    <template #title>
      回传策略设置
    </template>
    <a-form ref="formRef" :model="form" :style="{ width: '700px' }">
      <a-form-item field="name" label="*回传策略名称">
        <a-input v-model="name" placeholder="请输入回传策略名称" />
      </a-form-item>
      <a-form-item field="section" label="*回传字段类型">
        <a-select v-model="mode" placeholder="请选择回传字段类型">
          <a-option v-for="item in form.mode" :key="item.value" :value="item.value" :label="item.label"></a-option>
        </a-select>
      </a-form-item>
      <a-form-item field="radio" label="*筛选规则">
        <a-radio-group v-model="form.screenRule" @change="screenRules(form.screenRule)">
          <a-radio value="0">不需要筛选</a-radio>
          <a-radio value="1">需要筛选</a-radio>
        </a-radio-group>
      </a-form-item>
      <a-form-item field="section" v-if="form.screenRule == '1'">
        <a-select v-model="rule1" placeholder="请选择">
          <a-option v-for="item in form.rule1" :key="item.value" :value="item.value" :label="item.label"></a-option>
        </a-select>
        <a-select v-model="rule2" placeholder="请选择">
          <a-option v-for="item in form.rule2" :key="item.value" :value="item.value" :label="item.label"></a-option>
        </a-select>
        <a-input v-model="rule3" placeholder="请输入数值" />
      </a-form-item>
      <a-form-item field="section" label="*回传规则">
        <a-select v-model="passbackRule" placeholder="请选择回传规则">
          <a-option v-for="item in form.passbackRule" :key="item.value" :value="item.value" :label="item.label">
          </a-option>
        </a-select>
      </a-form-item>
      <a-form-item field="name">
        <a-input v-model="passbackRuleValue" v-if="passbackRule == '0'" placeholder="请输入数值" />
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="cancelFunc">关闭</a-button>
      <a-button @click="sumbitFunc" type="primary">确定</a-button>
    </template>
  </a-modal>
</template>

<script lang='ts' setup>
import useDate from "_hooks/useDate";
import useMessage from "_hooks/useMessage";
import type {
  DetailData
} from "_types/analysis";

const route = useRoute();
const router = useRouter();
const { MessageError } = useMessage();
// 公共数据
const page_title: string = "回传管理";
let name = ref();
let mode = ref();
let rule1 = ref();
let rule2 = ref();
let rule3 = ref();
let screenRule = ref();
let passbackRule = ref();
let passbackRuleValue = ref();

// 表头
const columns = [{
  title: '回传事件名称',
  dataIndex: 'name',
}, {
  title: '对应媒体侧回传事件',
  dataIndex: 'backEvent',
}, {
  title: '对应采集方式',
  dataIndex: 'collectEvent',
}, {
  title: '回传方式',
  dataIndex: 'backWay',
}, {
  title: '回传频次',
  dataIndex: 'frequency',
}, {
  title: '回传窗口期',
  dataIndex: 'windowPeriod',
}, {
  title: '回传附加属性数量',
  dataIndex: 'number',
}, {
  title: '订单支付金额',
  dataIndex: 'amount',
}, {
  title: '订单ID',
  dataIndex: 'id',
}, {
  title: '订单类型',
  dataIndex: 'type',
}, {
  title: '操作',
  slotName: 'optional'
}, {
  title: '开启状态',
  slotName: 'optional1'
}];
let strstegies_table_data = ref([{
  key: '1',
  name: '字节支付订单-web',
  backEvent: '订单支付',
  backWay: '实时回传',
  collectEvent: 'Web订单支付',
  frequency: '仅首次回传',
  windowPeriod: '24小时',
  number: '3',
  amount: '倍增',
  id: '不处理',
  type: '不处理',
  switch: true,
}, {
  key: '2',
  name: '字节支付订单-小程序',
  backEvent: '订单支付',
  backWay: '实时回传',
  collectEvent: 'App订单支付',
  frequency: '仅首次回传',
  windowPeriod: '24小时',
  number: '2',
  amount: '倍增',
  id: '不处理',
  type: '',
  switch: true,
}, {
  key: '3',
  name: '注册',
  backEvent: '注册',
  backWay: '定时回传',
  collectEvent: '用户注册',
  frequency: '每次回传',
  windowPeriod: '48小时',
  number: '0',
  amount: '',
  id: '',
  type: '',
  switch: false,
}, {
  key: '4',
  name: '激活',
  backEvent: '激活',
  backWay: '实时回传',
  collectEvent: '激活',
  frequency: '仅首次回传',
  windowPeriod: '24小时',
  number: '0',
  amount: '',
  id: '',
  type: '',
  switch: false,
}, {
  key: '5',
  name: '巨量表单提交',
  backEvent: '表单提交',
  backWay: '实时回传',
  collectEvent: '表单提交',
  frequency: '仅首次回传',
  windowPeriod: '24小时',
  number: '0',
  amount: '',
  id: '',
  type: '',
  switch: false,
}]);
// let strstegies_data = reactive<DetailData>({
//   key: '',
//   name: '',
//   time: '',
//   mode: '',
//   status: '',
//   detail: ''
// });
const reset = (): void => {
  name.value = ''
  mode.value = ''
  rule1.value = ''
  rule2.value = ''
  rule3.value = ''
  screenRule.value = ''
  passbackRule.value = ''
  passbackRuleValue.value = ''
}
const editStrstegies = (value: any): void => {
  console.log('编辑 跳出弹窗', value);
  show.value = true;
  name.value = value.name
  mode.value = value.mode
  rule1.value = value.rule1
  rule2.value = value.rule2
  rule3.value = value.rule3
  screenRule.value = value.screenRule
  passbackRule.value = value.passbackRule
  passbackRuleValue.value = value.passbackRuleValue
}
const deleteStrstegies = (value: unknown): void => {
  console.log('删除 二次提醒');
}
const show = ref<boolean>(false);
const sumbitFunc = () => {
  console.log('name', name.value, 'mode', mode.value, 'rule1', rule1.value, 'rule2', rule2.value, 'rule3', rule3.value, 'screenRule', screenRule.value,);
  if (name.value == '' || name.value == undefined) {
    MessageError('请输入回传策略名称');
  } else if (mode.value == undefined) {
    MessageError('请选择回传字段类型');
  } else if (passbackRule.value == undefined) {
    MessageError('请选择回传字段规则');
  } else if (passbackRuleValue.value == undefined) {
    MessageError('请输入数值');
  } else {
    show.value = false;
  }
};
const cancelFunc = () => {
  show.value = false;
}
let form = reactive({
  name: '',
  mode: [{
    label: '数值',
    value: 0
  }, {
    label: '字符',
    value: 1
  }, {
    label: '布尔',
    value: 2
  }, {
    label: '枚举',
    value: 3
  }],
  screenRule: '0',
  rule1: [{
    label: '访问时长',
    value: 0
  }, {
    label: '订单金额',
    value: 1
  }],
  rule2: [{
    label: '等于',
    value: 0
  }, {
    label: '大于',
    value: 1
  }, {
    label: '小于',
    value: 2
  }],
  rule3: '',
  passbackRule: [{
    label: '百分比',
    value: 0
  },],
  passbackRuleValue: '',
});

const screenRules = (value: any) => {
  console.log('筛选规则', value);
  screenRule.value = value
}
const addStrategies = () => {
  reset()
  show.value = true;
}
const changeSwitch = (value: boolean) => {
  value = !value;
  console.log(value);
}
const go = (value: any) => {
  console.log(value);
  
  router.push({
    path: '/passback/events',
    params: JSON.parse(JSON.stringify(value))
  });
}
</script>

<style lang='less' scoped>
.header {
  font-size: 22px;
  font-weight: bold;
  flex: auto;
  margin-bottom: 10px;
}

.strstegies-container {
  height: 100%;
  box-sizing: border-box;
  padding: 16px 0 16px 16px;
  overflow: hidden;

  &>.strstegies-content {
    height: calc(100% - 62px);
    width: 100%;
    overflow: hidden;

    &>.strstegies-top {
      margin: 10px 0;
    }

    &>.strstegies-table {
      width: 99%;
    }
  }
}
</style>