<!--角色管理 -->
<template>
  <div class="strstegies-container">
    <div class="header">{{ page_title }}</div>
    <div class="strstegies-content">
      <div class="strstegies-top">
        <a-button type="primary" @click="addEvent">添加角色</a-button>
      </div>
      <div class="strstegies-table">
        <a-table style="width: 500px;" :columns="columns" :data="event_table_data" :loading="isLoading"
          :pagination="false">
          <template #optional="{ record }">
            <a-button type="text" @click="editEvent(record)">编辑</a-button>
            <a-popconfirm content="确认删除吗？" position="left" @ok="deleteEvent(record)">
              <a-button type="text">删除</a-button>
            </a-popconfirm>
          </template>
        </a-table>
        <a-pagination class="pagination" v-show="total > 0" :total="total" @change="pageChange" :page-size="pageSize" />
      </div>
    </div>
  </div>
  <a-modal v-model:visible="show" :width="800">
    <template #title>
      {{ isEdit == false ? '编辑账号' : '添加账号' }}
    </template>
    <a-form ref="formRef" :model="event_table_data_list" :style="{ width: '700px' }">
      <a-form-item field="roleName" label="*角色名称">
        <a-input v-model="roleName" placeholder="请输入角色名称" />
      </a-form-item>
      <a-form-item field="menuIdList" label="*账号权限">
        <a-tree-select v-model="menuIdList" :data="treeData" :allow-clear="true" :allow-search="true"
          :tree-checkable="true" :tree-check-strictly="false" placeholder="请选择账号权限" :max-tag-count="1"></a-tree-select>
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="cancelFunc">关闭</a-button>
      <a-button @click="sumbitFunc" type="primary">确定</a-button>
    </template>
  </a-modal>
</template>

<script lang='ts' setup>
import { h, ref } from 'vue';
import { IconCalendar } from '@arco-design/web-vue/es/icon';
import {
  getRolerList,
  saveOrUpdateRole,
  deleteRoles,
  getMenuTree,
  getRoleDetail,
} from "_apis/admin";
import {
  UserListRequest,
} from "_types/admin";
import useMessage from "_hooks/useMessage";
const { MessageError, MessageSuccess } = useMessage();
// 公共数据
const page_title: string = "角色管理";
const isLoading = ref<boolean>(true);
const isEdit = ref<boolean>(true);
const show = ref<boolean>(false);
let total = ref();
let pageSize = ref();
let columns = reactive<any>([
  {
    title: '角色名称',
    dataIndex: 'roleName',
  },
  {
    title: '操作',
    slotName: 'optional'
  }
]);
let event_table_data = reactive<any>([]);
let event_table_data_list = reactive<any>([]);
let roleName = ref<string>();
let roleId = ref<number>();
let treeData = reactive<any>([]);
let menuIdList = ref([]);
let params = reactive<UserListRequest>({
  page: 1,
  limit: 10
});

const getEventDataList = () => {// 获取事件列表
  event_table_data.length = 0;
  getRolerList(params).then((res: any) => {
    // console.log(res.datas);
    total.value = res.datas.totalCount
    pageSize.value = res.datas.pageSize
    isLoading.value = false;
    event_table_data.push(...res.datas.list)
  })
}

const getMenuTreeList = (): void => {// 获取所有菜单
  getMenuTree().then((res: any) => {
    res.datas.map((item: any) => {
      item.icon = '';
      item.key = item.menuId
      item.title = item.name
      item.value = item.menuId
      item.children.map((item1: any) => {
        item1.key = item1.menuId
        item1.title = item1.name
        item1.value = item1.menuId
      })
    })
    // console.log(res.datas);
    treeData.push(...res.datas);
  })
}

const reset = (): void => {// 重置
  isEdit.value = true
  roleName.value = ''
  roleId.value = NaN
  menuIdList.value = []
}

const addEvent = () => {// 添加账号
  reset()
  show.value = true;
}

const editEvent = (value: any): void => {// 编辑属性列表
  getRoleList(value.roleId)
}

const deleteEvent = (value: any): void => {// 删除属性列表
  deleteRoles({ roleId: value.roleId }).then((res) => {
    MessageSuccess('删除成功');
    getEventDataList();
  });
}

const pageChange = (value: any): void => {// 点击分页
  params.page = value;
  getEventDataList()
}

const sumbitFunc = (): void => {// 新增/修改事件
  show.value = false;
  let params = <any>{
    roleName: roleName.value,
    roleId: roleId.value,
    menuIdList: menuIdList.value,
  };
  saveOrUpdateRole(params).then(res => {
    MessageSuccess(isEdit.value ? '添加成功' : '修改成功');
    getEventDataList();
  })
}
const cancelFunc = (): void => {
  show.value = false;
}
const getRoleList = (value: any) => { // 获取角色详情
  getRoleDetail(value).then((res: any) => {
    console.log(res.datas);
    isEdit.value = false;
    show.value = true;
    roleName.value = res.datas.roleName
    roleId.value = res.datas.roleId
    menuIdList.value = res.datas.menuIdList
  })
}
// create
getEventDataList()
getMenuTreeList()
</script>

<style lang='less' scoped>
.pagination {
  margin-top: 20px;
  position: absolute;
  left: 600px;
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