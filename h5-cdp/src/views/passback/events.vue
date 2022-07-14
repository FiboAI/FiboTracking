<template>
  <div class="strstegies-container">
    <div class="header">{{ page_title }}</div>
    <div class="strstegies-content">
      <a-form ref="formRef" :model="form" :style="{ width: '700px' }" @submit="handleSubmit">
        <a-form-item field="name" label="*回传事件名称">
          <a-input v-model="name" placeholder="请输入回传事件名称" />
        </a-form-item>
        <a-form-item field="section" label="*媒体侧回传事件名">
          <a-select v-model="mode" placeholder="请选择媒体侧回传事件名">
            <a-option v-for="item in form.mode" :key="item.value" :value="item.value" :label="item.label"></a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="section" label="*对应网站采集事件">
          <a-select v-model="mode1" placeholder="请选择对应网站采集事件">
            <a-option v-for="item in form.mode1" :key="item.value" :value="item.value" :label="item.label"></a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="radio" label="*回传方式">
          <a-radio-group v-model="form.screenRule" @change="screenRules(form.screenRule)">
            <a-radio value="0">触发回传</a-radio>
            <a-radio value="1">定时回传</a-radio>
          </a-radio-group>
        </a-form-item>
        <a-form-item field="section" v-if="form.screenRule == '1'" label="*回传时间设置">
          <a-select v-model="rule1" placeholder="请选择">
            <a-option v-for="item in form.rule1" :key="item.value" :value="item.value" :label="item.label"></a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="section" label="*回传窗口期">
          <a-select v-model="passbackRule" placeholder="请选择回传窗口期">
            <a-option v-for="item in form.passbackRule" :key="item.value" :value="item.value" :label="item.label">
            </a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="section" label="*回传频次">
          <a-select v-model="rule2" placeholder="请选择回传频次">
            <a-option v-for="item in form.rule2" :key="item.value" :value="item.value" :label="item.label">
            </a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="section" label="*可选附加属性">
          <a-select v-model="rule3" placeholder="请选择附加属性">
            <a-option v-for="item in form.rule3" :key="item.value" :value="item.value" :label="item.label">
            </a-option>
          </a-select>
        </a-form-item>
        <a-form-item>
          <a-space>
            <a-button type="primary" html-type="submit">保存</a-button>
          </a-space>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>
<script lang='ts' setup>
import useMessage from "_hooks/useMessage";
const router = useRouter();
const page_title: string = "回传事件配置";
let name = ref();
let mode = ref(0);
let mode1 = ref(0);
let rule1 = ref(0);
let rule2 = ref(0);
let rule3 = ref(0);
let screenRule = ref();
let passbackRule = ref(0);
let passbackRuleValue = ref();
let form = reactive({
  name: '',
  mode: [{
    label: '订单支付',
    value: 0
  }, {
    label: '注册数',
    value: 1
  }, {
    label: '登录数',
    value: 2
  }, {
    label: '提交表单数',
    value: 3
  }],
  mode1: [{
    label: 'Web订单支付',
    value: 0
  }, {
    label: '注册数',
    value: 1
  }, {
    label: '登录数',
    value: 2
  }, {
    label: '提交表单数',
    value: 3
  }],
  screenRule: '0',
  rule1: [{
    label: '每天一次',
    value: 0
  }, {
    label: '一周一次',
    value: 1
  }],
  rule2: [{
    label: '首次回传',
    value: 0
  }, {
    label: '每次回传',
    value: 1
  }],
  rule3: [{
    label: '订单ID',
    value: 0
  }, {
    label: '支付订单金额',
    value: 1
  }],
  passbackRule: [{
    label: '24小时',
    value: 0
  }, {
    label: '48小时',
    value: 1
  },],
  passbackRuleValue: '',
});
onMounted(() => {
  console.log('router:', router.currentRoute.value.query)
})
const screenRules = (value: any) => {
  console.log('筛选规则', value);
  screenRule.value = value
}
const handleSubmit = (value: any) => {
  console.log(name.value, mode.value, mode1.value, rule1.value, rule2.value, rule3.value, passbackRule.value);
  router.push({
    path: '/passback/configure',
    // query: value
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
  }
}
</style>