package com.nurseathome.bid.config;

import com.nurseathome.bid.aspect.Authorized;
import com.nurseathome.bid.service.AuthService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.nurseathome.bid.config.RequestAttributesFeignPropagator.PropagationStrategy.IF_NOT_EXISTS;
import static com.nurseathome.bid.config.RequestAttributesFeignPropagator.PropagationStrategy.REWRITE;
import static com.nurseathome.bid.config.RequestAttributesFeignPropagator.create;
import static com.nurseathome.bid.utils.JwtUtils.getAccessToken;
import static java.util.Optional.ofNullable;
import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    AuthService authService;

    @Override
    public void apply(RequestTemplate template) {
        val methodMetadata = template.methodMetadata();
        val authorized = methodMetadata.template()
                .methodMetadata()
                .method()
                .getAnnotation(Authorized.class);

        val requestAttributes =
                ofNullable((ServletRequestAttributes) getRequestAttributes());

        if ("user".equals(authorized.tokenType())) {
            requestAttributes.ifPresent(servletRequestAttributes ->
                    create(template).propagate(IF_NOT_EXISTS, getAccessToken())
            );
        } else if ("service".equals(authorized.tokenType())) {
            requestAttributes.ifPresent(servletRequestAttributes ->
                    create(template).propagate(REWRITE, authService.getServiceAccessToken())
            );
        }
    }
}