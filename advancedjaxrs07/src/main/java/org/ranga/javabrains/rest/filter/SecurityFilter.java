package org.ranga.javabrains.rest.filter;

import org.glassfish.jersey.internal.util.Base64;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_URL_PREFIX = "secured";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
            List<String> authHeader =
                    requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
            if (authHeader != null && authHeader.size() > 0) {
                String authToken =
                        authHeader.get(0).replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                String decodedString = Base64.decodeAsString(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String userName = tokenizer.nextToken();
                String password = tokenizer.nextToken();
                // Verify through service in DB.
                if ("user".equals(userName) && "password".equals(password)) {
                    return;
                }
            }
            Response unauthorizedStatusResponse =
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("User cannot access the resource")
                            .build();
            requestContext.abortWith(unauthorizedStatusResponse);
        }
    }
}
