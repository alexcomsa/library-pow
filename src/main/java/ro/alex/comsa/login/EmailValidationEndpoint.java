package ro.alex.comsa.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alex.comsa.login.service.EmailValidationService;

@RestController
@RequestMapping("/email")
public class EmailValidationEndpoint {

    @Autowired
    public EmailValidationEndpoint(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    private EmailValidationService emailValidationService;


    @PostMapping(value = "/validate", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ValidEmailResponse validateEmail(@RequestBody String email) {
        boolean isValid = emailValidationService.isEmailValid(email);
        return new ValidEmailResponse(isValid);
    }
}
