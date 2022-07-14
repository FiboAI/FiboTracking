<!--账号管理 -->
<template>
  <div class="strstegies-container">
    <div class="header">{{ page_title }}</div>
    <div class="strstegies-content">
      <div class="strstegies-top">
        <a-button type="primary" @click="addEvent">添加账号</a-button>
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
      <a-form-item field="username" label="*账号名称">
        <a-input v-model="username" placeholder="请输入账号名称" />
      </a-form-item>
      <a-form-item field="roleIdList" label="*账号角色">
        <a-select v-model="roleIdList" placeholder="请选择账号角色" multiple>
          <a-option v-for="item in event_group" :key="item.roleId" :value="item.roleId" :label="item.roleName">
          </a-option>
        </a-select>
      </a-form-item>
    </a-form>
    <template #footer>
      <a-button @click="cancelFunc">关闭</a-button>
      <a-button @click="sumbitFunc" type="primary">确定</a-button>
    </template>
  </a-modal>
</template>

<script lang='ts' setup>
import {
  getUserList,
  saveOrUpdate,
  deleteUsers,
  getRoleSelect,
  getUserDetail,
} from "_apis/admin";
import {
  UserListRequest,
} from "_types/admin";
import useMessage from "_hooks/useMessage";
const { MessageError, MessageSuccess } = useMessage();
// 公共数据
const page_title: string = "账号管理";
const isLoading = ref<boolean>(true);
const isEdit = ref<boolean>(true);
const show = ref<boolean>(false);
let total = ref();
let pageSize = ref();
let columns = reactive<any>([
  {
    title: '账号名称',
    dataIndex: 'username',
  },
  {
    title: '账号角色',
    dataIndex: 'roleName',
  },
  {
    title: '操作',
    slotName: 'optional'
  }
]);
let event_table_data = reactive<any>([]);
let event_table_data_list = reactive<any>([]);
let event_group = reactive<any>([]);
let username = ref<string>();
let userId = ref<number>();
let roleIdList = ref([]);

const getRoleList = () => {// 获取角色列表
  getRoleSelect().then(res => {
    // console.log(res.datas);
    event_group.push(...res.datas as any)
  })
}
let params = reactive<UserListRequest>({
  page: 1,
  limit: 10
});

const getEventDataList = () => {// 获取事件列表
  event_table_data.length = 0;
  getUserList(params).then((res: any) => {
    // console.log(res.datas);
    total.value = res.datas.totalCount
    pageSize.value = res.datas.pageSize
    isLoading.value = false;
    event_table_data.push(...res.datas.list)
  })
}

const reset = (): void => {// 重置
  isEdit.value = true
  username.value = ''
  userId.value = NaN
  roleIdList.value = []
}

const addEvent = () => {// 添加账号
  reset()
  show.value = true;
}

const editEvent = (value: any): void => {// 编辑属性列表
  getDetail(value.userId)
}

const deleteEvent = (value: any): void => {// 删除用户列表
  deleteUsers({ userId: value.userId }).then((res) => {
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
  let params = {
    username: username.value,
    userId: userId.value,
    roleIdList: roleIdList.value,
  };
  console.log(params);
  saveOrUpdate(params).then(res => {
    MessageSuccess(isEdit.value ? '添加成功' : '修改成功');
    getEventDataList();
  })
}
const cancelFunc = (): void => {
  show.value = false;
}

const getDetail = (value: any) => {// 获取详情
  isEdit.value = false;
  show.value = true;
  // console.log('userid', value);
  getUserDetail(value).then((res: any) => {
    // console.log(res.datas);
    userId.value = res.datas.userId
    username.value = res.datas.username
    roleIdList.value = res.datas.roleIdList;
  })
}

// create
getEventDataList()
getRoleList()
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