<template>
<a-range-picker v-bind="$attrs" :disabledDate="(current:Date) => getdisabled(current) " />
</template>
<script lang="ts" setup>


const getdisabled = (date:Date)=>{
   return new Date().getTime()< new Date(date).getTime()
}


</script>