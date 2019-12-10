package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
@Entity
public class Client implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String email;
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Compte> comptes;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}
	public Client(String nom, String email,Collection<Compte> comptes) {
		super();
		this.comptes=comptes;
		this.nom = nom;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setid(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	

}
