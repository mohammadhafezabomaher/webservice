package tn.esprit.wala.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.wala.entities.Entretien;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnseignantDto {

    private Long idEnseignant;
    private Date dateDebut;
    private String email;
    private String name;
    private String specialty;
    private String meetingTitle;
    private LocalDateTime meetingDate;

    private ContactDto contact;

    private Entretien entretien;
}
