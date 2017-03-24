package com.isamarasinghe.factory;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class CrawlerFactory
{
	private CrawlerFactory()
	{

	}

	public static Crawler getCrawler( String siteName )
	{
		Crawler crawler = null;
		if( siteName.equalsIgnoreCase( "ikman.lk" ))
		{
			crawler = new IkmanCrawler();
		}
		return crawler;

	}
}
