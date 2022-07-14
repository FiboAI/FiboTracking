<template>
  <div class="strstegies-container">
    <div class="header">{{ page_title }}</div>
    <div class="strstegies-content">
      <div class="strstegies-top">
        <a-button type="primary" @click="addStrategies">添加回传策略</a-button>
      </div>
      <div class="strstegies-table">
        <a-table :columns="columns" :data="strstegies_table_data">
          <template #optional="{ record }">
            <a-button type="text" @click="editStrstegies(record)">编辑</a-button>
            <a-button type="text" @click="deleteStrstegies(record)">删除</a-button>
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
const { getTime, getAddTime } = useDate();
const { MessageError } = useMessage();
// 公共数据
const page_title: string = "回传策略";
let name = ref();
let mode = ref();
let rule1 = ref();
let rule2 = ref();
let rule3 = ref();
let screenRule = ref();
let passbackRule = ref();
let passbackRuleValue = ref();

// 
const columns = [{
  title: '回传策略名称',
  dataIndex: 'name',
}, {
  title: '类型',
  dataIndex: 'modeLabel',
}, {
  title: '筛选规则',
  dataIndex: 'screenRule',
}, {
  title: '回传规则',
  dataIndex: 'passbackRule',
}, {
  title: '操作',
  slotName: 'optional'
}];
let strstegies_table_data = [{
  key: '1',
  name: '字节支付订单-web',
  mode: 0,
  modeLabel: '数值',
  time: '2022-06-21 23:43',
  status: '成功',
  rule1: 0,
  rule2: 0,
  rule3: '0',
  screenRule: 0,
  passbackRule: 0,
  passbackRuleValue: '0',
}, {
  key: '2',
  name: '字节支付订单-小程序',
  mode: 0,
  modeLabel: '数值',
  time: '2022-06-21 23:45',
  status: '成功',
  rule1: 0,
  rule2: 0,
  rule3: '0',
  screenRule: 0,
  passbackRule: 0,
  passbackRuleValue: '0',
}, {
  key: '3',
  name: '注册回传策略',
  mode: 2,
  modeLabel: '布尔',
  time: '2022-06-21 23:10',
  status: '失败',
  rule1: 0,
  rule2: 0,
  rule3: '0',
  screenRule: 0,
  passbackRule: 0,
  passbackRuleValue: '0',
}, {
  key: '4',
  name: '激活回传策略',
  mode: 3,
  modeLabel: '枚举',
  time: '2022-06-21 23:10',
  status: '失败',
  rule1: 0,
  rule2: 0,
  rule3: '0',
  screenRule: 0,
  passbackRule: 0,
  passbackRuleValue: '0',
}, {
  key: '5',
  name: '字符型回传策略',
  mode: 1,
  modeLabel: '字符',
  time: '2022-06-21 23:10',
  status: '失败',
  rule1: 0,
  rule2: 0,
  rule3: '0',
  screenRule: 0,
  passbackRule: 0,
  passbackRuleValue: '0',
}];
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