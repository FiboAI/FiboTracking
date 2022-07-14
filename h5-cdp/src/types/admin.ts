// ===================用户列表===================
export interface GetUserList { // 事件类型
  (data: UserListRequest): Promise<EventDataListResponse>
}
export interface UserListRequest { // 入参
  page: number
  limit: number
}
export type EventDataListResponse = MResponse<getEventDataList[]> // 返回值类型

export interface getEventDataList { 
  dates?: Array<{[key: string]: number}> | string
  [key: string]: string | number | undefined | Array<{[key: string]: number | object}>
  total?: string
  code?: string
  msg?: string
}
// ===================新增修改===================
export interface SaveOrUpdate { // 事件类型
  (data: SaveOrUpdateRequest): Promise<EventDataListResponse>
}
export interface SaveOrUpdateRequest { // 入参
  username?: string
  userId?: number
  roleIdList?: Array<number>
}
export type SaveOrUpdateResponse = MResponse<getEventDataList[]> // 返回值类型
// ================新增修改role========================
export interface SaveOrUpdateRole { // 事件类型
  (data: SaveOrUpdateRoleRequest): Promise<EventDataListResponse>
}
export interface SaveOrUpdateRoleRequest { // 入参
  roleName?: string
  roleId?: number
  menuIdList?: Array<number>
}
// ===================获取所有分组======================
export type EventGroupData = {
  name: string
  eventDataDtoList: Array<{
      id: number
      nameCn: string
      nameEn: string
      isVirtual: number
  }>
}[]
export type EventGroupResponse = MResponse<EventGroupData>
export interface GetGroupEventList {
  (): Promise<EventGroupResponse>
}