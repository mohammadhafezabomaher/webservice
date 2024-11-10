const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");
const bodyParser = require("body-parser");
const dotenv = require("dotenv");

// Load environment variables from .env file
dotenv.config();

// Import routes
const authRoutes = require("./routes/authRoutes");

// Import Eureka client configuration
// const eurekaClient = require("./eurekaClient"); // Assuming eureka.js is in the same directory

// Initialize the app
const app = express();

// Middleware
app.use(cors()); // Enable Cross-Origin Resource Sharing
app.use(bodyParser.json()); // Parse incoming requests with JSON payloads

// Connect to MongoDB
mongoose
  .connect(process.env.DB_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => console.log("MongoDB connected"))
  .catch((error) => console.log("Error connecting to MongoDB:", error));

// Use routes
app.use("/api/auth", authRoutes); // Set the base URL for authentication routes

// Define the port
const PORT = process.env.PORT || 3000;

// Start the server
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);

  // Start the Eureka client registration
  // eurekaClient.start((error) => {
  //   if (error) {
  //     console.log("Failed to register with Eureka:", error);
  //   } else {
  //     console.log("Node.js service registered with Eureka");
  //   }
  // });
});
