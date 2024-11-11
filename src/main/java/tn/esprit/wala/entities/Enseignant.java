package tn.esprit.wala.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnseignant;
    private String identifiant;
    private Date dateDebut;
    private String email;
    private String name;
    private String specialty;
    private String meetingTitle;
    private LocalDateTime meetingDate;

    private String contactId;

    @ManyToOne
    private Entretien entretien;
}

