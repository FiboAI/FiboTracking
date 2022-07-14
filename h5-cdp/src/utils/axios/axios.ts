import myapp from "@/main"
import axios, { AxiosResponse } from "axios";
import type { AxiosInstance, AxiosRequestConfig } from "axios"
import type { RequestInterceptors, RequestConfig } from "./types"
import { Message } from "@arco-design/web-vue";
import "@arco-design/web-vue/es/message/style/css.js"
import { routerKey } from "vue-router";
import router from '@/routers'


Message._context = myapp._context

const ResponseCodeMaps = {
    301: "",
    500: "",
    401: "",
    402: "",
    403: "",
    404: "路径不存在",
    405: "接口HTTP协议错误",
    406: "参数错误",
    8001: "概览不存在",
    8002: "无权操作",
}

class Request {
    instance: AxiosInstance
    interceptorsObj?: RequestInterceptors<AxiosResponse>

    constructor(config: RequestConfig) {
        this.instance = axios.create(config)
        this.interceptorsObj = config.interceptors

        this.instance.interceptors.request.use((res: AxiosRequestConfig) => {
            return res
        }, (err: any) => err)

        this.instance.interceptors.request.use(
            this.interceptorsObj?.requestInterceptors,
            this.interceptorsObj?.requestInterceptorsCatch,
        )

        this.instance.interceptors.response.use(
            this.interceptorsObj?.responseInterceptors,
            this.interceptorsObj?.requestInterceptorsCatch,
        )

        this.instance.interceptors.response.use(
            (res: AxiosResponse) => {
                if (res.data.code == 0) {
                    return res.data
                } else {
                    Message.error(`${res.data.code}:${res.data.msg}`)
                    if(res.data.code == 401){
                        router.push('/login')
                    }
                    return Promise.reject(new Error(res.data.code))
                }
            },
            (err: any) => err
        )
    }

    request<T>(config: RequestConfig<T>): Promise<T> {
        return this.instance.request<any, T>(config)
    }
}

export default Request