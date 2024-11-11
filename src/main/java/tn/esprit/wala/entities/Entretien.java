package tn.esprit.wala.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntretien;
    private Date dateEntretien;
    private String lien;
    private String salle;


    @OneToMany (mappedBy = "entretien" , cascade = CascadeType.ALL)
    private Set<Enseignant> bloc;
}
