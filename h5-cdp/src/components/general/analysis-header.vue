<!-- 分析头部 -->
<template>
    <div class="analysis-header">
        <div class="title">{{ props.title }}</div>
        <div class="tools">
            <a-button type="text" @click="emits('on-save')">{{set?'更新':'保存'}}</a-button>
        </div>
    </div>
</template>

<script lang='ts' setup>
const props = defineProps<{
    title: string
    set:boolean
}>()

const emits = defineEmits<{
    (event: 'on-save'): void
}>()
</script>

<style lang='less' scoped>
.analysis-header {
    height: @analysis-header-height + 16px;
    box-sizing: border-box;
    padding: 10px 16px 26px;
    display: flex;
    align-items: center;

    &>div {
        &.title {
            font-size: 22px;
            font-weight: bold;
            flex: auto;
        }

        &.tools {
            justify-content: right;

            &>button {
                margin-right: 5px;

                &:last-child {
                    margin-right: 0;
                }
            }
        }
    }
}
</style>