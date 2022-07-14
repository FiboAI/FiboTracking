import Request from "@/utils/axios";
import type {
  GetGroupEventList,
  GetEventData,
  EventDataRequest,
  EventDataListResponse,
  EventPropertyRequest,
  GetPropertyData,
} from "_types/data";
// 获取事件列表
export const getPropertyData: GetEventData = (data) => {
  return Request<EventDataRequest, EventDataListResponse>({
      url: "cdp/propertydata/list",
      method: "post",
      data
  })
}
// 删除事件
export const deleteEvents = (data: {id: number}) => {
  return Request<{id: number}, EventDataListResponse>({
      url: "cdp/eventdata/delete",
      method: "post",
      data
  })
}
// 删除属性
export const deleteProperty = (data: {id: number}) => {
  return Request<{id: number}, EventDataListResponse>({
      url: "cdp/propertydata/delete",
      method: "post",
      data
  })
}
// 获取所有分组
export const getPropertyList = () => {
  return Request<null, GetGroupEventList>({
      url: "cdp/propertydata/listAll",
      method: "get",
  })
}
// 获取属性分组
export const getProperty = (data: any) => {
  return Request<any, EventDataListResponse>({
      url: "cdp/eventdata/info/" + data,
      method: "get",
  })
}
// 新增/修改事件
export const saveOrUpdate: GetPropertyData = (data) => {
  return Request<EventPropertyRequest, EventDataListResponse>({
      url: "cdp/eventdata/saveOrUpdate",
      method: "post",
      data
  })
}
// 新增/修改属性
export const saveOrUpdateProperty: GetPropertyData = (data) => {
  return Request<EventPropertyRequest, EventDataListResponse>({
      url: "cdp/propertydata/saveOrUpdate",
      method: "post",
      data
  })
}
// 获取所有分组
export const getListAll = () => {
  return Request<null, GetGroupEventList>({
      url: "cdp/eventgroup/listAll",
      method: "post",
  })
}

// 获取事件列表
export const getEventData: GetEventData = (data) => {
  return Request<EventDataRequest, EventDataListResponse>({
      url: "cdp/eventdata/list",
      method: "post",
      data
  })
}