import Request from "_utils/axios";
import type { LoginRequestData, LoginResponseData } from "_types/login";

// 登录接口
export const login = (data: LoginRequestData) => {
    return Request<LoginRequestData, LoginResponseData>({
        url: "sys/login",
        method: "post",
        data
    })
}