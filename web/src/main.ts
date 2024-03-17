import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import axios from "axios";

const app = createApp(App);

app.use(Antd).use(store).use(router).mount('#app')

axios.interceptors.request.use((config) => {
    console.log('axios 请求参数：', config);
    return config;
}, error => {
    return Promise.reject(error);
});

axios.interceptors.response.use((response) => {
    console.log('axios 响应参数：', response);
    return response;
}, error => {
    return Promise.reject(error);
})