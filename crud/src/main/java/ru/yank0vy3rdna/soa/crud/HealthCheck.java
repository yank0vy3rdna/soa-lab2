package ru.yank0vy3rdna.soa.crud;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("healthcheck")
public class HealthCheck {
    @GET
    public Response healthcheck(){
        return Response.ok().build();
    }
}
