package org.ranga.javabrains.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/{pathParam}/test")
public class MyResource {

    @PathParam("pathParam") String pathParam;
    @QueryParam("name") String name;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
        return "It Works! Path param " + pathParam + " and Query param " + name;
    }
}
