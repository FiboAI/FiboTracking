<!-- 概览头部 -->
<template>
    <div class="overview-header">
        <div class="title">
            <span>{{ props.title }}</span>
            <a-button v-show="editor" class="editor-btn" size="mini" type="text" @click="editorFunc">
                <icon-edit :size="18" />
            </a-button>
        </div>
        <div class="tools">
<!--            <a-button size="mini" type="primary" status="success" @click="emits('on-refresh')">-->
<!--                <icon-refresh />-->
<!--            </a-button>-->
            <a-button size="mini" v-show="editor" type="primary" @click="emits('on-add-component')">
                <icon-plus />
            </a-button>
            <!-- 删除按钮 -->
            <a-popconfirm type="error" content="确定需要删除当前概览?" position="left" @ok="deleteFunc">
                <a-button v-show="editor" size="mini" type="primary" status="danger">
                    <icon-delete />
                </a-button>
            </a-popconfirm>
        </div>
    </div>
</template>

<script lang='ts' setup>
import {Ref} from 'Vue'
const props = defineProps<{
    title: string
    editor: boolean
    onEditor: (title: string) => void
}>()
const emits = defineEmits<{
    (event: "on-refresh"): void
    (event: "on-delete"): void
    (event: "on-add-component"): void
}>()
const deleteFunc = (): void => {
    emits('on-delete')
}
const editorFunc = (): void => {
    props.onEditor(props.title)
}
</script>

<style lang='less' scoped>
.overview-header {
    height: @overview-height;
    display: flex;
    align-items: center;
    padding: 10px 10px 10px 10px;
    box-sizing: border-box;

    &>div {
        &.title {
            font-size: 22px;
            font-weight: bold;
            flex: auto;

            &>.editor-btn {
                &:hover {
                    background-color: transparent;
                }
            }
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
