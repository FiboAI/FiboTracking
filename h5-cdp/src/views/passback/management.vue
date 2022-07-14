<template>
  <div class="management-container">
    <div class="header">{{ page_title }}</div>
    <div class="management-content">
      <div class="management-top">
        <div class="box" v-for="(item, index) in data" :key="index" @click="clickBox(item)">
          <div class="left">
            <img :src="item.logo" alt="">
          </div>
          <div class="right">
            <div class="title large">{{ item.title }}</div>
            <div class="title">已配置回传事件: {{ item.number }}</div>
            <div class="title gray">授权状态: {{ item.type == 0 ? '无需授权' : '未授权' }}</div>
            <div class="title skyblue">回传事件管理 ></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang='ts' setup>
import useDate from "_hooks/useDate";
import useMessage from "_hooks/useMessage";

const route = useRoute();
const router = useRouter();
const { getTime, getAddTime } = useDate();
const { MessageError } = useMessage();
const page_title: string = "回传管理";
const save_event_analysis = ref();
// 假数据data
let data = [{
  url: '/passback/configure',
  logo: 'https://gitlab-mc.4paradigm.com/zhangjizhong/h5-yiwu-cdp/raw/hzzj_0613/src/assets/jlyqlogo.png',
  // logo: '/src/assets/jlyqlogo.png',
  title: '巨量引擎回传',
  number: 2,
  type: 0,
}, {
  url: '',
  logo: 'https://gitlab-mc.4paradigm.com/zhangjizhong/h5-yiwu-cdp/raw/hzzj_0613/src/assets/baidulogo.png',
  // logo: '/src/assets/baidulogo.png',
  title: '百度营销回传',
  number: 0,
  type: 1,
}]
const clickBox = (value: any) => {
  console.log('click', value);
  router.push({
    path: value.url,
    // query: value
    params: value
  });
}
</script>

<style lang='less' scoped>
.header {
  font-size: 22px;
  font-weight: bold;
  flex: auto;
  margin-bottom: 10px;
}

.management-container {
  height: 100%;
  box-sizing: border-box;
  padding: 16px 0 16px 16px;
  overflow: hidden;

  &>.management-content {
    height: calc(100% - 62px);
    width: 100%;
    overflow: hidden;

    &>.management-top {
      display: flex;

      &>.box {
        display: flex;
        width: 300px;
        height: 170px;
        margin: 20px;
        background: white;

        &>.left {
          width: 30%;
          margin-right: 10px;
          &>img {
            width: 100%;
            background-size: auto;
            transform: scale(0.7, 0.7);
          }
        }

        &>.right {
          width: 70%;

          &>.title {
            margin: 15px 0;
          }

          &>.large {
            font-size: 22px;
            font-weight: bold;
          }

          &>.gray {
            color: gray;
          }

          &>.skyblue {
            color: skyblue;
          }
        }
      }
    }
  }
}
</style>