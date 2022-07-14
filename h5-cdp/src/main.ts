import { createApp } from 'vue'
import { createPinia } from "pinia"
import router from "./routers"
import App from './App'
import "_styles/index.less"




const app = createApp(App);

app.use(router)

app.use(createPinia())

app.mount('#app')

export default app
