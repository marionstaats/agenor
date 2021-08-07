import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import Cloudinary from "cloudinary-vue";

Vue.config.productionTip = false

Vue.use(Cloudinary, {
  configuration: { cloudName: "agenor" }
});

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
