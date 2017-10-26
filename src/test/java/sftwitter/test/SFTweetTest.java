package sftwitter.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sftwitter.SFTweet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SFTweetTest {
	
    @Test
    public void testImageUrls() throws Exception {
        SFTweet tweet = new SFTweet();
        //verify empty list is returned instead of null
        assert(tweet.getTweetImageUrls() != null);
    }
    
    @Test
    public void testAddImage() throws Exception {
        SFTweet tweet = new SFTweet();
        tweet.addTweetImageUrl("http://imageurl");
        assert(tweet.getTweetImageUrls().size() == 1);
    }
}