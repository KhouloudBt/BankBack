package com.example.demo.metier;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.example.demo.dao.EmployeesRepository;
import com.example.demo.entities.*;


@RestController
@RequestMapping("/Employes")
public class EmployesMetierImpl {
	private final Logger log = LoggerFactory.getLogger(EmployesMetierImpl.class);
	@Autowired
	private EmployeesRepository employe;
	
	@GetMapping("/allemployes")
	public Collection <Employees> allClients()
	{
	return employe.findAll();	
	}
	
	
	@GetMapping("/employeByid/{id}")
	public Employees findemployes(@PathVariable Long id)
	{
		return employe.findById(id).get();
	}
	

	@GetMapping("/EmployeByName/{name}")
    public Collection<Employees> getToyByName(@PathVariable String name) {
        return employe.findAll().stream().
                filter(x -> x.getNomEmploye().equals(name))
                .collect(Collectors.toList());		
	}
	
	@PostMapping("/newEmploye")	
    public ResponseEntity<Void> ajouterProduit(@RequestBody Employees employe) {

        Employees employeAdded =  this.employe.save(employe);

        if (employeAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employeAdded.getCodeEmploye())
                .toUri();

        return ResponseEntity.created(location).build();
    }


	@PutMapping("/updateEmploye/{id}")
    public ResponseEntity<Employees> modifierEmploye(@Valid @RequestBody Employees employe ,@PathVariable("id") Long id) {
		{
			Optional<Employees> employeOptional = this.employe.findById(id);
			

	       /* if (clientOptional.isEmpty())
	            return ResponseEntity.notFound().build();*/

	        Employees emp1 = employeOptional.get();
	        emp1.setCodeEmploye(id);
	        emp1.setNomEmploye(employe.getNomEmploye());;
	       
	        // repository.deleteById(id); //with this instruction a new id will be generated
	        Employees result = this.employe.save(emp1);

	        //return ResponseEntity.noContent().build();
	        return ResponseEntity.ok().body(result);		}
		}
	
	
    @DeleteMapping("/removeEmployee/{id}")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {

        employe.deleteById(id);
        return ResponseEntity.ok().build();
    }
 
}