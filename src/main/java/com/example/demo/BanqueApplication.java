package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.entities.Client;
import com.example.demo.entities.Compte;
import com.example.demo.entities.CompteCourant;
import com.example.demo.metier.CompteMetierImpl;
@EnableJpaRepositories(basePackages = {"com.example.demo.dao"})
@ComponentScan(basePackages = {"com.example.metier"})


@SpringBootApplication

public class BanqueApplication implements CommandLineRunner{
    @Autowired
    private ClientRepository clt;
    @Autowired
    private CompteRepository cc;
	public static void main(String[] args) {
	 SpringApplication.run(BanqueApplication.class, args);
    
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
