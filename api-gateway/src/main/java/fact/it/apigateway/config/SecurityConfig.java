package fact.it.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET, "/tables")
                                .permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET, "/visits")
                                .permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET, "/waiters")
                                .permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET, "/dishes")
                                .permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .oauth2ResourceServer((oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                ));
        return serverHttpSecurity.build();
    }
}
