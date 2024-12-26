package com.ali.nurse_at_home.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.FieldDefaults;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class RequestContextUtils {

    static String DEFAULT_MESS = ": header not found";

    public static String getHeaderValue(String headerName) {
        return getServletRequest()
                .map(request -> request.getHeader(headerName))
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, headerName + DEFAULT_MESS));
    }

    private static Optional<HttpServletRequest> getServletRequest() {
        return ofNullable(getRequestAttributes())
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }
}