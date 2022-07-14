import type {CalculateDataResponse, SavaEventAnalysisRequest, SelectPropertyParams} from "./analysis"
import type { TableColumnData } from "@arco-design/web-vue"
export interface OverviewItem {
    name: string,
    id?: number
}

export interface OverviewDetailsRequest {
    overviewId: number | string
}

export interface OverviewDetailsResponseData {
    id: number
    windowSize: string
    isAvg?: number
    isSum?: number
    isYearToYear?: number
    chartType: number
    type:number
    startTime: string
    endTime: string
    name: string
}

export interface OverviewDetailsResponse extends MResponse<OverviewDetailsResponseData[]> {
    name: string
}

export interface GetCalculateDataByModelIdResponse extends CalculateDataResponse {
[x: string]: any
    nowMap: GetCalculateDataByModelId.NowMap
    preChainMap: GetCalculateDataByModelId.PreChainMap
    preYearToYearMap: GetCalculateDataByModelId.PreYearToYearMap,
    modelData:GetCalculateDataByModelId.modelData,
    times:string[],
    columns: TableColumnData[]
}

export interface GetCalculateDataByModelIdRequest {
    modelId: number | string
    startTime: string
    endTime: string
}
// =====

export type OverviewList = MResponse<OverviewItem[]>

// =====

export interface GetOverviewDetails {
    (data: OverviewDetailsRequest): Promise<OverviewDetailsResponse>
}

export interface AddOverview {
    (data: OverviewItem): Promise<MResponse<any>>
}

export interface GetOverviewList {
    (data: object): Promise<OverviewList>
}

export interface GetCalculateDataByModelId {
    (data: GetCalculateDataByModelIdRequest): Promise<GetCalculateDataByModelIdResponse>
}


// 后期每个接口都要创建一个对应的namespace
export namespace GetCalculateDataByModelId {
    export interface Request {
        modelId: number | string
        startTime: string
        endTime: string
    }
    export interface NowMap {
        nowDate: string
        nowDateValue: number
    }
    export interface PreChainMap {
        preChainRate: number
        preChainDate: string
        preChainValue: number
        text:string
    }
    export interface PreYearToYearMap {
        preYearToYearRate: number
        preYearToYearDate: string
        preYearToYearValue: number
        text:string
    }
    export interface modelData{
        timeGranularity:string
        selectPropertyParams:SelectPropertyParams[]
        chartType:number
    }
}

// 删除概览小组件
export namespace RemoveOverviewWidget {
    export interface Request {
        modelId: number
    }

    export interface ResponseDatas {

    }

    export type Response = MResponse<ResponseDatas>

    export interface RequestFunc {
        (data: Request): Promise<Response>
    }
}

// 删除概览
export namespace RemoveOverview {
    export interface Request {
        overviewId: number
    }

    export interface ResponseDatas {

    }

    export type Response = MResponse<ResponseDatas>

    export interface RequestFunc {
        (data: Request): Promise<Response>
    }
}
// 更新概览图表
export namespace GetModelListByOverviewId {
    export interface Request {
        modelId: number
    }

    export interface ResponseDatas  extends  SavaEventAnalysisRequest{}

    export type Response = MResponse<ResponseDatas>

    export interface RequestFunc {
        (data: Request): Promise<Response>
    }
}
