package com.ontotext.trree.plugin.externalsync.auth.impl;

import com.ontotext.trree.plugin.externalsync.auth.HttpClientConfigurator;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomHttpRequestInterceptor implements HttpClientConfigurator {

    /**
     * Returns an {@link HttpRequestInterceptor} to be added as the last interceptor in the HTTP client.
     * <p>
     * This method may return NULL to signal no interceptor is needed.
     *
     * @param url        the URL of the remote server as provided in the connector definition
     * @param instanceId the ID of the connector instance
     * @return an {@link HttpRequestInterceptor} instance or NULL if no interceptor is available.
     */
    @Override
    public HttpRequestInterceptor getHttpRequestInterceptor(String url, String instanceId) {

        String username = getUsername(url, instanceId);
        String password = getPassword(url, instanceId);

        byte[] credentials = Base64.encodeBase64((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(credentials, StandardCharsets.UTF_8);

        HttpRequestInterceptor interceptor = new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {

                // todo implement custom authentication logic
                // example for basic Authorization
                httpRequest.setHeader("Authorization", authHeader);
            }
        };

        return interceptor;
    }

    private String getPassword(String url, String instanceId) {

        //todo implement
        throw new RuntimeException("not implemented");

    }

    private String getUsername(String url, String instanceId) {

        //todo implement
        throw new RuntimeException("not implemented");

    }

    /**
     * Whether to disable the authentication cache in the HTTP client. The default implementation does not disable it.
     *
     * @param url        the URL of the remote server as provided in the connector definition
     * @param instanceId the ID of the connector instance
     * @return true (disable the authentication cache) or false (do not disable it)
     */
    @Override
    public boolean shouldDisableAuthCaching(String url, String instanceId) {
        return false;
    }

    @Override
    public CredentialsProvider getCredentialsProvider(String url, String instanceId) {
        return null;
    }
}
