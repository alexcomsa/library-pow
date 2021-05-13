package ro.alex.comsa.login;

import ro.alex.comsa.login.service.TokenService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is a rudimentary  implementation, for a production environment using a security framework like
 * Spring-Security is a much better approach
 */
public class VerifyTokenFilter implements Filter {


    public static final String TOKEN_HEADER_NAME = "alexc-token";
    TokenService tokenService;

    public VerifyTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader(TOKEN_HEADER_NAME);
        if (tokenService.isValid(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);


    }
}
