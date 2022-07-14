import MRequest from "./axios";
import type { RequestConfig } from "./types";

interface MRequstConfig<T, R> extends RequestConfig<R> {
    data?: T
}
const request = new MRequest({
    baseURL: import.meta.env.VITE_BASE_URL,
    timeout: 1000 * 60 * 5,
    interceptors: {
        requestInterceptors: config => {
            const token: string | null = sessionStorage.getItem('ut');
            if (!!token) {
                config.headers && (config.headers.token = token)
            }
            return config
        },
        responseInterceptors: (result) => {
            return result
        }
    }
})

const Request = <T = any, R = any>(config: MRequstConfig<T, R>) => {
    const { method = 'GET' } = config
    
    if (method === 'get' || method === 'GET') {
        config.params = config.data
    }
    return request.request(config)
}

export default Request

