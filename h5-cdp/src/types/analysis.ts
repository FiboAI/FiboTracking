export type EventGroupData = {
    name: string
    eventDataDtoList: Array<{
        id: number
        nameCn: string
        nameEn: string
        isVirtual: number
    }>
}[]

export type PropertyList = {
    propertyId: number
    nameCn: string
    nameEn: string
    fieldType: string
    remarks: string | number | boolean
}[]

export interface CalculateData {
    dates?: Array<{[key: string]: number}>|string
    [key: string]: string | number | undefined |Array<{[key: string]: number | object}>
    total?:string
}
export interface CalculateData1 {
    dates?: Array<{[key: string]: number}> | string
    [key: string]: string | number | Datas | undefined | Array<{[key: string]: number | object}>
    code?: string
    msg?: string
}
type Datas = {
    data: Array<{[key: string]: number}> | string
    columns: Array<{[key: string]: number}> | string
}[]

export interface PropertyListRequest {
    eventNameEn?: string
    isGroupView?: number
}

export interface CalculateDataRequest {
    id?: number | string
    type: number            //分析模型类型
    timeGranularity: string //事件粒度
    startTime: string       //开始事件
    endTime: string         //结束时间
    selectPropertyParams?: SelectPropertyParams[]
    wherePropertyParams: WherePropertyParams[]
    groupPropertyParams?: GroupPropertyParams[]
    funnelId?:number
    keepEventParam?: KeepEventParam
    timeValue?: number
    name?: string
}

export interface SavaEventAnalysisRequest {
    id?: number
    name: string
    startTime: string
    endTime: string
    chartType?: number
    isAvg?: number
    isSum?: number
    isYearToYear?: number
    overviewId: number
    timeGranularity?: string
    type: number
    windowSize?: string
    selectPropertyParams?: SelectPropertyParams[]
    wherePropertyParams?: WherePropertyParams[]
    groupPropertyParams?: GroupPropertyParams[]
    funnelId?:number
}

export interface SelectPropertyParams {
    title?: string
    calcType: string
    eventEn: string
    propertyEn:string
    isVirtual: number
    name: string
    sort: string
}

export interface RemainList {
    label: string
    value: number
    children: Array<{
        is_virtual: number
        label: string
        value: string
    }>
}

export interface WherePropertyParams {
    propertyEn: string
    fieldType: string
    conditions: string
    value?: string | number | boolean
    maxValue?: number
}

export interface GroupPropertyParams {
    propertyEn: string,
    propertyCn: string
}

export interface KeepEventParam {
    firstEventEn: string,
    lastEventEn: string,
    firstIsVirtual: number,
    lastIsVirtual: number,
}
// 定义请求体的类型

// 定义请求返回值的类型
export type EventGroupResponse = MResponse<EventGroupData>

export type PropertyResponse = MResponse<PropertyList>

export type CalculateDataResponse = MResponse<CalculateData[]>

export interface CalculateDataResponseAddTotil extends CalculateDataResponse {
    total : {
        [key: string]:  number
    },
    times:string[],
    columns: []
}


export type SaveOrUpdateEventAnalysisResponse = MResponse<null>

// 定义请求方法类型
export interface GetGroupEventList {
    (): Promise<EventGroupResponse>
}

export interface GetPropertyList {
    (data?: PropertyListRequest): Promise<PropertyResponse>
}

export interface GetCalculateData {
    (data: CalculateDataRequest): Promise<CalculateDataResponseAddTotil>
}

export interface EventAnalysisSaveOrUpdate {
    (data: SavaEventAnalysisRequest): Promise<SaveOrUpdateEventAnalysisResponse>
}
export interface GetCalculateData1 {
    (data: QueriesDataRequest): Promise<CalculateData1>
}

// ==========漏斗部分

export interface  funnelStepDataEntity{
    eventEn?:string,
    funnelId?:number,
    name?:string,
    sort?:number,
    isVirtual?:number
}
// 漏斗信息
export interface AddfunnelData{
    id?:number,
    name?:string,
    windowPeriod?:number,
    funnelStepDataEntityList?: funnelStepDataEntity[],
}
export type AddfunnelDataResponse = MResponse<{
    [key: string]:object
}>

export interface FunnelSaveOrUpdateFunnel {
    (data: AddfunnelData): Promise<AddfunnelDataResponse>
}

// export type FunnelList = {
//     delFlag: '',
//     funnelStepDataEntityList: []|null,
//     id: number,
//     name: string,
//     remarks: string|null
//     status: number
//     windowPeriod: number
// }[]
export type FunnelList = AddfunnelData[]
export type getFunnelListDataResponse = MResponse<FunnelList>

export interface GetFunnelListData {
    (data?:undefined): Promise<getFunnelListDataResponse>
}


// 回传
export interface DetailData {
    key: string | number,
    name: string,
    time: string,
    mode: string,
    status: string,
    detail: string
}

// 自定义查询
export interface QueriesDataRequest {
    id?: number             //分析模型ID，修改时使用
    overviewId?: number     //概览ID
    name?: string           //分析模型名称
    type: number            //分析模型类型 自定义为3
    startTime: string       //开始事件
    endTime: string         //结束时间
    metadataCustomsqlData?: MetadataCustomsqlData //自定义数据对象
}

export interface MetadataCustomsqlData {
    querySql: string        //sql语句
}
