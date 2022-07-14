import Request from "@/utils/axios";
import type {
    GetGroupEventList,
    EventGroupResponse,
    PropertyListRequest,
    PropertyResponse,
    GetPropertyList,
    GetCalculateData,
    GetCalculateData1,
    CalculateDataRequest,
    CalculateData,
    CalculateDataResponse,
    EventAnalysisSaveOrUpdate,
    SavaEventAnalysisRequest,
    SaveOrUpdateEventAnalysisResponse,
    FunnelSaveOrUpdateFunnel,
    AddfunnelData,
    AddfunnelDataResponse,
    GetFunnelListData,
    getFunnelListDataResponse,
    CalculateDataResponseAddTotil,
    CalculateData1,
    QueriesDataRequest
} from "_types/analysis"

// 获取所有事件
export const getGroupEventList: GetGroupEventList = () => {
    return Request<null, EventGroupResponse>({
        url: "cdpclient/eventdata/getGroupEventList",
        method: "get",
    })
}
// 获取属性列表
export const getPropertyList: GetPropertyList = (data) => {
    return Request<PropertyListRequest, PropertyResponse>({
        url: "cdpclient/eventdata/getPropertyList",
        method: "get",
        data
    })
}
// 根据规则数据计算数据
export const getCalculateData: GetCalculateData = (data) => {
    return Request<CalculateDataRequest, CalculateDataResponseAddTotil>({
        url: "cdpclient/analysisconfig/getCalculateData",
        method: "post",
        data
    })
}
// 新增或更新概览
export const saveOrUpdate: EventAnalysisSaveOrUpdate = (data) => {
    return Request<SavaEventAnalysisRequest, SaveOrUpdateEventAnalysisResponse>({
        url: "cdpclient/analysisconfig/eventAnalysisSaveOrUpdate",
        method: "post",
        data
    })
}
// ===============漏斗部分
// 新增或更新漏斗
export const saveOrUpdateFunnel: FunnelSaveOrUpdateFunnel = (data) => {
    return Request<AddfunnelData,AddfunnelDataResponse>({
        url: "cdpclient/funneldata/saveOrUpdateFunnel",
        method: "post",
        data
    })
}
// 获取所有漏斗
export const getFunnelListData: GetFunnelListData = (data) => {
    return Request<undefined,getFunnelListDataResponse>({
        url: "cdpclient/funneldata/getFunnelListData",
        method: "get",
        data
    })
}
export const deleteFunnelUrl = (data:{funnelId :number}) => {
    return Request<{funnelId :number},{[key:string] :string}>({
        url: "/cdpclient/funneldata/removeFunnel",
        method: "post",
        data
    })
}
// 自定义查询
export const getSqlData: GetCalculateData1 = (data) => {
    return Request<QueriesDataRequest, CalculateData1>({
        url: "/cdpclient/analysisconfig/getCalculateData",
        method: "post",
        data
    })
}
export const exportData: GetCalculateData1 = (data) => {
    return Request<QueriesDataRequest, CalculateData1>({
        url: "/cdpclient/analysisconfig/exportData",
        method: "post",
        data
    })
}