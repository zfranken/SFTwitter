package sftwitter.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SFTwitterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testTopTweets() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tweets").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("tweetDate")));
    }
    
    @Test
    public void testTwoTweets() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/tweets?count=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("tweetDate")));
    }
    
}