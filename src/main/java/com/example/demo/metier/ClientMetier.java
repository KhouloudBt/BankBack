package com.example.demo.metier;



import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.demo.entities.Client;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Client")
public class ClientMetier {
	@Autowired
	private ClientRepository client;
	
	@GetMapping("/allClients")
	public Collection <Client> allClients()
	{return client.findAll();
	}
	
	
	@GetMapping("/clientByid/{id}")
	public Client findclient(@PathVariable Long id)
	{
		return client.findById(id).get();
	}
	
	@PostMapping("/newClient")	
    public ResponseEntity<Void> ajouterProduit(@RequestBody Client client) {

        Client clientAdded =  this.client.save(client);

        if (clientAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


	@GetMapping("/ClientByName/{name}")
    public List <Client> getToyByName(@PathVariable String name) {
		
		List <Client> l1  = new ArrayList();
		List <Client> l2  = new ArrayList();

		 l1 = this.client.findAll();
		 for(int i=0; i< l1.size(); i++)
		 {
			 if (l1.get(i).getNom().equals(name)) l2.add(l1.get(i));
		 }
		return l2;
      
	}
	@PutMapping("/updateClient/{id}")
    public ResponseEntity<Client> modifierClient(@Valid @RequestBody Client client ,@PathVariable("id") Long id) {
		{
			Optional<Client> clientOptional = this.client.findById(id);
			

	       if (clientOptional.isEmpty())
	            return ResponseEntity.notFound().build();

	        Client client1 = clientOptional.get();
	        client1.setid(id);
	        client1.setNom(client.getNom());
	        client1.setEmail(client.getEmail());
	        client1.setComptes(client.getComptes());
	        Client result = this.client.save(client1);

	        return ResponseEntity.ok().body(result);		}
		}
	
	
    @DeleteMapping("/removeClient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {

        client.deleteById(id);
        return ResponseEntity.ok().build();
    }

	
	
}