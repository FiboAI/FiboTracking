import Request from "@/utils/axios";
import type {
  GetUserList,
  UserListRequest,
  EventDataListResponse,
  SaveOrUpdate,
  SaveOrUpdateRole,
  SaveOrUpdateRequest,
  SaveOrUpdateRoleRequest,
  SaveOrUpdateResponse,
  EventGroupResponse,
} from "_types/admin";
// 获取当前登录用户的菜单
export const getMenuNav = () => {
  return Request<null, EventGroupResponse>({
      url: "sys/menu/nav",
      method: "get",
  })
}
// 获取角色详情
export const getRoleDetail = (data: any) => {
  return Request<any, EventDataListResponse>({
      url: "sys/role/info/" + data,
      method: "get",
  })
}
// 获取用户详情
export const getUserDetail = (data: any) => {
  return Request<any, EventDataListResponse>({
      url: "sys/user/info/" + data,
      method: "get",
  })
}
// 获取所有角色
export const getRoleSelect = () => {
  return Request<null, EventGroupResponse>({
      url: "sys/role/select",
      method: "get",
  })
}
// 获取所有菜单(树形结构)
export const getMenuTree = () => {
  return Request<null, EventGroupResponse>({
      url: "sys/menu/allMenuTree",
      method: "get",
  })
}
// 获取用户列表
export const getUserList: GetUserList = (data) => {
  return Request<UserListRequest, EventDataListResponse>({
      url: "sys/user/list",
      method: "post",
      data
  })
}
// 新增/修改用户
export const saveOrUpdate: SaveOrUpdate = (data) => {
  return Request<SaveOrUpdateRequest, SaveOrUpdateResponse>({
      url: "sys/user/saveOrUpdate",
      method: "post",
      data
  })
}
// 删除账号
export const deleteUsers = (data: { userId: number }) => {
  return Request<{ userId: number }, EventDataListResponse>({
      url: "sys/user/delete",
      method: "post",
      data
  })
}
// 获取角色列表
export const getRolerList: GetUserList = (data) => {
  return Request<UserListRequest, EventDataListResponse>({
      url: "sys/role/list",
      method: "post",
      data
  })
}
// 新增/修改角色
export const saveOrUpdateRole: SaveOrUpdateRole = (data) => {
  return Request<SaveOrUpdateRoleRequest, SaveOrUpdateResponse>({
      url: "sys/role/saveOrUpdate",
      method: "post",
      data
  })
}
// 删除角色
export const deleteRoles = (data: { roleId: number }) => {
  return Request<{ roleId: number }, EventDataListResponse>({
      url: "sys/role/delete",
      method: "post",
      data
  })
}