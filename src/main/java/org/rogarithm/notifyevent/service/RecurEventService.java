package org.rogarithm.notifyevent.service;

import org.rogarithm.notifyevent.service.dto.RecurEventAddDto;
import org.rogarithm.notifyevent.web.response.RecurEventAddResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class RecurEventService {
    private static final Logger log = LoggerFactory.getLogger(RecurEventService.class);

    @Autowired
    private WebClient webClient;

    @Transactional
    public RecurEventAddResponse add(RecurEventAddDto dto) {
        WebClient.ResponseSpec retrieve = webClient.post()
                .uri("http://localhost:4567/evts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve();

        Mono<RecurEventAddResponse> response = retrieve.bodyToMono(RecurEventAddResponse.class);

        RecurEventAddResponse recurEventAddResponse = null;

        try {
            log.info("Attempting to block response...");
            recurEventAddResponse = response.block();
            log.info("Successfully retrieved response");
        } catch (WebClientResponseException e) {
            log.error("WebClientResponseException: ", e);
            log.error("Full error cause: ", e.getCause());
        }

        if (recurEventAddResponse == null) {
            throw new RuntimeException("Failed to retrieve a response from external server");
        }

        return recurEventAddResponse;
    }
}
