package ro.alex.comsa.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alex.comsa.login.service.EmailValidationService;
import ro.alex.comsa.login.service.TokenService;

@RestController
@RequestMapping("/login")
public class LoginEndpoint {

    @Autowired
    public LoginEndpoint(TokenService tokenService, EmailValidationService emailValidationService) {
        this.tokenService = tokenService;
        this.emailValidationService = emailValidationService;
    }

    private final TokenService tokenService;
    private final EmailValidationService emailValidationService;


    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        if (emailValidationService.isEmailValid(request.getUsername())) {
            return new LoginResponse(tokenService.generateToken(request));
        } else throw new InvalidEmailAddressException();
    }
}
