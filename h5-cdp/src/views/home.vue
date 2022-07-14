<template>
    <a-layout class="home-container">
        <a-layout-header>
            <div class="left"></div>
            <div class="right">
                <div class="user">
                    <span style="font-size: 16px;">{{ username }}</span>
                    <a-dropdown trigger="hover" style="width: 40px;">
                        <icon-user class="userIcon" />
                        <template #content>
                            <a-doption>
                                <a-button type="text" @click="logout">退出登录</a-button>
                            </a-doption>
                            <!-- <a-doption>
                                <a-button type="text">修改密码</a-button>
                            </a-doption> -->
                        </template>
                    </a-dropdown>
                </div>
            </div>
        </a-layout-header>
        <div class="view">
            <div class="navigation">
                <a-menu :style="{ width: '210px', height: '100%' }" @menu-item-click="menuItemClick">
                    <template v-for="value in navigationBar">
                        <a-menu-item v-if="!value.sub" :key="value.key">{{ value.title }}</a-menu-item>
                        <a-sub-menu v-else :key="value.key + ''">
                            <template #icon>
                                <icon-computer v-if="value.isComputer" />
                                <icon-bar-chart v-if="value.isChart" />
                                <icon-idcard v-if="value.isIdcard" />
                                <icon-nav v-if="value.isNav" />
                                <icon-desktop v-if="value.isDesktop" />
                                <icon-relation v-if="value.isRelation" />
                            </template>
                            <template #title>
                                <span style="font-size: 16px;">{{ value.title }}</span>
                            </template>
                            <a-menu-item v-for="item in value.sub" :key="item.key" @sub-menu-click="menuItemClick"
                                :disabled="item.disabled">
                                <span style="font-size: 16px;padding-left: 14px;">{{ item.title }}</span>
                            </a-menu-item>
                        </a-sub-menu>
                    </template>
                </a-menu>
            </div>
            <router-view :key="$route.fullPath" />
        </div>
    </a-layout>
</template>

<script lang="ts" setup>
import { RouterView, useRouter, useRoute } from "vue-router";
import eventAnalysisStore from '@/stores/useEventList';
import {
    getMenuNav,
} from "_apis/admin";

const router = useRouter();
const route = useRoute();
// 提前获取事件列表
const Store = eventAnalysisStore();
Store.getEventlist();
const navigationBar = reactive<any>([]);
let username = localStorage.getItem('username');
const menuItemClick = (e: string) => {
    router.push({
        path: e
    })
}
const logout = () => {
    router.push({
        path: '/'
    })
}
const getMenu = () => {
    getMenuNav().then((res: any) => {
        res.datas.menuList.forEach((item: any) => {
            item.key = item.key.toString()
        });
        navigationBar.push(...res.datas.menuList);
    })
}
getMenu()
// const navigationBar = [
//     {
//         title: '数据大盘',
//         key: '2',
//         isComputer: true,
//         sub: [
//             {
//                 title: '公共概览',
//                 key: '/overView/1',
//                 disabled: false,
//             },
//             {
//                 title: '我的概览',
//                 key: '/overview/home',
//                 disabled: false,
//             },
//         ]
//     },
//     {
//         title: '数据分析',
//         key: '3',
//         isChart: true,
//         sub: [
//             {
//                 title: '事件分析',
//                 key: "/analysis/event",
//                 disabled: false,
//             }, {
//                 title: '漏斗分析',
//                 key: "/analysis/funnel",
//                 disabled: false,
//             }, {
//                 title: '留存分析',
//                 key: "/analysis/remain",
//                 disabled: false,
//             }, {
//                 title: '归因分析',
//                 key: "归因分析",
//                 disabled: true,
//             }, {
//                 title: 'LTV分析',
//                 key: "LTV分析",
//                 disabled: true,
//             }, {
//                 title: '自定义查询',
//                 key: "/analysis/customQueries",
//                 disabled: false,
//             },
//         ]
//     },
//     {
//         title: '画像分析',
//         key: '4',
//         isIdcard: true,
//         sub: [
//             {
//                 title: '用户画像',
//                 disabled: true,
//                 key: "用户画像",
//             }, {
//                 title: '用户标签',
//                 disabled: true,
//                 key: "用户标签",
//             },
//         ]
//     },
//     {
//         title: '数据管理',
//         key: '5',
//         isNav: true,
//         sub: [
//             {
//                 title: '事件管理',
//                 disabled: false,
//                 key: "/data/eventManagement",
//             }, {
//                 title: '属性管理',
//                 disabled: false,
//                 key: "/data/propertyManagement",
//             },
//         ]
//     },
//     {
//         title: '平台管理',
//         key: '6',
//         isDesktop: true,
//         sub: [
//             {
//                 title: '账号管理',
//                 disabled: false,
//                 key: "/admin/accountManagement",
//             },
//             {
//                 title: '角色管理',
//                 disabled: false,
//                 key: "/admin/roleManagement",
//             },
//         ]
//     },
//     {
//         title: '数据回传',
//         key: '7',
//         isRelation: true,
//         sub: [
//             {
//                 title: '回传管理',
//                 key: "/passback/management",
//                 disabled: false,
//             }, {
//                 title: '回传策略',
//                 key: "/passback/strategies",
//                 disabled: false,
//             }, {
//                 title: '回传记录',
//                 key: "/passback/records",
//                 disabled: false,
//             }
//         ]
//     },
// ]
</script>

<style scoped lang="less">
.user {
    position: absolute;
    top: 50%;
    right: 20px;
    transform: translate(-10px, -50%);
    font-size: 25px;
    display: flex;
    align-items: center;

    &>.userIcon {
        margin-left: 5px;
    }
}

.arco-layout {
    height: 100%;
    width: 100%;
    overflow: hidden;

    .arco-layout-header {
        height: 64px;
        display: flex;

        background-color: #fff;
        position: relative;
        z-index: 100;

        &>div {
            &.right {
                flex: auto;
                box-shadow: 0 2px 4px rgb(175 186 200 / 20%);

                :deep(.arco-menu) {
                    font-size: 17px !important;
                    color: #fff;

                    .arco-menu-inner {
                        padding: 14px 0;
                    }
                }
            }

            &.left {
                width: 210px;
                background-image: url("@/assets/logo.png");
                background-size: contain;
                background-repeat: no-repeat;
                background-position: center 4px;
                transform: translate(-20px, 10px) scale(1.2, 1.2);
                overflow: hidden;
            }
        }
    }
}

.view {
    display: flex;
    height: calc(100vh - 64px);

    &>.navigation {
        width: 210px;
        background-color: #fff;
        box-shadow: 6px 2px 10px rgb(175 186 200 / 20%);
    }
}
</style>
