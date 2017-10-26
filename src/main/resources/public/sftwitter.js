var Tweet = Backbone.Model.extend({
	defaults: {
    	tweetContent: ''
	}
});

var TweetCollection = Backbone.Collection.extend({
	model: Tweet,
	
	initialize: function() {
		console.log("initialize tweets collection");
		
	},
	
	url: 'https://frozen-plateau-10170.herokuapp.com/tweets',
	
	defaults: {
    count: 10
	},
	
	filtered: function(query) {
		console.log("filtering Tweet List");
		var filteredTweetCollection = this.filter(function (tweet) {
			console.log("filtering tweet.. " + tweet.get("tweetContent"));
            return tweet.get("tweetContent").indexOf(query) !== -1;
        });
		console.log("filteredTweets:" + filteredTweetCollection);
        return new TweetCollection(filteredTweetCollection);
	}
});


var TweetListView = Backbone.View.extend({
	model: TweetCollection,
	template: '',
	el: '#container',
	
	initialize: function() {
		console.log("initialize tweetlist view");
		//this.listenTo(this.model, "change", this.render);
		this.template = _.template($('#tweet-list-template').html());
	},

	render: function() {
		console.log("rendering TweetListView");
		this.$el.html(this.template(this.collection));
		
		return this;
	},
	
//	filterTweets: function() {
//		var query = $('#searchbox').val()
//		console.log("filtering tweets (view):"+ query);
//		this.model = tweetCollection.filtered(query);
//		this.render();
//	}
});


//I'm sure there is a more "Backbone" way of doing this
//But I clearly have a ways to go to get more familiar with Backbone
$('#searchbutton').click(function(){
	
	//Grab the search query string from the input textbox
	var query = $('#searchbox').val().toLowerCase();
	console.log('searching for ' + query);	
	
	//Find all tweet elements
    var ul = $("#tweetlistul");
    var tweets = ul.children('li');

    // Loop through all tweets, and hide those who don't match the query
    for (i = 0; i < tweets.length; i++) {
    	var tweettext = $(tweets[i]).find('.tweet-text').text().toLowerCase();
    	console.log("filtering " + tweettext);
        if (!query || tweettext.indexOf(query) > -1) {
        	$(tweets[i]).show();
        } else {
        	$(tweets[i]).hide();
        }
    }
});

// Clear search box and show all tweets
$('#clearsearchbutton').click(function(){
	$('#searchbox').val("");
	$('#searchbutton').click();
});

// Enter key inside text box will trigger search 
$('#searchbox').on('keyup', function (e) {
    if (e.keyCode == 13) {
    	$('#searchbutton').click();
    }
});

// Fetch tweets and render view
function updateTweetCollection() {
	tweetCollection.fetch().then(function() {
		  console.log(tweetCollection);
		  var tweetListView = new TweetListView();
		  tweetListView.render();
		});
}

// Fetch and show tweets
var tweetCollection = new TweetCollection();
updateTweetCollection();

// Set timer to update tweets every 60 seconds
$(document).ready(function() {
	setInterval(updateTweetCollection, 60*1000); //run every 60 seconds
});