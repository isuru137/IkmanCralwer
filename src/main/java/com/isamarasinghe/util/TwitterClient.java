package com.isamarasinghe.util;

import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class TwitterClient
{
	public static void main( String[] args )
	{
		// access_secret obtained by authentication user's twitter account
		String directMessage= "Hi, this is just a test message. 1";
		String twitterName = "isuru137";// twitter name of the receiver.
		DirectMessage dm = sendDM( twitterName, directMessage );
		if( dm != null )
		{
			System.out.println(dm);
		}
	}
	public static DirectMessage sendDM(String twitterName, String directMessage)
	{
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		try
		{
			return twitter.sendDirectMessage( twitterName, directMessage );
		}
		catch ( TwitterException e )
		{
			e.printStackTrace();
		}
		return null;
	}
}
