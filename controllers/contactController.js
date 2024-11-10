const Contact = require("../models/Contact"); // Assuming the Contact model is set up properly

// Function to get contact by ID
exports.getContactById = async (req, res) => {
  try {
    const contactId = req.params.id;
    const contact = await Contact.findById(contactId); // Find the contact by ID in the database

    if (!contact) {
      return res.status(404).json({ message: "Contact not found" });
    }

    res.json({ ...contact._doc, idContact : contact._id}); // Respond with the contact if found
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Server error" });
  }
};

exports.deleteContactById = async (req, res) => {
  try {
    const contactId = req.params.id;
    const contact = await Contact.findByIdAndDelete(contactId); // Find the contact by ID in the database

    if (!contact) {
      return res.status(404).json({ message: "Contact not found" });
    }

    res.json(contact); // Respond with the contact if found
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Server error" });
  }
};