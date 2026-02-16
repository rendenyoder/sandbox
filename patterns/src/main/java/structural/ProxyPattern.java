package structural;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

/**
 * The proxy pattern is a structural design pattern that provides a substitute, or placeholder, for another object in
 * order to control access to it. This pattern is commonly used to provide security, logging, or performance
 * enhancements to existing objects without modifying them directly.
 *
 * <h4>Pattern Components:</h4>
 * <ul>
 *     <li><strong>Service Interface</strong> the contract for all services that can be proxied.</li>
 *     <li><strong>Concrete Service</strong> the concrete implementation of the service interface.</li>
 *     <li><strong>Proxy Service</strong> the proxy implementation that wraps a concrete service.</li>
 * </ul>
 *
 * <h4>Key Benefits:</h4>
 * <ul>
 *     <li><strong>Control</strong> proxies can control access without modifying existing code.</li>
 *     <li><strong>Abstraction</strong> proxies take away the complexities of managing a service from the client.</li>
 *     <li><strong>Open/Closed Principal</strong> proxies can be added or removed without modifying existing code.</li>
 * </ul>
 *
 * <h4>Example Usage:</h4>
 * <pre>{@code
 * // create a proxy service that caches expensive responses from a remote service
 * final var proxy = new HttpCacheProxy(new HttpService());
 *
 * // make a request to the remote service (only the first request will be made)
 * final var response = proxy.get("https://www.example.com");
 * final var cachedResponse = proxy.get("https://www.example.com");
 *
 * // the response should be the same string
 * assert response.equals(cachedResponse);
 * }
 */
public class ProxyPattern {
    /**
     * The service interface defining the contract for all services that can be proxied.
     */
    interface Service {
        /**
         * A simple method for retrieving data from a remote service.
         *
         * @param url the URL of the remote service.
         * @return the response body from the remote service.
         */
        String get(String url);
    }

    /**
     * The concrete proxy service which wraps a {@link HttpService} instance and caches the responses.
     */
    class HttpCacheProxy implements Service {

        private final HttpService service;
        private final Map<String, String> cache;

        /**
         * Constructor for the proxy service.
         *
         * @param service the service to proxy.
         */
        public HttpCacheProxy(HttpService service) {
            this.service = service;
            this.cache = new HashMap<>();
        }

        /**
         * Returns the proxied response from the cache if available, otherwise returns and caches the response from
         * the remote service.
         *
         * @param url the URL of the remote service.
         * @return the potentially cached response body from the remote service.
         */
        @Override
        public String get(String url) {
            return cache.computeIfAbsent(url, service::get);
        }
    }

    /**
     * The concrete service implementation which makes HTTP requests to a remote service.
     */
    public class HttpService implements Service {
        /**
         * Retrieves data from a remote service using HTTP GET.
         *
         * @param url the URL of the remote service.
         * @return the response body from the remote service.
         */
        @Override
        public String get(String url) {
            try (final var httpClient = HttpClient.newHttpClient()) {
                final var httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();

                return httpClient
                        .send(httpRequest, BodyHandlers.ofString())
                        .body();

            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException("Something went wrong...", ex);
            }
        }
    }
}
