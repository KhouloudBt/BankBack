package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type_CPTE",discriminatorType=DiscriminatorType.STRING,length=2)
public abstract class Compte implements Serializable {
	
@Id
@GeneratedValue
private long CodeCompte;
private Date DateCreation;
private double solde;
@ManyToOne
@JoinColumn(name="Code_Client")
private Client client;
@ManyToOne
@JoinColumn(name="Code_emp")
private Employees employe;
@OneToMany(mappedBy="compte")
private Collection<Operation> operation;
public Compte() {
	super();
	// TODO Auto-generated constructor stub
}
public Compte(Date dateCreation, double solde, Client client) {
	super();
	DateCreation = dateCreation;
	this.solde = solde;
	this.client = client;
}
public long getCodeCompte() {
	return CodeCompte;
}
public void setCodeCompte(long codeCompte) {
	CodeCompte = codeCompte;
}
public Date getDateCreation() {
	return DateCreation;
}
public void setDateCreation(Date dateCreation) {
	DateCreation = dateCreation;
}
public double getSolde() {
	return solde;
}
public void setSolde(double solde) {
	this.solde = solde;
}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}
public Employees getEmploye() {
	return employe;
}
public void setEmploye(Employees employe) {
	this.employe = employe;
}
public Collection<Operation> getOperation() {
	return operation;
}
public void setOperation(Collection<Operation> operation) {
	this.operation = operation;
}

}

