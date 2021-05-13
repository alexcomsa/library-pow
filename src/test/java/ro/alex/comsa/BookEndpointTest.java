package ro.alex.comsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.alex.comsa.customer.dao.CustomerModel;
import ro.alex.comsa.customer.dao.CustomerRepository;
import ro.alex.comsa.login.LoginRequest;
import ro.alex.comsa.login.VerifyTokenFilter;
import ro.alex.comsa.login.service.TokenService;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MainApp.class)
@AutoConfigureMockMvc
public class BookEndpointTest {

    public static final String EMAIL_ADDRESS = "unitTest@unitTest.com";

    @Autowired
    public MockMvc mvc;
    @Autowired
    public TokenService tokenService;
    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void  init(){
        customerRepository.deleteAll();
        tokenService.removeAllEntries();
    }

    @After
    public void  tearDown(){
        customerRepository.deleteAll();
        tokenService.removeAllEntries();
    }


    @Test
    public void getAllBooksFromRepositoryTest() throws Exception {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setEmailAddress(EMAIL_ADDRESS);
        customerModel.setHomeAddress("Home");
        customerModel.setName("test");
        customerRepository.save(customerModel);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(EMAIL_ADDRESS);
        loginRequest.setPassword("test");
        String token = tokenService.generateToken(loginRequest);

        mvc.perform(get("/book/all")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .header(VerifyTokenFilter.TOKEN_HEADER_NAME, token))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}
