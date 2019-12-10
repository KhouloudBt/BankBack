package com.example.demo.entities;

import java.util.Date;

import javax.persistence.*;
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{
private double taux;

public CompteEpargne(double taux) {
	super();
	this.taux = taux;
}

public CompteEpargne() {
	super();
	// TODO Auto-generated constructor stub
}

public CompteEpargne(Date dateCreation, double solde, Client client, double taux) {
	super(dateCreation, solde, client);
	this.taux = taux;
}

public double getTaux() {
	return taux;
}

public void setTaux(double taux) {
	this.taux = taux;
}

}
