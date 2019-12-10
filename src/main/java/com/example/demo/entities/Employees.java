package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Employees implements Serializable {
	@Id
	@GeneratedValue
 private Long codeEmploye;
 private String nomEmploye;
 @ManyToOne
 @JoinColumn(name="code_emp_Sup")
 private Employees EmployesSup;
 @ManyToMany
 private Collection<Groupe> groupes;
 @OneToMany(mappedBy="employe")
 private Collection<Compte> comptes;
 @OneToMany(mappedBy="employe")
 private Collection<Operation> operations;
public Employees() {
	super();
	// TODO Auto-generated constructor stub
}
public Employees(String nomEmploye, Employees employesSup) {
	super();
	this.nomEmploye = nomEmploye;
	EmployesSup = employesSup;
}
public Long getCodeEmploye() {
	return codeEmploye;
}
public void setCodeEmploye(Long codeEmploye) {
	this.codeEmploye = codeEmploye;
}
public String getNomEmploye() {
	return nomEmploye;
}
public void setNomEmploye(String nomEmploye) {
	this.nomEmploye = nomEmploye;
}
public Employees getEmployesSup() {
	return EmployesSup;
}
public void setEmployesSup(Employees employesSup) {
	EmployesSup = employesSup;
}
public Collection<Groupe> getGroupes() {
	return groupes;
}
public void setGroupes(Collection<Groupe> groupes) {
	this.groupes = groupes;
}
 
 
}
