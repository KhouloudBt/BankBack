package com.example.demo.metier;

import java.net.URI;
import java.util.Collection;
import java.util.Date; 
import java.util.List;
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

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.entities.*;



@RestController
@RequestMapping("/Compte")
public class CompteMetierImpl {
	private final Logger log = LoggerFactory.getLogger(CompteMetierImpl.class);
	@Autowired
	private CompteRepository compte;
	
	@GetMapping("/allComptes")
	public Collection <Compte> allComptes()
	{
	return compte.findAll();	
	}
	
	
	@GetMapping("/compteByid/{id}")
	public Compte findcompte(@PathVariable Long id)
	{
		return compte.findById(id).get();
	}
	
	@PostMapping("/newCompte")	
    public ResponseEntity<Void> ajouterCompte(@RequestBody Compte compte) {

        Compte compteAdded =  this.compte.save(compte);

        if (compteAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(compteAdded.getCodeCompte())
                .toUri();

        return ResponseEntity.created(location).build();
    }


	@PutMapping("/updateCompte/{id}")
    public ResponseEntity<Compte> modifierClient(@Valid @RequestBody Compte compte ,@PathVariable("id") Long id) {
		{
			Optional<Compte> compteOptional = this.compte.findById(id);
			

	        if (compteOptional.isEmpty())
	            return ResponseEntity.notFound().build();

	        Compte compte1 = compteOptional.get();
	        compte1.setCodeCompte(id);
	        compte1.setDateCreation(compte.getDateCreation());
	        compte1.setSolde(compte.getSolde());
	        Compte result = this.compte.save(compte1);

	        //return ResponseEntity.noContent().build();
	        return ResponseEntity.ok().body(result);		}
		}
	
	
    @DeleteMapping("/removeCompte/{id}")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteCompte(@PathVariable("id") Long id) {

        compte.deleteById(id);
        return ResponseEntity.ok().build();
    }
}