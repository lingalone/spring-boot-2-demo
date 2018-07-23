package io.github.lingalone.springboottokenssoclientdemo.auth;

import io.github.lingalone.springboottokenssoclientdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/7/23
 */

@Configuration
public class AuthConfig {

    private static final String jwtTokenCookieName = "JWT-TOKEN";
    // 签名key
    private static final String signingKey = "signingKey";

    @Value("${services.auth}")
    private String authService;

    @Autowired
    JwtUtil jwtUtil;


    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                try {
                    String username = jwtUtil.getSubject(request, jwtTokenCookieName, signingKey);
                    if (username == null) {
                        String authService = this.getFilterConfig().getInitParameter("services.auth");
                        response.sendRedirect(authService + "?redirect=" + request.getRequestURL());
                    } else {
                        request.setAttribute("username", username);
                        filterChain.doFilter(request, response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }
        });
        registrationBean.setInitParameters(Collections.singletonMap("services.auth", authService));
        /*registrationBean.addUrlPatterns("/protected-resource");*/
        registrationBean.addUrlPatterns("/protected-resource", "/logout");
        return registrationBean;
    }
}
