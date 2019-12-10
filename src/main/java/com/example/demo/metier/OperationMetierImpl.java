package com.example.demo.metier;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.*;
import antlr.collections.List;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.EmployeesRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.entities.*;


@RestController
@RequestMapping("/Operation")
public class OperationMetierImpl {
	private final Logger log = LoggerFactory.getLogger(OperationMetierImpl.class);
	@Autowired
	private OperationRepository operation;
	@Autowired
	private CompteRepository compte1;
	@Autowired
	private CompteRepository compte2;
	@GetMapping("/allOperation")
	public Collection <Operation> allOperations()
	{
	return operation.findAll();	
	}
	
	
	@GetMapping("/operationByid/{id}")
	public Operation findoperations(@PathVariable Long id)
	{
		return operation.findById(id).get();
	}
	
	@PostMapping("/newOperation")	
    public ResponseEntity<Void> ajouterOperation(@RequestBody Operation operation) {

        Operation operationAdded =  this.operation.save(operation);

        if (operationAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(operationAdded.getNumero())
                .toUri();

        return ResponseEntity.created(location).build();
    }


	@PutMapping("/updateOperation/{id}")
    public ResponseEntity<Operation> modifierOperation(@Valid @RequestBody Operation operation ,@PathVariable("id") Long id) {
		{
			Optional<Operation> operationOptional = this.operation.findById(id);
			

	       /* if (clientOptional.isEmpty())
	            return ResponseEntity.notFound().build();*/

	        Operation op1 = operationOptional.get();
	        op1.setNumero(id);
	        op1.setCompte(operation.getCompte());
	        op1.setDateOperation(operation.getDateOperation());
	        op1.setEmploye(operation.getEmploye());
	        op1.setMontant(operation.getMontant());
	       
	        // repository.deleteById(id); //with this instruction a new id will be generated
	        Operation result = this.operation.save(op1);

	        //return ResponseEntity.noContent().build();
	        return ResponseEntity.ok().body(result);		}
		}
	
	
    @DeleteMapping("/removeOperation/{id}")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteOperation(@PathVariable("id") Long id) {

        operation.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/Versement/{id}/{montant}")
    public ResponseEntity<Operation>Versement (@PathVariable("id") Long id,@PathVariable("montant") double montant) {
		{   Optional<Compte> cp=this.compte1.findById(id);
		    Compte cp1=cp.get();
		    cp1.setSolde(cp1.getSolde()+montant);
		    this.compte1.save(cp1);
			LocalDate localDate = LocalDate.now();
			Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Operation op=new Versement(date1,montant,cp1);
            this.operation.save(op);		
			

	       /* if (clientOptional.isEmpty())
	            return ResponseEntity.notFound().build();*/

	        
	        // repository.deleteById(id); //with this instruction a new id will be generated

	        //return ResponseEntity.noContent().build();
	        return ResponseEntity.ok().body(op);}
		}
    @PutMapping("/Retrait/{id}/{montant}")
    public ResponseEntity<Operation>Retrait (@PathVariable("id") Long id,@PathVariable("montant") double montant) {
		{   Optional<Compte> cp=this.compte1.findById(id);
		    Compte cp1=cp.get();
		    cp1.setSolde(cp1.getSolde()-montant);
		    this.compte1.save(cp1);
			LocalDate localDate = LocalDate.now();
			Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Operation op=new Retrait(date1,montant,cp1);
            this.operation.save(op);		
			

	       /* if (clientOptional.isEmpty())
	            return ResponseEntity.notFound().build();*/

	        
	        // repository.deleteById(id); //with this instruction a new id will be generated

	        //return ResponseEntity.noContent().build();
	        return ResponseEntity.ok().body(op);}
		}
    @PutMapping("/Virement/{id}/{id1}/{montant}")
    public ResponseEntity<Operation>Retrait (@PathVariable("id") Long id,@PathVariable("id1") Long id1,@PathVariable("montant") double montant) {
		{   Optional<Compte> cp=this.compte1.findById(id);
		    Compte cp1=cp.get();
		    cp1.setSolde(cp1.getSolde()-montant);
		    this.compte1.save(cp1);


		    Optional<Compte> cp3=this.compte2.findById(id1);
		    Compte cp4=cp3.get();
		    cp4.setSolde(cp4.getSolde()+montant);
		    this.compte1.save(cp4);

			LocalDate localDate = LocalDate.now();
			Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Operation op=new Retrait(date1,montant,cp1);
			Operation op1=new Versement(date1,montant,cp4);
            this.operation.save(op);		
			

	       /* if (clientOptional.isEmpty())
	            return ResponseEntity.notFound().build();*/

	        
	        // repository.deleteById(id); //with this instruction a new id will be generated

	        //return ResponseEntity.noContent().build();
	        return ResponseEntity.ok().body(op);}
		}
    
}