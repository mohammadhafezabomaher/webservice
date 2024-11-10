package tn.esprit.molka.dtos;


import jakarta.persistence.*;
import lombok.*;
import tn.esprit.molka.entities.TypeRole;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactDto {

    private String idContact;
    private String nom;
    private String prenom;
    private Long cin;
    private String email;
    private String password;
    private String image;
    private Date dateNaissance;
    private Long telephone;
    private String adresse;
    private TypeRole role;


}
