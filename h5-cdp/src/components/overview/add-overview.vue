<!-- 添加概览 -->
<template>
    <a-modal v-model:visible="visible" title-align="start" :unmount-on-close="true" :closable="false">
        <template #title>
            新建/编辑概览
        </template>
        <a-form ref="overview_form" :rules="overview_form_rules" :model="overview_form_data">
            <a-form-item field="name" label="概览名称">
                <a-input v-model="overview_form_data.name" placeholder="请输入概览名称"></a-input>
            </a-form-item>
        </a-form>
        <template #footer>
            <div>
                <a-button style="margin-right: 10px;" @click="cancelFunc">取消</a-button>
                <a-button :loading="save_loading" type="primary" @click="confirmFunc">确定</a-button>
            </div>
        </template>
    </a-modal>
</template>

<script lang='ts' setup>
import { Ref } from "vue";
import { addOverview } from "_apis/overview"
import useMessage from "_hooks/useMessage";
import type { OverviewItem } from "_types/overview";
const { MessageSuccess } = useMessage();
const emits = defineEmits<{
    (event: "on-refrsh"): void
}>()
// form的实例
const overview_form = ref();
// 保存的时候loading
let save_loading: Ref<boolean> = ref(false);
// 控制弹窗是否显示
let visible: Ref<boolean> = ref(false);
// 概览的详情
const overview_form_data = reactive<OverviewItem>({
    name: ""
})
// 校验概览内容
const overview_form_rules: FromRules = {
    name: [
        {
            required: true,
            message: "请输入概览名称"
        }
    ]
}
// 提供父组件一个方法打开弹窗
const openFunc = (data: null | OverviewItem): void => {

    if (data) {
        overview_form_data.name = data.name;
        overview_form_data.id = data.id;
    }

    visible.value = true
}
// 提交编辑框内容
const confirmFunc = (): void => {
    overview_form.value.validate((err: FormValidateErr) => {
        if (!err) {
            save_loading.value = true;
            addOverview(overview_form_data).then(res => {
                visible.value = false;
                save_loading.value = false;
                MessageSuccess("操作成功");
                location.reload();
                emits('on-refrsh');
            }).catch(err => {
                save_loading.value = false;
            })
        }
    })
}
// 关闭弹窗的事件
const cancelFunc = (): void => {
    visible.value = false;
    save_loading.value = false;
    overview_form_data.name = "";
    overview_form_data.id = undefined;
    overview_form.value.clearValidate()
}
// 提供给父级使用
defineExpose({
    openFunc
})
</script>

<style lang='less' scoped>
</style>