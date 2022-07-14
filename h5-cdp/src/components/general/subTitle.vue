<template>

<div class="subTitle">
 <div class="redIcon">

 </div>
    <div>
        {{props.title}}
    </div>
</div>

</template>

<script lang="ts" setup> 

const props = defineProps<{
    title: string
}>()


</script>
<style lang="less">
.subTitle{
    padding: 4px;
    display: flex;
    align-items: center;
    font-size: 16px;
    & > .redIcon{
        background-color: #EE5A46;
        width: 4px;
        height: 20px;
        border-radius: 2px;
        margin-right: 10px;
    }
}

</style>