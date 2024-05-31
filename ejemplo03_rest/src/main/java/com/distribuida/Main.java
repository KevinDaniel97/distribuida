package com.distribuida;

import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import com.google.gson.Gson;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.*;

public class Main {

    static SeContainer container;

    static List <Persona> listarPersonas(Request rq, Response res){
        res.type("application/json");

        var servicio = container.select(ServicioPersona.class).get();
        return  servicio.findAll();
    }

    static Persona buscarPersona(Request req, Response res) {
        res.type("application/json");

        String _id = req.params(":id");
        var servicio = container.select(ServicioPersona.class).get();
        var persona = servicio.findById(Integer.valueOf(_id));

        if(persona == null){
            halt(404, "Persona no encontrada");
        }

        return persona;

    }


        public static void main(String[] args) {
         container = SeContainerInitializer
                .newInstance()
                .initialize();


        ServicioPersona servicio = container.select(ServicioPersona.class).get();

        servicio.findAll().stream().map(Persona::getNombre)
                .forEach(System.out::println);

        port(8080);
        Gson gson = new Gson();

            //get("/hello", (req, res) -> "Hello World sssss");
        get("/personas",Main::listarPersonas, gson::toJson);
        get("/personas/:id",Main::buscarPersona, gson::toJson);

        //get("", Main::listarPersonas, null);

    }

}
