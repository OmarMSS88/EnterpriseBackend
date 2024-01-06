import { axios } from '@/plugins/axios'

new Vue({
    el: '#app',
    data () {
      return {
        info: null
      }
    },
    mounted() {
        axios
          .get('https://dish-service-jetzeluyten.cloud.okteto.net/api/dish/all')
          .then(response => (this.info = response.data))
    }
      
  });

  