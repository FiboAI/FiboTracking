<template>
  <div class="records-container">
    <div class="header">{{ page_title }}</div>
    <div class="records-content">
      <div class="records-top">
        <div class="time">
          <span>回传时间: </span>
          <range-picker @change="rangePickerChangeFunc" :model-value="[records_date[0], records_date[1]]"
            :style="{ width: '300px', marginBottom: '10px' }" />
        </div>
        <div class="mode">
          <span> 回传方式: </span>
          <a-space direction="vertical" size="small">
            <a-select v-model="mode_value" size="small" @change="changeMode" placeholder="请选择回传方式">
              <a-option v-for="(item, index) in mode_data" :value="item" :label="item.label" :key="index" />
            </a-select>
          </a-space>
        </div>
        <div class="status">
          <span> 回传状态: </span>
          <a-space direction="vertical" size="small">
            <a-select v-model="status_value" size="small" @change="changeStatus" placeholder="请选择回传状态">
              <a-option v-for="(item, index) in status_data" :value="item" :label="item.label" :key="index" />
            </a-select>
          </a-space>
        </div>
        <div class="search">
          <a-button type="primary" @click="records_search">搜索</a-button>
        </div>
      </div>
      <div class="records-table">
        <a-table :columns="columns" :data="records_table_data">
          <template #optional="{ record }">
            <a-button @click="viewDetail(record)">查看详情</a-button>
          </template>
        </a-table>
      </div>
    </div>
  </div>
  <!-- <detail v-model:visible="show" :data="detail_data"></detail> -->
  <a-modal v-model:visible="show" :width="700">
    <template #title>
      回传事件详情
    </template>
    <a-form ref="formRef" :model="detail_data" :style="{ width: '600px' }">
      <a-form-item field="name" label="*回传事件名称">
        <a-input v-model="detail_data.name" />
      </a-form-item>
      <a-form-item field="time" label="*回传时间" >
        <a-input v-model="detail_data.time" />
      </a-form-item>
      <a-form-item field="mode" label="*回传方式">
        <a-input v-model="detail_data.mode" />
      </a-form-item>
      <a-form-item field="status" label="*回传状态">
        <a-input v-model="detail_data.status" />
      </a-form-item>
      <a-form-item field="detail" label="*回传内容" auto-size disabled>
        <a-textarea v-model="detail_data.detail" allow-clear />
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="cancelFunc">关闭</a-button>
    </template>
  </a-modal>
</template>

<script lang='ts' setup>
import { Ref } from "vue";
import useDate from "_hooks/useDate";
import useMessage from "_hooks/useMessage";
import type { TableColumnData } from "@arco-design/web-vue";
import type {
  DetailData
} from "_types/analysis";
// 公共方法
const route = useRoute();
const { getAddTime } = useDate();
const { MessageError } = useMessage();
// 初始默认数据
const page_title: string = "回传记录";
// 回传时间数据
let records_date = reactive<string[]>([
  getAddTime(-7),
  getAddTime(-1)
]);
const rangePickerChangeFunc = (value: unknown): void => {
  records_date.length = 0
  records_date.push(...value as string);
}
// 回传方式数据
const mode_value = ref();
const mode_data = [{
  value: 0,
  label: '所有方式',
  other: 'extra'
}, {
  value: 1,
  label: '实时',
  other: 'extra'
}, {
  value: 2,
  label: '定时',
  other: 'extra'
}];
const changeMode = (value: unknown): void => {
  console.log(value);
}
// 回传状态数据
const status_value = ref();
const status_data = [{
  value: 0,
  label: '所有状态',
  other: 'extra'
}, {
  value: 1,
  label: '成功',
  other: 'extra'
}, {
  value: 2,
  label: '失败',
  other: 'extra'
}];
const changeStatus = (value: unknown): void => {
  console.log(value);
}
// 搜索相关
const records_search = (value: unknown): void => {
  // console.log('获取入参重新调用接口并刷新页面', records_date, mode_value.value, status_value.value);
  if (mode_value.value == undefined) {
    MessageError('请选择回传方式');
  } else if (status_value.value == undefined) {
    MessageError('请选择回传状态');
  }
}
// 回传记录表格数据
const columns = [{
  title: '回传事件名称',
  dataIndex: 'name',
}, {
  title: '回传方式',
  dataIndex: 'mode',
}, {
  title: '回传时间',
  dataIndex: 'time',
}, {
  title: '回传状态',
  dataIndex: 'status',
}, {
  title: '操作',
  slotName: 'optional'
}];
const records_table_data = [{
  key: '1',
  name: '支付成功',
  mode: '实时',
  time: '2022-06-21 23:43',
  status: '成功',
  detail: '11111111111111111111111111',
}, {
  key: '2',
  name: '注册',
  mode: '定时',
  time: '2022-06-21 23:45',
  status: '成功',
  detail: '22222222222222222222222222222222222222',
}, {
  key: '3',
  name: '商品购买',
  mode: '实时',
  time: '2022-06-21 23:10',
  status: '失败',
  detail: '3333333333333333333333333333333333333333',
}];
let show = ref<boolean>(false);
let detail_data = reactive<DetailData>({
  key: '1',
  name: '支付成功',
  time: '2022-06-21 23:45',
  mode: '实时回传',
  status: '成功',
  detail: `{
    "event_type": "successful_pay",
    "context": {
        "ad": {
            "callback": "EPHk9cX3pv4CGJax4ZENKI7w4MDev_4CEPHk9cX3pv4CGJax4ZENKI7w4MDev_4C"
        }
    },
    "properties": {
        "product_id": "3234",
        "product_price": 120
    },
    "timestamp": 1604888786102
}`
});
const viewDetail = (value: DetailData): void => {
  show.value = true;
  detail_data = value;
  console.log('查看详情 弹窗', detail_data);
}
const cancelFunc = (value: unknown): void => {
  show.value = false;
  // console.log(value);
}

</script>

<style lang='less' scoped>
.header {
  font-size: 22px;
  font-weight: bold;
  flex: auto;
  margin-bottom: 10px;
}

.records-container {
  height: 100%;
  box-sizing: border-box;
  padding: 16px 0 16px 16px;
  overflow: hidden;

  &>.records-content {
    height: calc(100% - 62px);
    width: 100%;
    overflow: hidden;

    &>.records-top {
      display: flex;

      &>.search {
        position: absolute;
        right: 10px;
      }
    }

    &>.records-table {
      width: 99%;
    }
  }


}
</style>