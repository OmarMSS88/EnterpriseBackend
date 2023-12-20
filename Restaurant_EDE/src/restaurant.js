new Vue({
    el: '#app',
    data: {
      dishes: [
        { id: 1, name: 'Dish 1', price: 10.99 },
      { id: 2, name: 'Dish 2', price: 8.99 },
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

  