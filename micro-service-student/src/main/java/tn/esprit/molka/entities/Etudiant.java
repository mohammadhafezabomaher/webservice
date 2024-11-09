package tn.esprit.molka.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtudiant;
    private String faculte;
    private String identifiant;
    @Enumerated(EnumType.STRING)
    private TypeEtudiant typeEtudiant;
    private String contactId;

    @ManyToOne
    private NiveauSpecialite niveauSpecialite;
}
