package com.esprit.examen.entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Operateur implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOperateur;
	private String nom;
	private String prenom;
	
	private String password;
	@OneToMany
	@JsonIgnore
	private Set<Facture> factures;
	
}
