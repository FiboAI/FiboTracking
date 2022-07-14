<!-- 小组件的顶部栏 -->
<template>
    <div class="widget-topbar">
        <div class="left">
            <div class="title" @click="editModel">{{ props.name }}</div>
            <div class="range">
                {{ parseTime(props.startTime) }}~{{ parseTime(props.endTime) }}
            </div>
        </div>
        <div class="tools">
            <a-dropdown position="br" v-if="toolsShow" @select="dropdownSelectFunc">
                <icon-unordered-list />
                <template #content>
                    <a-doption value="set">编辑</a-doption>
                    <a-doption value="delete">删除</a-doption>
                </template>
            </a-dropdown>
            <!-- <icon-delete class="delete-btn" /> -->
        </div>
    </div>
</template>

<script lang='ts' setup>
import useDate from '@/hooks/useDate';
const props = defineProps<{
    startTime: string
    endTime: string
    name: string
    toolsShow: boolean
}>()

const emits = defineEmits<{
    (event: 'delete'): void
    (event: 'set'): void
}>()

const { parseTime } = useDate()

const dropdownSelectOption = new Map([
    ['delete', (): void => emits('delete')],
    ['set', (): void => emits('set')]
])
const editModel = () => {
  emits('set');
}
const dropdownSelectFunc = (value: unknown) => {
    let action = dropdownSelectOption.has(<string>value) && dropdownSelectOption.get(<string>value) || null
    action && action()
}
</script>

<style lang='less' scoped>
.title:hover{
  text-decoration:underline;
  cursor:pointer;
}
div.widget-topbar {
    width: 100%;
    display: flex;

    &>div {
        &.left {
            flex: auto;

            &>div {
                &.title {
                    white-space: nowrap;
                    width: 100%;
                    font-size: 14px;
                    font-weight: 400;
                    color: #5f6e82;
                    height: 25px;
                }

                &.range {
                    font-size: 12px;
                    color: #8492a6;
                    white-space: nowrap;
                    overflow: hidden;
                    text-overflow: ellipsis;
                }
            }
        }

        &.tools {
            &>.delete-btn {
                color: @checked-border-color;
            }
        }
    }
}
</style>
