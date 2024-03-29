package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_Operation",discriminatorType=DiscriminatorType.STRING,length=1)
public abstract class Operation implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long numero;
	private Date dateOperation;
	@ManyToOne
	@JoinColumn(name="Code_Employe")
	private Employees employe;
	private double montant;
	@ManyToOne
	@JoinColumn(name="Code_compte")
	private Compte compte;
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Operation(Date dateOperation, double montant, Compte compte, Employees employe) {
		super();
		this.employe=employe;
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}
	public Operation(Date dateOperation, double montant, Compte compte) {
		super();
		
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}
	public Employees getEmploye() {
		return employe;
	}
	public void setEmploye(Employees employe) {
		this.employe = employe;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
