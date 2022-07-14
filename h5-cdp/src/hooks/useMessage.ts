import myapp from "@/main"
import { Message } from "@arco-design/web-vue";
import "@arco-design/web-vue/es/message/style/css.js"
Message._context = myapp._context

export default function () {
    // 成功的方法
    const MessageSuccess = Message.success
    const MessageError = Message.error
    return {
        Message,
        MessageSuccess,
        MessageError
    }
}