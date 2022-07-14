<!-- 登录页面 -->
<template>
    <div class="login-container">
        <div class="content">
            <div class="header"></div>
            <a-form ref="login_from" :rules="login_from_rules" :model="login_from_data"
                :label-col-props="{ span: 0, offset: 0 }" :wrapper-col-props="{ span: 24, offset: 0 }">
                <a-form-item field="username">
                    <a-input v-model="login_from_data.username" placeholder="请输入用户名"></a-input>
                </a-form-item>
                <a-form-item field="password">
                    <a-input-password v-model="login_from_data.password" placeholder="请输入登录密码"></a-input-password>
                </a-form-item>
                <a-form-item>
                    <a-button type="primary" long @click="SubmitLoginFunc">登录</a-button>
                </a-form-item>
            </a-form>
        </div>
    </div>
</template>

<script lang='ts' setup>
import { reactive } from 'vue';
import { useRouter } from "vue-router"
import { login } from "_apis/login"
import type { Router } from "vue-router"
import type { LoginRequestData } from "_types/login"
// 路由实例
const router: Router = useRouter()
// 登录所需参数
const login_from_data: LoginRequestData = reactive({
    username: "",
    password: "",
    captcha: "",
    uuid: "",
});
// from ref
const login_from = ref();
// 校验登录数据
const login_from_rules: FromRules = {
    username: [
        {
            required: true,
            message: "请输入用户名"
        }
    ],
    password: [
        {
            required: true,
            message: "请输入登录密码"
        }
    ],
};
// 登录
const SubmitLoginFunc = () => {
    login_from.value.validate((validate: undefined | FromRules) => {
        if (!validate) {
            login(login_from_data).then(res => {
                sessionStorage.setItem('ut', res.token)
                sessionStorage.setItem('userId', res.userId + "")
                router.push({
                    path: "/overView/1"
                });
                localStorage.setItem("username", login_from_data.username);
            })
        }
    });
};
</script>

<style lang='less' scoped>
.login-container {
    height: 100%;
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgb(239, 240, 238);

    &>div.content {
        height: auto;
        width: 350px;
        background-color: #fff;
        box-sizing: border-box;
        padding: 15px;
        border-radius: 4px;

        &>.header {
            height: 64px;
            margin-bottom: 15px;
            background-image: url("@/assets/logo.png");
            background-size: 100% 100%;
            background-repeat: no-repeat;
            // background-position: 0 12px;
            background-position: 0 0;
        }
    }
}
</style>
