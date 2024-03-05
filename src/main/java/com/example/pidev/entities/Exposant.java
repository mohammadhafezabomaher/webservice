package com.example.pidev.entities;

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
public class Exposant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExposant;
    private String siteOfficiel;
    @ManyToOne
    private Pack pack;
    @OneToOne
    private Contact contact;
}
