package ru.yank0vy3rdna.soa.crud;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Objects;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/")
public class StaticFiles {

    @Inject
    ServletContext context;

    @GET
    @Path("{path: .*}")
    public Response staticResources(@PathParam("path") String path) {
        if (path.length() == 0) {
            path = "index.html";
        }
        String resourcePath = String.format("/WEB-INF/frontend/%s", path);
        System.out.println(resourcePath);
        InputStream resource = context.getResourceAsStream(resourcePath);

        return Objects.isNull(resource)
                ? Response.status(NOT_FOUND).build()
                : Response.ok().entity(resource).build();
    }
}