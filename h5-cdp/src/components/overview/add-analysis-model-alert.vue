<!-- 添加概览 -->
<template>
    <a-modal v-model:visible="visible"
             :footer="false"
             title-align="start" :unmount-on-close="true" :closable="false">
        <template #title>
            新建组件
        </template>
      <a-row :gutter="20">
        <template v-for="(item, index) in analysisModelBaseData" :key="index">
          <a-col :span="8">
            <a-card hoverable class="redio-card" @click="linkModelPath(item.path)">
              <icon-font :size="18" :type="item.icon" />
              <div>{{item.name}}</div>
            </a-card>
          </a-col>
        </template>
      </a-row>

    </a-modal>
</template>

<script lang='ts' setup>
import MIconFont from "_hooks/useIconFont"
import { Ref } from "vue";
import useMessage from "_hooks/useMessage";
import type { OverviewItem } from "_types/overview";
const { MessageSuccess } = useMessage();
const router = useRouter();
const IconFont = MIconFont();
const emits = defineEmits<{
    (event: "on-refrsh"): void
}>()
let visible: Ref<boolean> = ref(false);
// 概览的详情
const overview_form_data = reactive<OverviewItem>({
    name: ""
})
const analysisModelBaseData = reactive<Array<{type:number,name:string,icon:string,path:string}>>([{
  type:0,
  name:"事件分析",
  icon:"icon-bianzu17",
  path:"/analysis/event"
},{
  type:1,
  name:"漏斗分析",
  icon:"icon-loudoufenxi",
  path:"/analysis/funnel"
},{
  type:2,
  name:"留存分析",
  icon:"icon-liucunfenxi",
  path:"/analysis/remain"
},
{
  type:3,
  name:"自定义查询",
  icon:"icon-bianzu17",
  path:"/analysis/customQueries"
}
])

const linkModelPath =(data:string) :void =>{
  if(data){
    router.push({
      path: data,
      query: {
        oi: overview_form_data.id
      }
    })
  }
}

// 提供父组件一个方法打开弹窗
const openFunc = (data: null | OverviewItem): void => {
  // debugger
    if (data) {
        overview_form_data.id = data.id;
    }
    visible.value = true
}
// 提供给父级使用
defineExpose({
    openFunc
})
</script>

<style lang='less' scoped>
.redio-card{
  text-align: center;
  background:#f5f8fc;
  margin-bottom: 20px;
}
.redio-card:hover{
  border-color: #c5503f;
  color:#c5503f;
  cursor: pointer;
  color:#c5503f !important;
}
</style>
