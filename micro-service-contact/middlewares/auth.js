const jwt = require("jsonwebtoken");

module.exports = (req, res, next) => {
  // Get token from the Authorization header
  const token = req.header("Authorization")?.replace("Bearer ", "");

  // Check if the token is provided
  if (!token) {
    return res
      .status(401)
      .json({ message: "Access denied. No token provided." });
  }

  // Verify the token
  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded; // Add user data to the request object
    next(); // Proceed to the next middleware/controller
  } catch (error) {
    res.status(400).json({ message: "Invalid or expired token" });
  }
};
