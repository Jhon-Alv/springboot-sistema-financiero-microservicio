package com.reto.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class WebClientConfig {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter((request, next) ->
                        Mono.deferContextual(ctx -> {
                            String correlationId = ctx.getOrDefault(CORRELATION_ID_HEADER, UUID.randomUUID().toString());
                            ClientRequest newRequest = ClientRequest.from(request)
                                    .header(CORRELATION_ID_HEADER, correlationId)
                                    .build();
                            return next.exchange(newRequest);
                        }))
                .build();
    }

    private ExchangeFilterFunction addCorrelationHeader() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest ->
                Mono.deferContextual(ctxView -> {
                    String correlationId = ctxView.getOrDefault(CORRELATION_ID_HEADER, "");
                    return Mono.just(ClientRequest.from(clientRequest)
                            .headers(headers -> headers.set(CORRELATION_ID_HEADER, correlationId))
                            .build());
                })
        );
    }

}
