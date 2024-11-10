const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const Contact = require("../models/Contact");

// Function to handle user registration
const registerUser = async (userData) => {
  try {
    const { nom, prenom, cin, email, password } = userData;

    // Check if the user already exists
    const existingUser = await Contact.findOne({ email });
    if (existingUser) {
      throw new Error("User already exists ..");
    }

    // Hash the password before saving
    const hashedPassword = await bcrypt.hash(password, 10);

    // Create a new user
    const newUser = new Contact({
      nom,
      prenom,
      cin,
      email,
      password: hashedPassword,
    });

    // Save the new user to the database
    await newUser.save();

    return { message: "User registered successfully" };
  } catch (error) {
    throw new Error(error.message);
  }
};

// Function to handle user login and generate JWT token
const loginUser = async (credentials) => {
  const { email, password } = credentials;

  // Find the user by email
  const user = await Contact.findOne({ email });
  if (!user) {
    throw new Error("Invalid credentials");
  }

  // Check if the password is correct
  const isMatch = await bcrypt.compare(password, user.password);
  if (!isMatch) {
    throw new Error("Invalid credentials");
  }

  // Generate a JWT token
  const payload = { id: user._id, role: user.role };
  const token = jwt.sign(payload, process.env.JWT_SECRET, { expiresIn: "1h" });

  return { token };
};

// You can add more service methods here (e.g., for user data updates)

module.exports = {
  registerUser,
  loginUser,
};
