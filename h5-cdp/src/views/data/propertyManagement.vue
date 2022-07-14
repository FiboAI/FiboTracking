<template>
  <div>
    <!--属性管理propertyManagement -->
    <div class="strstegies-container">
      <div class="header">{{ page_title }}</div>
      <div class="strstegies-content">
        <div class="strstegies-top">
          <a-button type="primary" @click="addProperty">添加属性</a-button>
        </div>
        <div class="strstegies-table">
          <a-table :columns="columns" :data="event_table_data" :loading="isLoading" :pagination="false">
            <template #fieldType="{ record }">
              <span>{{ record.fieldType == 'string' ? '字符' : record.fieldType == 'number' ? '数值' : '布尔' }}</span>
            </template>
            <template #isGroupView="{ record }">
              <span>{{ record.isGroupView == '0' ? '否' : record.isGroupView == '1' ? '是' : '未选择' }}</span>
            </template>
            <template #optional="{ record }">
              <a-button type="text" @click="editEvent(record)">编辑</a-button>
              <a-popconfirm content="确认删除吗？" position="left" @ok="deleteEvent(record)">
                <a-button type="text">删除</a-button>
              </a-popconfirm>
            </template>
          </a-table>
          <a-pagination class="pagination" v-show="total > 0" :total="total" @change="pageChange"
            :page-size="pageSize" />
        </div>
      </div>
    </div>
    <a-modal v-model:visible="show" :width="800">
      <template #title>
        {{ isEdit == false ? '编辑事件' : '添加事件' }}
      </template>
      <a-form ref="formRef" :model="event_table_data_list" :style="{ width: '700px' }">
        <a-form-item field="nameEn" label="*属性英文名" :disabled="isEdit == false">
          <a-input v-model="nameEn" placeholder="请输入属性英文名" />
        </a-form-item>
        <a-form-item field="nameCn" label="*属性中文名">
          <a-input v-model="nameCn" placeholder="请输入属性中文名" />
        </a-form-item>
        <a-form-item field="fieldType" label="*事件分组">
          <a-select v-model="fieldType" placeholder="请选择事件分组" @change="changeGroup(fieldType)">
            <a-option v-for="item in event_group" :key="item.id" :value="item.value" :label="item.name"></a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="isGroupView" label="*是否分组">
          <a-select v-model="isGroupView" placeholder="请选择是否分组" @change="changeIsGroup(isGroupView)">
            <a-option v-for="item in is_group_list" :key="item.id" :value="item.value" :label="item.name"></a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="sort" label="*权重">
          <a-input-number v-model="sort" placeholder="请输入权重" />
        </a-form-item>
        <a-form-item field="remarks" label="备注">
          <a-input v-model="remarks" placeholder="请输入备注" />
        </a-form-item>
      </a-form>
      <template #footer>
        <a-button @click="cancelFunc">关闭</a-button>
        <a-button @click="sumbitFunc" type="primary">确定</a-button>
      </template>
    </a-modal>
  </div>
</template>

<script lang='ts' setup>
import useMessage from "_hooks/useMessage";
import {
  getPropertyData,
  saveOrUpdateProperty,
  deleteProperty,
} from "_apis/data";
import {
  EventDataRequest,
  EventPropertyRequest,
  propertyDataEntityList
} from "_types/data";
const { MessageError, MessageSuccess } = useMessage();
// 公共数据
const page_title: string = "属性管理";
const isLoading = ref<boolean>(true);
const isEdit = ref<boolean>(true);
const show = ref<boolean>(false);
let total = ref();
let pageSize = ref();
let columns = reactive<any>([
  {
    title: '属性英文名',
    dataIndex: 'nameEn',
  },
  {
    title: '属性中文名',
    dataIndex: 'nameCn',
  },
  {
    title: '属性类型',
    slotName: 'fieldType',
  },
  {
    title: '是否分组',
    slotName: 'isGroupView',
  },
  {
    title: '权重',
    dataIndex: 'sort',
  },
  {
    title: '事件备注',
    dataIndex: 'remarks',
  },
  {
    title: '操作',
    slotName: 'optional'
  }
]);
let event_table_data = reactive<any>([]);
let event_table_data_list = reactive<any>([]);
let event_group = reactive<any>([
  {
    id: 1,
    name: '数值',
    value: 'number',
  },
  {
    id: 2,
    name: '字符',
    value: 'string',
  },
  {
    id: 3,
    name: '布尔',
    value: 'bool',
  },
]);
let is_group_list = reactive<any>([
  {
    name: '是',
    value: 1,
  },
  {
    name: '否',
    value: 0,
  },
]);
let nameEn = ref<string>();
let nameCn = ref<string>();
let remarks = ref<string>();
let sort = ref<number>();
let id = ref<number>();
let fieldType = ref();
let isGroupView = ref();
// 
let params = reactive<EventDataRequest>({
  page: 1,
  limit: 20
});
// 获取事件列表
const getEventDataList = () => {
  event_table_data.length = 0;
  getPropertyData(params).then((res: any) => {
    total.value = res.datas.totalCount
    pageSize.value = res.datas.pageSize
    isLoading.value = false;
    event_table_data.push(...res.datas.list)
  })
}
const reset = (): void => {
  isEdit.value = true
  nameEn.value = ''
  nameCn.value = ''
  remarks.value = ''
  fieldType.value = ''
  sort.value = NaN
  isGroupView.value = ''
}
const addProperty = () => {
  reset()
  show.value = true;
}
// 编辑属性列表
const editEvent = (value: any): void => {
  isEdit.value = false;
  show.value = true;
  nameEn.value = value.nameEn
  nameCn.value = value.nameCn
  remarks.value = value.remarks
  sort.value = value.sort
  id.value = value.id
  isGroupView.value = value.isGroupView
  fieldType.value = value.fieldType
}
// 删除属性列表
const deleteEvent = (value: any): void => {
  deleteProperty({ id: value.id }).then((res) => {
    MessageSuccess('删除成功');
    getEventDataList();
  });
}
// 点击分页
const pageChange = (value: any): void => {
  // params.page = value;
  // getEventDataList()
}
// 新增/修改事件
const sumbitFunc = (): void => {
  show.value = false;
  if (isEdit.value == false) {
    let params = <EventPropertyRequest>{
      nameCn: nameCn.value,
      nameEn: nameEn.value,
      sort: sort.value,
      id: id.value,
      isGroupView: isGroupView.value,
      fieldType: fieldType.value,
      remarks: remarks.value,
    };
    console.log(params);
    saveOrUpdateProperty(params).then(res => {
      MessageSuccess('修改成功');
      getEventDataList()
    })
  } else {
    let params = <EventPropertyRequest>{
      nameCn: nameCn.value,
      nameEn: nameEn.value,
      sort: sort.value,
      isGroupView: isGroupView.value,
      fieldType: fieldType.value,
      remarks: remarks.value,
    };
    console.log(params);
    saveOrUpdateProperty(params).then(res => {
      MessageSuccess('添加成功');
      getEventDataList()
    })
  }
}
const cancelFunc = (): void => {
  show.value = false;
}
// 更改group分组
const changeGroup = (value: any): void => {
  console.log(value);
}
const changeIsGroup = (value: any): void => {
  console.log(value);
}
// create调用
getEventDataList()
</script>

<style lang='less' scoped>
.pagination {
  margin-top: 20px;
  position: absolute;
  right: 20px;
}

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