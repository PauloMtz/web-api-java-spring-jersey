package api.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/agenda-api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        this.register(RequestContextFilter.class);
        this.packages("api.endpoint");
        this.register(IdadeContatoExceptionHandler.class);
        this.register(ContatoNaoEncontradoExceptionHandler.class);
        this.register(CorsInterceptor.class);
    }
}