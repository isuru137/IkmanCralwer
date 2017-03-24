package com.isamarasinghe;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class Bookmark
{
	private String name, url;
	public Bookmark( String name, String url)
	{
		this.name = name;
		this.url = url;
	}

	public String getName()
	{
		return name;
	}

	public String getUrl()
	{
		return url;
	}
}
