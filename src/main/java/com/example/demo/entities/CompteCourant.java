package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {
private double decouvert;

public CompteCourant() {
	super();
	// TODO Auto-generated constructor stub
}

public CompteCourant(Date dateCreation, double solde, Client client, double decouvert) {
	super(dateCreation, solde, client);
	this.decouvert = decouvert;
}

public double getDecouvert() {
	return decouvert;
}

public void setDecouvert(double decouvert) {
	this.decouvert = decouvert;
}

}
