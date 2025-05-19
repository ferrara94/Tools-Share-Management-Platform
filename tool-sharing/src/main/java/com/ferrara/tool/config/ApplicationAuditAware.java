package com.ferrara.tool.config;

import com.ferrara.tool.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/*
    we have the Integer type as Generic type since our user ID is
    @Id
    @GeneratedValue
    private Integer id;
*/
public class ApplicationAuditAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //if the User is not Authenticated
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken)
        {
            return Optional.empty();
        }

        //User userPrincipal = (User) authentication.getPrincipal();
        return Optional.ofNullable(authentication.getName());
    }
}
