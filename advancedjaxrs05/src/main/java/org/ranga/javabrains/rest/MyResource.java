package org.ranga.javabrains.rest;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class MyResource {

    @GET
    @Produces(value = {MediaType.TEXT_PLAIN, "text/short"})
    public Date testWriteMethod() {
        return Calendar.getInstance().getTime();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void testReadDate(Date date) {
        System.out.println(date.toString());
    }
}
