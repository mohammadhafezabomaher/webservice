package com.example.pidev.dtos;

import com.example.pidev.entities.TypeRole;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactDto {
    private Long idContact;
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
