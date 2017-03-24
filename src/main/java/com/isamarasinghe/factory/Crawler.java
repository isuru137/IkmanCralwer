package com.isamarasinghe.factory;

import java.util.List;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public abstract class Crawler
{
	public abstract List<ContentItem> crawl( String url );
}
