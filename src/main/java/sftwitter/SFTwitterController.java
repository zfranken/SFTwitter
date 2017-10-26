package sftwitter;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SFTwitterController {
	
	private static final Logger logger = Logger.getLogger(SFTwitterController.class.getName());
	
	@Value("${twitter.default.tweetcount}")
    private Integer defaultTweetCount;
	
	@Autowired
	SFTwitterService sfTwitterService;

	@CrossOrigin(origins = {"http://localhost:8080","https://frozen-plateau-10170.herokuapp.com","null"})
    @RequestMapping("/tweets")
    public List<SFTweet> topTweets(@RequestParam(required=false) Integer count) {
    	int numberOfTweets = defaultTweetCount;    	
    	
    	if(count != null)
    		numberOfTweets = count;
    	
        return sfTwitterService.getLatestTweets(numberOfTweets);
    }

}