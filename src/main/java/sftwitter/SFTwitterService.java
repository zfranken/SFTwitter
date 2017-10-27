package sftwitter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class SFTwitterService {

	private static final Logger logger = Logger.getLogger(SFTwitterService.class.getName());

	@Value("${twitter.username}")
	private String twitterUsername;

	@Value("${twitter.api.consumerkey}")
	private String twitterApiConsumerKey;

	@Value("${twitter.api.consumersecret}")
	private String twitterApiConsumerSecret;

	@Value("${twitter.api.accesstoken}")
	private String twitterApiAccessToken;

	@Value("${twitter.api.accesstokensecrect}")
	private String twitterApiAccessTokenSecret;

	@Autowired
	Twitter twitter;
	

	/**
	 * Get the top n tweets from the twitter feed
	 * 
	 * @param numberOfTweets
	 * @return List of SFTweets
	 */
	public List<SFTweet> getLatestTweets(Integer numberOfTweets) {

		String user = twitterUsername;
		List<SFTweet> tweets = new ArrayList<SFTweet>();

		try {
			Paging page = new Paging(1, numberOfTweets);
			List<Status> statuses = twitter.getUserTimeline(user, page);
			for (Status status : statuses) {
				tweets.add(buildSFTweet(status));
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		return tweets;
	}

	/**
	 * Convert the twitter4j status into a SFTweet
	 * 
	 * @param status The twitter4j tweet object
	 * @return SFTweet populated from the twitter4j status object
	 */
	public SFTweet buildSFTweet(Status status) {

		SFTweet sfTweet = new SFTweet();
		sfTweet.setScreenName(status.getUser().getScreenName());
		sfTweet.setUserName(status.getUser().getName());
		sfTweet.setProfileImageUrl(status.getUser().getProfileImageURLHttps());
		sfTweet.setTweetContent(status.getText());
		sfTweet.setRetweetCount(status.getRetweetCount());
		sfTweet.setTweetDate(status.getCreatedAt());

		// get tweet images
		MediaEntity[] media = status.getMediaEntities();
		for (MediaEntity m : media) {
			sfTweet.addTweetImageUrl(m.getMediaURLHttps());
		}

		return sfTweet;
	}

	
	@Bean
	public Twitter twitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(twitterApiConsumerKey);
		cb.setOAuthConsumerSecret(twitterApiConsumerSecret);
		cb.setOAuthAccessToken(twitterApiAccessToken);
		cb.setOAuthAccessTokenSecret(twitterApiAccessTokenSecret);

		return new TwitterFactory(cb.build()).getInstance();
	}
}