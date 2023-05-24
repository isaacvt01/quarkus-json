package com.example;



import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/fruits")
public class FruitsResource {

    @Inject
    FruitService service;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> getFruits() {
        return service.list();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFruit(Fruit fruit) {
        service.add(fruit);

        return Response.created(URI.create("/fruits/".concat(fruit.id.toString()))).entity(fruit).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFruit(@PathParam("id") Long id) {
        service.removeById(id);
        Fruit fruit =  service.findById(id);

        return fruit==null?
                Response.ok("Fruta eliminada correctamente").build():
                Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se pudo eliminar la fruta").build();
    }

    @PUT
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFruit(Fruit fruit) {
        service.update(fruit);
        return Response.ok().entity(fruit).build();
    }


}
