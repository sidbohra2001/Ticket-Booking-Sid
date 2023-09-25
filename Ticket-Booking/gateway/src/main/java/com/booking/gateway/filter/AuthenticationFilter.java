package com.booking.gateway.filter;

import com.booking.gateway.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;

    @Autowired private RestTemplate rest;
    @Autowired private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) throws RuntimeException{
        return ((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                // Check is header contains token.
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                    throw new RuntimeException("Missing authorization header");
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7); //Removing the "Bearer " from the beginning of the token.
                }
                try{
                    // rest.getForEntity("http://AUTH-SERVICE/auth/validate?token="+authHeader, String.class); // Useful method but can be breached.
                    jwtUtil.validateToken(authHeader);
                }catch (Exception e){
                    throw new RuntimeException("Unauthorized User");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}
