package sftwitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SFTweet {
	
	private String userName;
	private String screenName; // @screenname
	private String profileImageUrl; 
	private String tweetContent;
	private List<String> tweetImageUrls;
	private long retweetCount;
	private Date tweetDate;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public String getTweetContent() {
		return tweetContent;
	}
	public void setTweetContent(String tweetContent) {
		this.tweetContent = tweetContent;
	}
	public long getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(long retweetCount) {
		this.retweetCount = retweetCount;
	}
	public Date getTweetDate() {
		return tweetDate;
	}
	public void setTweetDate(Date tweetDate) {
		this.tweetDate = tweetDate;
	}
	public List<String> getTweetImageUrls() {
		//return empty list instead of null
		if(tweetImageUrls == null) {
			tweetImageUrls = new ArrayList<String>();
		}
		return tweetImageUrls;
	}
	public void setTweetImageUrls(List<String> tweetImageUrls) {
		this.tweetImageUrls = tweetImageUrls;
	}
	public void addTweetImageUrl(String url) {
		if(url != null)
			getTweetImageUrls().add(url);
	}
	
}