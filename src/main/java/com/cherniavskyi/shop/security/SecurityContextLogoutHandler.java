package com.cherniavskyi.shop.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class SecurityContextLogoutHandler implements LogoutHandler {

    @Getter
    private boolean invalidateHttpSession = true;
    private boolean clearAuthentication = true;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @Override
    public void logout(@NotNull HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (invalidateHttpSession) {
            var session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        var context = securityContextHolderStrategy.getContext();
        securityContextHolderStrategy.clearContext();
        if (clearAuthentication) {
            context.setAuthentication(null);
        }
        var emptyContext = securityContextHolderStrategy.createEmptyContext();
        securityContextRepository.saveContext(emptyContext, request, response);
    }

}
