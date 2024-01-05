new Vue({
    el: '#app',
    data: {
      dishes: [
        { id: 1, name: 'Pizza Margherita', price: 10.99 },
      { id: 2, name: 'Spaghetti Carbonara', price: 12.99 },
      { id: 3, name: 'Tiramisu', price: 6.99 },
      ],
      newDish: { name: '', price: 0 },
      visitors: [],
      tables: [],
      waiters: []
    },
    methods: {
        addDish(newDish) {
          this.dishes.push(newDish);
        },
        updateDish(updatedDish) {
          // Implement update logic
        },
        deleteDish(dishId) {
          this.dishes = this.dishes.filter(dish => dish.id !== dishId);
        },
        // Similar methods for visitors, tables, and waiters
      },
    mounted() {
        this.fetchData();
    }
      
  });

  