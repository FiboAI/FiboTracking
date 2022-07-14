<!--事件管理eventManagement -->
<template>
  <div class="strstegies-container">
    <div class="header">{{ page_title }}</div>
    <div class="strstegies-content">
      <div class="strstegies-top">
        <a-button type="primary" @click="addEvent">添加事件</a-button>
      </div>
      <div class="strstegies-table">
        <a-table :columns="columns" :data="event_table_data" :loading="isLoading" :pagination="false">
          <template #optional="{ record }">
            <a-button type="text" @click="editEvent(record)">编辑</a-button>
            <a-popconfirm content="确认删除吗？" position="left" @ok="deleteEvent(record)">
              <a-button type="text">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
        <a-pagination class="pagination" v-show="total > 0" :total="total" @change="pageChange" />
      </div>
    </div>
  </div>
  <a-modal v-model:visible="show" :width="800">
    <template #title>
      {{ isEdit == false ? '编辑事件' : '添加事件' }}
    </template>
    <a-form ref="formRef" :model="event_table_data_list" :style="{ width: '700px' }">
      <a-form-item field="nameEn" label="*事件英文名" :disabled="isEdit == false">
        <a-input v-model="nameEn" placeholder="请输入事件英文名" />
      </a-form-item>
      <a-form-item field="nameCn" label="*事件中文名">
        <a-input v-model="nameCn" placeholder="请输入事件中文名" />
      </a-form-item>
      <a-form-item field="groupId" label="*事件分组">
        <a-select v-model="groupId" placeholder="请选择事件分组" @change="changeGroup(groupId)">
          <a-option v-for="item in group" :key="item.id" :value="item.id" :label="item.name"></a-option>
        </a-select>
      </a-form-item>
      <a-form-item field="sort" label="*权重">
        <a-input-number v-model="sort" placeholder="请输入权重" />
      </a-form-item>
      <a-form-item field="remarks" label="备注">
        <a-input v-model="remarks" placeholder="请输入备注" />
      </a-form-item>
      <a-form-item field="property" label="*属性">
        <a-button type="primary" @click="addProperty(id)">添加关联属性</a-button>
      </a-form-item>
      <a-form-item>
        <!-- <a-table :columns="property_columns" :data="property_table_data" v-model:selectedKeys="selectedKeys"
          :pagination="pagination" v-if="property_table_data.length != 0"> -->
        <a-table :columns="property_columns" :data="property_table_data" v-if="property_table_data.length != 0">
          <template #property_optional="{ record, rowIndex }">
            <a-button type="text" @click="deleteProperty(record, rowIndex)">删除</a-button>
          </template>
        </a-table>
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="cancelFunc">关闭</a-button>
      <a-button @click="sumbitFunc" type="primary">确定</a-button>
    </template>
  </a-modal>
  <a-modal v-model:visible="property_show">
    <template #title>
      添加属性
    </template>
    <a-form ref="formRef" :model="event_table_data_list">
      <a-form-item field="property" label="*属性">
        <a-select v-model="groupPropertyId" placeholder="请选择关联属性" allow-search
          @change="changeGroupProperty(groupPropertyId)">
          <a-option v-for="item in groupProperty" :key="item.id" :value="item.id" :label="item.nameCn"></a-option>
        </a-select>
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="cancelPropertyFunc">关闭</a-button>
      <a-button @click="sumbitPropertyFunc(groupPropertyId)" type="primary">确定</a-button>
    </template>
  </a-modal>
</template>

<script lang='ts' setup>
import useMessage from "_hooks/useMessage";
import {
  getEventData,
  getListAll,
  getProperty,
  getPropertyList,
  saveOrUpdate,
  deleteEvents,
} from "_apis/data";
import { EventDataRequest, EventPropertyRequest, propertyDataEntityList } from "_types/data";

const { MessageError, MessageSuccess } = useMessage();
// 公共数据
const page_title: string = "事件管理";
const isLoading = ref<boolean>(true);
const isEdit = ref<boolean>(true);
const show = ref<boolean>(false);
const property_show = ref<boolean>(false);
let total = ref();
let columns = reactive<any>([
  {
    title: '事件英文名',
    dataIndex: 'nameEn',
  },
  {
    title: '事件中文名',
    dataIndex: 'nameCn',
  },
  {
    title: '事件备注',
    dataIndex: 'remarks',
  },
  {
    title: '排序权重',
    dataIndex: 'sort',
  },
  {
    title: '操作',
    slotName: 'optional'
  }
]);
let property_columns = reactive<any>([
  {
    title: '事件英文名',
    dataIndex: 'nameEn',
  },
  {
    title: '事件中文名',
    dataIndex: 'nameCn',
  },
  {
    title: '操作',
    slotName: 'property_optional'
  }
]);

let event_table_data = reactive<any>([]);
let property_table_data = reactive<any>(<any>[]);
let event_table_data_list = reactive<any>([]);
let group = reactive<any>([]);
let groupProperty = reactive<any>([]);
let nameEn = ref<string>();
let nameCn = ref<string>();
let remarks = ref<string>();
let sort = ref<number>();
let id = ref<number>();
let groupId = ref();
let groupPropertyId = ref();
// 重置
const reset = (): void => {
  isEdit.value = true
  nameEn.value = ''
  nameCn.value = ''
  remarks.value = ''
  id.value = NaN
  sort.value = NaN
  groupId.value = NaN
  property_table_data.length = 0;
}
// 添加事件
const addEvent = () => {
  reset()
  show.value = true;
}
let params = reactive<EventDataRequest>({
  page: 1,
  limit: 10
});
// 获取事件列表
const getEventDataList = () => {
  event_table_data.length = 0;
  getEventData(params).then((res: any) => {
    // console.log(res.datas);
    total.value = res.datas.totalCount
    isLoading.value = false;
    event_table_data.push(...res.datas.list)
  })
}
// 编辑事件列表
const editEvent = (value: any): void => {
  isEdit.value = false;
  show.value = true;
  nameEn.value = value.nameEn
  nameCn.value = value.nameCn
  remarks.value = value.remarks
  sort.value = value.sort
  groupId.value = value.groupId
  id.value = value.id
  getProperty(value.id).then((res: any) => {
    property_table_data.length = 0;
    // console.log(res.datas.propertyDataEntityList);
    property_table_data.push(...res.datas.propertyDataEntityList);
  })
  // console.log(value);
}
// 删除事件列表
const deleteEvent = (value: any): void => {
  // console.log('删除', value.id);
  deleteEvents({ id: value.id }).then((res) => {
    MessageSuccess('删除成功');
    getEventDataList();
  });
}
// 删除属性
const deleteProperty = (value: any, index: any): void => {
  property_table_data.splice(index, 1);
}
// 添加关联属性
const addProperty = (value: any): void => {
  property_show.value = true
}
// 点击分页
const pageChange = (value: any): void => {
  params.page = value;
  getEventDataList()
}
// 获取属性分组
const getPropertyArray = (): void => {
  getPropertyList().then((res: any) => {
    // console.log(res.datas);
    groupProperty.push(...res.datas)
  })
}

// 获取所有分组
const getListAllList = (): void => {
  getListAll().then((res: any) => {
    group.push(...res.datas)
  })
}
// 更改group分组
const changeGroup = (value: any): void => {
  console.log(value);
}
const changeGroupProperty = (value: any): void => {
  // console.log(value);
}
// 新增/修改事件
const sumbitFunc = (): void => {
  show.value = false;
  let obj = reactive<Array<propertyDataEntityList>>([]);
  property_table_data.forEach((item: any) => {
    obj.push({ nameEn: item.nameEn });
  });
  let params = <EventPropertyRequest>{
    nameCn: nameCn.value,
    nameEn: nameEn.value,
    groupId: groupId.value,
    sort: sort.value,
    id: id.value,
    remarks: remarks.value,
    propertyDataEntityList: obj
  };
  // console.log(params);
  saveOrUpdate(params).then(res => {
    MessageSuccess('修改成功');
    getEventDataList()
  })
}
const cancelFunc = (): void => {
  show.value = false;
}
const sumbitPropertyFunc = (value: any): void => {
  console.log(groupProperty[value - 1]);
  property_table_data.push(groupProperty[value - 1]);
  property_show.value = false;
}
const cancelPropertyFunc = (): void => {
  property_show.value = false;
}

// create阶段运行的函数
getPropertyArray()
getListAllList()
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