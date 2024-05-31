package com.distribuida.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class Jpaconfig {
    private EntityManagerFactory emf;
    //Se invoca cuando se crea el componente (una sola vez)
    @PostConstruct
    public void init(){
        System.out.println("**** Jpaconfig::init");

        emf = Persistence.createEntityManagerFactory("pu-distribuida");

    }
    //Se invoca cada que se necesita el entity manager
    @Produces
    public EntityManager em(){
        System.out.println("**** Jpaconfig::em");
        return emf.createEntityManager();
    }
}
