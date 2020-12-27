// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import echarts from 'echarts'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueCookies from 'vue-cookies'

Vue.use(ElementUI)
Vue.use(VueCookies)

Vue.config.productionTip = false
Vue.prototype.bus = new Vue()
Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios

new Vue({
  el: '#app',
  router,
  render: h => h(App),
  components: { App },
  template: '<App/>'
})
axios.interceptors.request.use(
  config => {
    console.log('请求拦截成功')
    if (window.sessionStorage.getItem('token')) {
      config.headers.token = window.sessionStorage.getItem('token')
      // console.log('token: '+config.headers.token)
    }
    else{
      console.log('未能获取token')
    }
    return config;
  },
  error => {
    console.log('请求拦截异常')
    return Promise.reject(error);
  });

  // 响应拦截
axios.interceptors.response.use(
  function(res) {
    console.log('响应拦截成功')
    if(res.data.code=='401'){
      console.log('未登录或登录已过期，跳转登录')
      VueCookies.remove("cname") //清除cookie
      VueCookies.remove("cid")
      VueCookies.remove("role")
      window.sessionStorage.removeItem('token')
      router.push({path:"/"}) 
    }
    return res;
  },
  function(error) {
    return Promise.reject(error);
  }
);