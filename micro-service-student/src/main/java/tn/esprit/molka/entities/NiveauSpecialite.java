package tn.esprit.molka.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NiveauSpecialite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNiveauSpecialite;
    private String libelle;
    private String etablissement;
    private String departement;
    @OneToMany(cascade = CascadeType.ALL ,mappedBy =
            "niveauSpecialite")
    private Set<Etudiant> etudiants;
}
