<!-- 概览页面 -->
<template>
    <a-layout-content>
        <Dashboard v-model:overview-id="overview_id" @editor="editorOverviewFunc" />
    </a-layout-content>
    <add-overview-modal ref="add_overview" />
</template>

<script lang='ts' setup>
import { AddOverviewModal } from "_components/overview";

import Dashboard from "./dashboard.vue"
import type { OverviewItem } from "_types/overview"
const router = useRouter();
const route = useRoute();

// console.log(route.params.overviewId) 

// 编辑弹窗的ref实例
const add_overview = ref()
// 记录当前被选中的概览id
let overview_id = ref<string | number>(route.params.overviewId ? <string>route.params.overviewId : '1')
// 菜单默认选中
let default_selected_keys = reactive<string[]>([
    route.params.overviewId ? <string>route.params.overviewId : '1'
])


// 概览头部修改概览信息
const editorOverviewFunc = (title: string): void => {
    let overview_details: OverviewItem = {
        id: Number(overview_id.value),
        name: title
    };
    add_overview.value.openFunc(overview_details)
}
// 打开新增弹窗
const openAddModalFunc = (data: null | OverviewItem) => {
    add_overview.value.openFunc()
}
// 切换预览
const checkoutOverviewFunc = (key: string) => {
    router.push('/overview/' + key)
    overview_id.value = key
    default_selected_keys.length = 0;
    default_selected_keys.push(key)
}


</script>

<style lang='less' scoped>
.arco-layout {
    height: calc(100% - 64px);

    &>.arco-layout-sider {
        background-color: #fff;
        box-sizing: border-box;
        padding: 5px 0;

        &>.arco-layout-sider-children {
            &>.arco-menu {
                height: calc(100% - 37px);
            }

            &>.arco-btn {
                width: 80%;
                margin: 0 auto;
            }
        }
    }

    &>.arco-layout-content {
        padding: 10px 0 10px 10px;
        height: 100%;
        box-sizing: border-box;
    }
}
</style>