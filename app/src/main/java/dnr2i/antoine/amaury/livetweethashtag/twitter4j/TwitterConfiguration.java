package dnr2i.antoine.amaury.livetweethashtag.twitter4j;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Alex & Jean on 04/02/15.
 */
public class TwitterConfiguration
{
    /**
     * Register your here app https://dev.twitter.com/apps/new and get your
     * consumer key and secret
     */
    private static String TWITTER_CONSUMER_KEY = "mxtRRBhF07wthFNnvSnnYQ0Xy";
    private static String TWITTER_CONSUMER_SECRET = "2KQLrBHgv5KBph6XicOYwnmq16iw9031QLIgxdgKdDx0HqDtVT";

    private static final String PREF_KEY_OAUTH_TOKEN = "359353442-cb0BXpx4bOSBuG2gUtbyXnMbPVgwigR2YCBVcf4K";
    private static final String PREF_KEY_OAUTH_SECRET = "20SPjOBnn8q0RinS9kP6nH7U7VZ00oWMMgjHnDbVPftk8";

    private static Configuration configuration = buildConfiguration();

    public TwitterConfiguration(){

    }

    private static Configuration buildConfiguration()
    {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
        builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);

        builder.setOAuthAccessToken(PREF_KEY_OAUTH_TOKEN);
        builder.setOAuthAccessTokenSecret(PREF_KEY_OAUTH_SECRET);

        Configuration configuration = builder.build();
        return configuration;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}