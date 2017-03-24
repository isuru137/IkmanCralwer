package com.isamarasinghe.factory;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class ContentItem
{
	private String title, url, price, additionalInfo;

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl( String url )
	{
		this.url = url;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice( String price )
	{
		this.price = price;
	}

	public String getAdditionalInfo()
	{
		return additionalInfo;
	}

	public void setAdditionalInfo( String additionalInfo )
	{
		this.additionalInfo = additionalInfo;
	}

	@Override
	public String toString()
	{
		return String.format( "%s %s %s %s", title, price, additionalInfo, url );
	}
}
