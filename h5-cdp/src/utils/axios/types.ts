import type { AxiosResponse, AxiosRequestConfig } from "axios";

export interface RequestInterceptors<T> {
    // 请求拦截
    requestInterceptors?: (config: AxiosRequestConfig) => AxiosRequestConfig
    requestInterceptorsCatch?: (err: any) => any
    // 响应拦截
    responseInterceptors?: <T = AxiosResponse>(config: T) => T
    responseInterceptorsCatch?: (err: any) => any
}
// 自定义传入的参数
export interface RequestConfig<T = AxiosResponse> extends AxiosRequestConfig {
    // interceptors?: RequestInterceptors
    interceptors?: RequestInterceptors<T>
}
