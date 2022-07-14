<!-- 回传 -->
<template>
  <a-layout class="analysis-content">
    <a-layout-content>
      <router-view :key="$route.fullPath"></router-view>
    </a-layout-content>
  </a-layout>
</template>

<script lang='ts' setup>
import AnalysisList from '@/stores/useAnalysisList'

// 左侧菜单列表
const Store = AnalysisList()
let { analysis_list } = reactive(Store)
// console.log(analysis_list,Store)
// 路由的实例
const route = useRoute();

const router = useRouter()
const goNextPage = (e: string) => {
  router.push({
    path: e
  })
}
// 获取当前路由的name作为默认的key
const router_path = computed(() => <string>route.path || '')
// 默认选中的菜单
const default_selected_keys = reactive<string[]>([
  router_path.value
])
// 切换选中事件分析
const checkoutEventFunc = (key: string): void => {

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
    box-sizing: border-box;
    height: 100%;
    overflow: hidden;
  }
}
</style>