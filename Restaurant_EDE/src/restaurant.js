import axios from 'axios';

new Vue({
  el: '#app',
  data() {
    return {
      info: null
    };
  },
  async mounted() {
    try {
      const response = await axios.get('https://dish-service-jetzeluyten.cloud.okteto.net/api/dish/all');
      this.info = response.data;
    } catch (error) {
      console.error('Error fetching data:', error);
      this.handleAxiosError();
    }
  },
  methods: {
    handleAxiosError() {
      this.info = [
        {
          id: '1',
          dishName: 'Pizza Margherita',
          price: 10.99
        },
        {
          id: '2',
          dishName: 'Spaghetti Carbonara',
          price: 12.99
        },
        {
          id: '3',
          dishName: 'Tiramisu',
          price: 6.99
        },
        // Add more fallback data as needed
      ];
    }
  }
});
