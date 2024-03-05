package com.example.pidev.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectionFinanciere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDirectionFinanciere;
    private Date dateDebut;
    private float budget;
    @OneToOne
    private Contact contact;
}
