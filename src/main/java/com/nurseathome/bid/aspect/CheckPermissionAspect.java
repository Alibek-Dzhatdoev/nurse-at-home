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

import static com.nurseathome.bid.utils.JwtUtils.getCurrentRole;
import static java.util.Arrays.stream;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CheckPermissionAspect {

    AuthService authService; //Может понадобиться, если будем использовать привилегии, при этом их не будет в токене

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

        if (isNotEmpty(checkPermission.roles())) {
            boolean hasRole = stream(checkPermission.roles())
                    .map(Enum::name)
                    .toList()
                    .contains(userRole);
            if (!hasRole) {
                throw new ResponseStatusException(UNAUTHORIZED, "Wrong role. You do not have permission.");
            }
        }
    }
}
