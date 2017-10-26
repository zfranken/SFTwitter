package sftwitter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Component
public class SFTwitterService {

	private static final Logger logger = Logger.getLogger(SFTwitterService.class.getName());
	
	@Autowired
	Twitter twitter;
	
	@Value("${twitter.username}")
    private String twitterUsername;

	public List<SFTweet> getLatestTweets(Integer numberOfTweets) {

		String user = twitterUsername;
		List<SFTweet> tweets = new ArrayList<SFTweet>();

		try {
			Paging page = new Paging(1, numberOfTweets);
			List<Status> statuses = twitter.getUserTimeline(user, page);
			for(Status status : statuses) {
				tweets.add(buildSFTweet(status));
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return tweets;
	}
	
	public SFTweet buildSFTweet(Status status) {
				
		SFTweet sfTweet = new SFTweet();
		sfTweet.setScreenName(status.getUser().getScreenName());
		sfTweet.setUserName(status.getUser().getName());
		sfTweet.setProfileImageUrl(status.getUser().getProfileImageURLHttps());
		sfTweet.setTweetContent(status.getText());
		sfTweet.setRetweetCount(status.getRetweetCount());
		sfTweet.setTweetDate(status.getCreatedAt());

		//get tweet images
		MediaEntity[] media = status.getMediaEntities();
		for(MediaEntity m : media){ 
		    sfTweet.addTweetImageUrl(m.getMediaURLHttps());
		}
		
		return sfTweet;
	}

}