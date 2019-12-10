package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Groupe implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long codeGroupe;
 private String nomGroupe;
 @ManyToMany(mappedBy="groupes")
 private Collection<Employees> employees;
public Groupe(String nomGroupe) {
	super();
	this.nomGroupe = nomGroupe;
}
public Groupe() {
	super();
	// TODO Auto-generated constructor stub
}
public Long getCodeGroupe() {
	return codeGroupe;
}
public void setCodeGroupe(Long codeGroupe) {
	this.codeGroupe = codeGroupe;
}
public String getNomGroupe() {
	return nomGroupe;
}
public void setNomGroupe(String nomGroupe) {
	this.nomGroupe = nomGroupe;
}
public Collection<Employees> getEmployees() {
	return employees;
}
public void setEmployees(Collection<Employees> employees) {
	this.employees = employees;
}
 
}
