package com.nurseathome.bid.aspect;

import com.nurseathome.bid.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

import static com.nurseathome.bid.utils.SecurityContextUtils.getCurrentRole;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CheckPermissionAspect {

    AuthService authService;

    @Before(value = "@annotation(checkPermission)")
    public void checkPermissionMethodLevel(final CheckPermission checkPermission) {
        checkPermission(checkPermission);
    }

    @Before(value = "@within(checkPermission)")
    public void checkPermissionClassLevel(final CheckPermission checkPermission) {
        checkPermission(checkPermission);
    }

    private void checkPermission(final CheckPermission checkPermission) {
        val userRole = getCurrentRole();

        boolean hasPermission = Arrays.stream(checkPermission.roles())
                .map(role -> role.description)
                .anyMatch(role -> role.equals(userRole));

        if (!hasPermission) {
            throw new ResponseStatusException(UNAUTHORIZED, format("Wrong role: %s. You do not have permission.", userRole));
        }
    }
}
