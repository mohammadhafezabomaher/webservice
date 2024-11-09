package tn.esprit.molka.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.molka.entities.NiveauSpecialite;
import tn.esprit.molka.entities.TypeEtudiant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDto {

    private Long idEtudiant;
    private String faculte;
    private String identifiant;
    @Enumerated(EnumType.STRING)
    private TypeEtudiant typeEtudiant;
    private ContactDto contact;
    private NiveauSpecialite niveauSpecialite;

}
