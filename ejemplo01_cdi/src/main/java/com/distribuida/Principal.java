package com.distribuida;

import com.distribuida.servicios.StringService;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Principal {
    public static void main(String[] args) {
        //Clase que gesiona el contenedor
        SeContainer container = SeContainerInitializer
                .newInstance()
                .initialize();

        //Esto insancia estos contenedores a mano
        //El microprofile no lo va a hacer asi
        //Utilizamos el componente de negocio
        StringService service = container.select(StringService.class)
                .get();

        String ret = service.convert("hola mundo!");
        System.out.println(ret);
    }
}
