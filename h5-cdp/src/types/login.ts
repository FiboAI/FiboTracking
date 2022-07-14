export interface LoginRequestData {
    username: string
    password: string
    captcha: string
    uuid: string | number
}

export interface LoginResponseData extends MResponse<any> {
    token: string
    expire: number,
    userId:number
}
