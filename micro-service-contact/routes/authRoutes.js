const express = require("express");
const router = express.Router();
const authController = require("../controllers/authController");
const contactController = require("../controllers/contactController"); // Assuming you have a contact controller

// Register route
router.post("/register", authController.register);

// Login route
router.post("/login", authController.login);
//get contact by ID route
router.get("/contacts/:id", contactController.getContactById);
router.delete("/contacts/:id", contactController.deleteContactById);


module.exports = router;
