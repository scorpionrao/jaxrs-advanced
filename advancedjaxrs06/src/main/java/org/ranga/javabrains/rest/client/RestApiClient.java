package org.ranga.javabrains.rest.client;

import org.glassfish.jersey.client.JerseyInvocation;
import org.ranga.javabrains.messenger.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestApiClient {

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/webapi/messages/1");
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
        Message message = builder.get(Message.class);
        //Response response = builder.get();
        //Message message = response.readEntity(Message.class);
        System.out.println(message.getMessage());
    }
}
