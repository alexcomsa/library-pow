package ro.alex.comsa.login;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.alex.comsa.login.service.TokenService;

@Configuration
public class LoginConfiguration {


    @Bean
    FilterRegistrationBean<VerifyTokenFilter> filterFilterRegistrationBean(TokenService tokenService) {
        FilterRegistrationBean<VerifyTokenFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(new VerifyTokenFilter(tokenService));
        regBean.addUrlPatterns("/book/*");
        return regBean;
    }
}
