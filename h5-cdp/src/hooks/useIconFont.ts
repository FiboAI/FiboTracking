import { Icon } from "@arco-design/web-vue"

// 导入阿里矢量图标库的icon
export default function () {
    const IconFont = Icon.addFromIconFontCn({
        src: 'http://127.0.0.1/iconfont.js'
    })
    return IconFont
}
