<template>
    <div class="main">
        <div class="title">
            <overview-header title="我的概览" />
        </div>
        <a-button type="primary" @click="openAddModalFunc(null)" shape="circle" size="large" class="addOverView">
            <icon-plus />
        </a-button>
        <div class="overview_home">
            <div v-for="(value, index) in  overview_list" class="overview" @click="goOverVoew(value)" :key="index">
                <div class="left">
                    <div class="report">
                        <icon-bookmark :size="26" />
                    </div>
                    <div class="text">{{ value.name }}</div>
                </div>
            </div>
        </div>
    </div>
    <add-overview-modal ref="add_overview" @on-refrsh="getDataListFunc" />

</template>

<script lang="ts" setup>
import { getOverviewList } from "_apis/overview"
import type { OverviewItem } from "_types/overview"
import { AddOverviewModal } from "_components/overview";
const router = useRouter();
const route = useRoute();

// 概览列表
let overview_list = reactive<OverviewItem[]>([])

const goOverVoew = (value: OverviewItem) => {
    router.push('/overView/' + value.id)
}

// 获取所有概述
const getDataListFunc = (id?: number | string): void => {
    getOverviewList({}).then(res => {
        overview_list.length = 0;
        overview_list.push(...<OverviewItem[]>res.datas);
    }).catch(err => {

    })
}

// 编辑弹窗的ref实例
const add_overview = ref()
// 打开新增弹窗
const openAddModalFunc = (data: null | OverviewItem) => {
    add_overview.value.openFunc()
}
let url = ref<string>('')
onMounted(() => {
    getDataListFunc()
    url.value = window.location.href
    // console.log(url.value);
})
watch(url, (newValue, oldValue) => {
    // console.log('watch 已触发', newValue, 'oldValue',oldValue)
})

</script>

<style scoped lang="less">
.main {
    width: 100%;
    overflow: scroll;
}

.overview_home {
    display: inline-flex;
    flex-wrap: wrap;

    &>.overview {
        width: 200px;
        height: 100px;
        background-color: #ffffff;
        margin: 30px;
        // font-weight: bold;
        font-size: 16px;
        box-shadow: 10px 10px 4px rgb(175 186 200 / 20%);
        // cursor: pointer;
    }

    &>.overview:hover {
        margin-top: 20px;
        transition: all .2s;
    }

}

.left {
    // background: red;
    height: 30px;
    width: 100%;
    display: flex;
    margin-top: 40px;
    position: relative;

    &>.report {
        width: 30px;
        height: 30px;
        margin: -5px 10px;
    }

    &>.text {
        display: inline-block;
        width: 140px;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
    }

    &>.rightIcon {
        height: 20px;
        width: 20px;
        position: absolute;
        right: 0;
    }
}

.addOverView {
    position: fixed;
    bottom: 150px;
    right: 150px;
}
</style>