package sftwitter.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sftwitter.SFTweet;
import sftwitter.SFTwitterService;
import twitter4j.Status;
import twitter4j.TwitterObjectFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SFTwitterServiceTest {
	
	@Autowired
	SFTwitterService sfTwitterService;

    @Test
    public void testTopTweets() throws Exception {
        List<SFTweet> tweets = sfTwitterService.getLatestTweets(10);
        assert(tweets.size() == 10);
    }
    
    @Test
    public void testBuildSFTweet() throws Exception {
    	Status twitter4jStatus = buildTwitter4jStatus();
    	SFTweet sftweet = sfTwitterService.buildSFTweet(twitter4jStatus);
    	assert(sftweet.getTweetContent().contains("Hey @you"));
    	assert(sftweet.getTweetDate() != null);
    	assert(sftweet.getUserName().equals("Salesforce"));
    	assert(sftweet.getScreenName().equals("salesforce"));
    }
    
    public Status buildTwitter4jStatus() throws Exception {
    	String string = "{\"in_reply_to_status_id_str\":\"922248563207184384\",\"in_reply_to_status_id\":922248563207184384,\"coordinates\":null,\"created_at\":\"Wed Oct 25 22:36:05 +0000 2017\",\"truncated\":false,\"in_reply_to_user_id_str\":\"31097290\",\"source\":\"foo\",\"retweet_count\":0,\"retweeted\":false,\"geo\":null,\"in_reply_to_screen_name\":\"denisemeyerson\",\"is_quote_status\":false,\"entities\":{\"urls\":[],\"hashtags\":[],\"user_mentions\":[{\"indices\":[0,15],\"screen_name\":\"denisemeyerson\",\"id_str\":\"31097290\",\"name\":\"Denise Meyerson\",\"id\":31097290}],\"symbols\":[]},\"id_str\":\"923317268204916737\",\"in_reply_to_user_id\":31097290,\"favorite_count\":0,\"id\":923317268204916737,\"text\":\"Hey @you this is a test tweet. We'd be happy to look into it for you.\",\"place\":null,\"contributors\":null,\"lang\":\"en\",\"user\":{\"utc_offset\":-25200,\"friends_count\":45919,\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/716283264881700865/voT_NePC_normal.jpg\",\"listed_count\":6810,\"profile_background_image_url\":\"http://pbs.twimg.com/profile_background_images/527222046799843328/5GFSYBIg.jpeg\",\"default_profile_image\":false,\"favourites_count\":7690,\"description\":\"Connect to your customers in a whole new way. Stay up-to-date on news, announcements, and innovation. On duty Mon - Fri 9am - 5pm PT.\",\"created_at\":\"Mon Apr 20 18:34:14 +0000 2009\",\"is_translator\":false,\"profile_background_image_url_https\":\"https://pbs.twimg.com/profile_background_images/527222046799843328/5GFSYBIg.jpeg\",\"protected\":false,\"screen_name\":\"salesforce\",\"id_str\":\"33612317\",\"profile_link_color\":\"009EDD\",\"is_translation_enabled\":false,\"translator_type\":\"none\",\"id\":33612317,\"geo_enabled\":true,\"profile_background_color\":\"00538B\",\"lang\":\"en\",\"has_extended_profile\":true,\"profile_image_extensions_alt_text\":null,\"profile_sidebar_border_color\":\"FFFFFF\",\"profile_text_color\":\"333333\",\"verified\":true,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/716283264881700865/voT_NePC_normal.jpg\",\"time_zone\":\"Pacific Time (US & Canada)\",\"url\":\"http://t.co/srWxB5LE0U\",\"contributors_enabled\":false,\"profile_background_tile\":true,\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/33612317/1505756033\",\"entities\":{\"description\":{\"urls\":[]},\"url\":{\"urls\":[{\"display_url\":\"salesforce.com\",\"indices\":[0,22],\"expanded_url\":\"http://salesforce.com\",\"url\":\"http://t.co/srWxB5LE0U\"}]}},\"statuses_count\":51707,\"follow_request_sent\":false,\"followers_count\":370608,\"profile_use_background_image\":false,\"default_profile\":false,\"following\":false,\"name\":\"Salesforce\",\"location\":\"San Francisco, CA\",\"profile_sidebar_fill_color\":\"B4DEF4\",\"profile_banner_extensions_alt_text\":null,\"notifications\":false},\"favorited\":false}";
	    return TwitterObjectFactory.createStatus(string);
    }
}