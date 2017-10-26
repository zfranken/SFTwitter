package sftwitter;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@SpringBootApplication
public class SFTwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SFTwitterApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> { };
    }
    
    @Bean
	public Twitter twitterFactory() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setOAuthConsumerKey("Plncj26x3kb9lvY3gmhtn6JKz");
	    cb.setOAuthConsumerSecret("7CO7aucPdUqxCUEJbYujY4rrxn3QqlmI0sbD7dMoU3PTE7UORw");
	    cb.setOAuthAccessToken("309629298-zFJINCG54x1v6LwRMSGZEyxtzQCx7A6HHdsyQpCE");
	    cb.setOAuthAccessTokenSecret("A6JNOKFYUejFQwbetTfkC4KJ4ofPPsSfuYTvfu30K0Str");
	    
	    return new TwitterFactory(cb.build()).getInstance();
	}

}