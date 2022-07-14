// ===================新增/修改事件======================
export interface EventPropertyRequest { // 入参
  nameCn?: string            // 分页页码
  nameEn?: string            // 获取行数
  groupId?: number
  id?: number
  remarks?: string
  propertyDataEntityList?: propertyDataEntityList[]
  fieldType?: string
  is_group_view?: string | number
}
export interface propertyDataEntityList {
  nameEn?: string
}
export interface GetPropertyData { // 事件类型
  (data: EventPropertyRequest): Promise<PropertyResponse>
}
export type PropertyResponse = MResponse<getEventDataList[]> // 返回值
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
// ===================获取事件列表======================
export interface getEventDataList { 
  dates?: Array<{[key: string]: number}> | string
  [key: string]: string | number | undefined | Array<{[key: string]: number | object}>
  total?: string
  code: string
  msg?: string
}
export interface EventDataRequest { // 入参
  page: number             // 分页页码
  limit: number            // 获取行数
}
                        // 定义请求方法类型
export type EventDataListResponse = MResponse<getEventDataList[]> // 返回值

export interface GetEventData { // 事件类型
  (data: EventDataRequest): Promise<EventDataListResponse>
}

// export interface EventDataListResponseDetail extends GetEventData {
//   total : {
//       [key: string]:  number
//   },
//   times: string[]
//   code: string
//   msg?: string
// }
