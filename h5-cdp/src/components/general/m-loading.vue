<!-- 全局页面统一loading -->
<template>
    <a-spin :size="42" :loading="props.loading" tip="加载中">
        <slot />
    </a-spin>
</template>

<script lang='ts' setup>
interface Props {
    loading?: boolean
    tip?: string
}

const props = withDefaults(defineProps<Props>(), {
    loading: false,
    tip: "加载中"
})
</script>

<style lang='less' scoped>
.arco-spin {
    height: 100%;
    width: 100%;

    :deep(.arco-spin-mask) {
        background-color: @body-color;
    }
}
</style>