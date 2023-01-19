package language.appconfig.security;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final AppUserProvider appUserProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var user = getUserDetailsFromRequest(request);

        var authentication = getAuthentication(request, user);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @NotNull
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, Optional<AppUserDetails> user) {
        var authentication = user
                .map(this::fromUserDetails)
                .orElseGet(this::fromEmptyDetails);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    private Optional<AppUserDetails> getUserDetailsFromRequest(HttpServletRequest request) {
        final var authHeader= request.getHeader(HttpHeaders.AUTHORIZATION);
        var token = StringUtils.removeStart(authHeader, "Bearer").trim();
        var user = appUserProvider.findByToken(token);
        return user;
    }

    private UsernamePasswordAuthenticationToken fromUserDetails(UserDetails details) {
        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    private UsernamePasswordAuthenticationToken fromEmptyDetails() {
        return new UsernamePasswordAuthenticationToken(null, null, null);
    }

}
