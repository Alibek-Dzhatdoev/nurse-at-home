package com.nurseathome.bid.config;

import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.CollectionUtils.isEmpty;

@RequiredArgsConstructor(access = PRIVATE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RequestAttributesFeignPropagator {

    RequestTemplate requestTemplate;

    static RequestAttributesFeignPropagator create(RequestTemplate requestTemplate) {
        return new RequestAttributesFeignPropagator(requestTemplate);
    }

    public void propagate(PropagationStrategy strategy, String token) {
        switch (strategy) {
            case IF_NOT_EXISTS -> {
                Map<String, Collection<String>> gatewayMethodHeaders = requestTemplate.methodMetadata()
                        .template()
                        .headers();
                Collection<String> methodHeaderValues = gatewayMethodHeaders.get(AUTHORIZATION);
                if (!isEmpty(methodHeaderValues)) return;
            }
            case REWRITE -> requestTemplate.removeHeader(AUTHORIZATION);
        }
        requestTemplate.header(AUTHORIZATION, token);
    }

    public enum PropagationStrategy {
        APPEND,
        REWRITE,
        IF_NOT_EXISTS
    }
}
