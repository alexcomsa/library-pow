package ro.alex.comsa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MainApp.class)
@AutoConfigureMockMvc
public class EmailValidationEndpointTest {


    public static final String EMAIL_ADDRESS = "abc@abc.com";

    @Autowired
    public MockMvc mvc;


    @Test
    public void validateEmailHappyPathTest() throws Exception {
        mvc.perform(post("/email/validate")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(EMAIL_ADDRESS))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid", equalTo(Boolean.TRUE)));
    }

}
