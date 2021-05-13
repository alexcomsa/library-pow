package ro.alex.comsa;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ro.alex.comsa.customer.CustomerDto;
import ro.alex.comsa.customer.dao.CustomerRepository;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MainApp.class)
@AutoConfigureMockMvc
public class CustomerEndpointTest {


    public static final String TEST_NAME = "TestName";
    public static final String HOME_ADDRESS = "HomeAddress";
    public static final String EMAIL_ADDRESS = "abc@abc.com";

    @Autowired
    public MockMvc mvc;

    @Autowired
    public CustomerRepository h2CustomerDAO;

    private ObjectMapper objectMapper = new ObjectMapper();

    @After
    public void tearDown() {
        h2CustomerDAO.deleteAll();
    }



    @Before
    public void setUp() {
        h2CustomerDAO.deleteAll();
    }

    @Test
    public void createMethodTest() throws Exception {
        mvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateCustomerDto(TEST_NAME, EMAIL_ADDRESS, HOME_ADDRESS)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createAndGetAllTest() throws Exception {
        mvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateCustomerDto(TEST_NAME, EMAIL_ADDRESS, HOME_ADDRESS)))
                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get("/customers")).andDo(print())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    private String generateCustomerDto(String name, String emailAddress, String homeAddress) {
        CustomerDto customerDto = getCustomerDto(name, emailAddress, homeAddress);
        StringWriter stringWriter = new StringWriter();
        try {
            objectMapper.writeValue(stringWriter, customerDto);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e.getMessage());
        }
    }


    private CustomerDto getCustomerDto(String name, String emailAddress, String homeAddress) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(name);
        customerDto.setHomeAddress(homeAddress);
        customerDto.setEmailAddress(emailAddress);
        return customerDto;
    }


}
