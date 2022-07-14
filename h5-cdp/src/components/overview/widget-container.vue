<!-- 组件的容器 -->
<template>
    <div class="widget-container" :data-size="size">
        <a-spin dot :loading="loading">
            <div class="widget-container-content">
                <slot name="header">
                    <widget-topbar :tools-show="tools_flag" v-bind="$attrs" @delete="deleteWidgetFunc" @set="setWidgetFunc" />
                </slot>
                <slot />
            </div>
        </a-spin>
    </div>
</template>

<script lang='ts' setup>
import { removeOverviewWidget } from "_apis/overview"
import type { RemoveOverviewWidget } from "_types/overview"
import useMessage from "@/hooks/useMessage";
import useAnalysisListStore from '@/stores/useAnalysisList'

const props = withDefaults(defineProps<{
    size: 'normal' | 'small' | 'large'
    loading: boolean
    widgetId: number
    onRefresh: (model:number) => void
    modelType:number
}>(), {
    size: 'normal',
    loading: false,
});

const emits = defineEmits<{
    (event: 'on-refresh'): void
}>()

const { size, loading } = toRefs(props)

const { MessageSuccess } = useMessage();

let tools_flag = ref<boolean>(true)

const deleteWidgetFunc = () => {
    let params: RemoveOverviewWidget.Request = {
        modelId: props.widgetId
    }
    removeOverviewWidget(params).then(res => {
        MessageSuccess('操作成功')
        emits('on-refresh');
        props.onRefresh(props.widgetId)
    })
}

const router = useRouter()
const Store = useAnalysisListStore()
const setWidgetFunc = ()=>{
    let analysis = Store.$state.analysis_list.find(x=>x.modelType==props.modelType)
    router.push({
        path: analysis&&analysis.key,
        query: {
            model: props.widgetId
        }
    })
}

</script>

<style lang='less' scoped>
.widget-container {
    padding: 10px;
    box-sizing: border-box;
    float: left;
    box-sizing: border-box;
    cursor: pointer;

    &[data-size='small'] {
        width: 25%;
        height: 182px;
    }

    &[data-size='mini'] {
        width: 25%;
        height: 182px;
    }

    &[data-size='normal'] {
        width: 50%;
        height: 364px;
    }

    &[data-size='large'] {
        width: 100%;
        height: 364px;
    }

    &>div {
        width: 100%;
        height: 100%;
        box-shadow: @widget-container-shadow;
        background-color: #fff;
        border-radius: 4px;
        border: 2px #fff solid;
        padding: 14px 20px;
        box-sizing: border-box;
        border: 2px solid transparent;

        &:hover {
            border-color: rgb(197, 80, 63);
        }

        :deep(.arco-spin-mask) {
            background-color: #fff;
        }

        &>div.widget-container-content {
            height: 100%;
        }
    }
}
</style>
