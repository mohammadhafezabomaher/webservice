package com.example.admin.dto;
import com.example.admin.entities.TypeRole;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactDTO {
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
