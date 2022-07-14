<!-- 概览内容区域 -->
<template>
    <div class="overview-content">
        <div>
            <slot></slot>
            <br clear="left" />
        </div>
    </div>
</template>

<script lang="ts" setup>
interface Props {
    loading?: boolean
}
const props = withDefaults(defineProps<Props>(), {
    loading: true
})

</script>

<style lang='less' scoped>
.overview-content {
    height: calc(~'100% - @{overview-height}');
    overflow: hidden;

    &>div {
        height: 100%;
        width: 100%;
        overflow: auto;
    }
}
</style>