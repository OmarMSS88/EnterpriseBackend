const express = require('express');
const app = express();

// Enable CORS for all routes
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', 'https://dish-service-jetzeluyten.cloud.okteto.net/dishes/all'); // Replace with your actual frontend origin
  res.header('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
  res.header('Access-Control-Allow-Headers', 'Content-Type');
  next();
});

// Your routes and other middleware...

app.listen(5173, () => {
  console.log('Server is listening on port 3000');
});
