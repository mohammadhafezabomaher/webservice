const mongoose = require("mongoose");

const contactSchema = new mongoose.Schema(
  {
    nom: { type: String, required: true },
    prenom: { type: String, required: true },
    cin: { type: Number, required: true },
    email: { type: String, required: true, unique: true },
    password: { type: String, required: true },
    image: { type: String },
    dateNaissance: { type: Date },
    telephone: { type: Number },
    adresse: { type: String },
    role: {
      type: String,
      enum: [
        "Admin",
        "Exposant",
        "Etudiant",
        "Fournisseur",
        "Enseignant",
        "DirectionFinanciere",
      ],
      default: "Etudiant",
    },
  },
  { timestamps: true }
);

module.exports = mongoose.model("Contact", contactSchema);
