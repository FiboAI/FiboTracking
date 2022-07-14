import Request from "@/utils/axios";
import type {
    OverviewList,
    OverviewItem,
    AddOverview,
    GetOverviewList,
    OverviewDetailsRequest,
    OverviewDetailsResponse,
    GetOverviewDetails,
    GetCalculateDataByModelId,
    GetCalculateDataByModelIdRequest,
    GetCalculateDataByModelIdResponse,
    RemoveOverviewWidget,
    RemoveOverview,
    GetModelListByOverviewId
} from "_types/overview"
import type { CalculateDataResponse } from "_types/analysis"

// 获取概览列表
export const getOverviewList: GetOverviewList = (data) => {
    return Request<object, OverviewList>({
        url: "cdpclient/overviewdata/getList",
        method: "get",
        data
    })
}

// 新增概览
export const addOverview: AddOverview = (data) => {
    return Request<OverviewItem, MResponse<any>>({
        url: "cdpclient/overviewdata/saveOrUpdate",
        method: "post",
        data
    })
}

// 获取概览详情
export const getOverviewDetails: GetOverviewDetails = (data) => {
    return Request<OverviewDetailsRequest, OverviewDetailsResponse>({
        url: "cdpclient/overviewdata/getModelListByOverviewId",
        method: "get",
        data
    })
}

// 删除概览
export const removeOverview: RemoveOverview.RequestFunc = (data) => {
    return Request<RemoveOverview.Request, RemoveOverview.Response>({
        url: "cdpclient/overviewdata/removeOverview",
        method: "post",
         data
    })
}

// 根据规则数据计算数据
export const getCalculateDataByModelId: GetCalculateDataByModelId = (data) => {
    return Request<GetCalculateDataByModelIdRequest, GetCalculateDataByModelIdResponse>({
        url: "cdpclient/analysisconfig/getCalculateDataByModelId",
        method: "get",
        data
    })
}

// 删除当前分析
export const removeOverviewWidget: RemoveOverviewWidget.RequestFunc = (data) => {
    return Request<RemoveOverviewWidget.Request, RemoveOverviewWidget.Response>({
        url: "cdpclient/analysisconfig/removeOverview",
        method: "post",
         data
    })
}

// 根据概览Id获取分析模型
export const getModelListByOverviewId: GetModelListByOverviewId.RequestFunc = (data) => {
    return Request<GetModelListByOverviewId.Request, GetModelListByOverviewId.Response>({
        url: "cdpclient/analysisconfig/getDetail",
        method: "get",
        data
    })
}