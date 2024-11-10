const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const Contact = require("../models/Contact");

// Register User
exports.register = async (req, res) => {
  try {
    // Extract data from request body
    const { nom, prenom, cin, email, password, image,
      dateNaissance, telephone, adresse, role } = req.body;

    // Validate if all required fields are provided
    if (!nom || !prenom || !cin || !email || !password) {
      return res.status(400).json({ message: "All fields are required" });
    }

    // Check if the user already exists
    const existingUser = await Contact.findOne({ email });
    if (existingUser) {
      return res.status(400).json({ message: "User already exists" });
    }

    // Hash the password using bcryptjs
    const hashedPassword = await bcrypt.hash(password, 10);

    // Create a new contact (user) object
    const newContact = new Contact({
      nom,
      prenom,
      cin,
      email,
      password: hashedPassword,
      image,
      dateNaissance,
      telephone,
      adresse,
      role
    });

    // Save the new user to the database
    contact = await newContact.save();

    res.status(201).json({ message: "User registered successfully", idContact: contact._doc._id, ...contact._doc });
  } catch (error) {
    console.error(error); // Log error for debugging
    res.status(500).json({ message: "Server error" });
  }
};

// Login User
exports.login = async (req, res) => {
  try {
    const { email, password } = req.body;

    // Validate if both email and password are provided
    if (!email || !password) {
      return res
        .status(400)
        .json({ message: "Email and password are required" });
    }

    // Check if the user exists
    const user = await Contact.findOne({ email });
    if (!user) {
      return res.status(400).json({ message: "Invalid credentials" });
    }

    // Compare the password with the hashed password in the database
    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) {
      return res.status(400).json({ message: "Invalid credentials" });
    }

    // Generate JWT token with a payload (user id and role)
    const payload = { id: user._id, role: user.role };
    const token = jwt.sign(payload, process.env.JWT_SECRET, {
      expiresIn: "1h",
    });

    // Return the token to the user
    res.json({ token });
  } catch (error) {
    console.error(error); // Log error for debugging
    res.status(500).json({ message: "Server error" });
  }
};
