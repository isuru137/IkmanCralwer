package com.isamarasinghe.util;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class DomainUtil
{
	public static void main( String[] args )
	{
		System.out.println( extractDomainName( "http://ikman.lk/en/ads/rajagiriya/houses?sort=date&by_paying_member=0&type=for_rent&filters%5B0%5D%5Btype%5D=money&filters%5B0%5D%5Bkey%5D=price&filters%5B0%5D%5Bmin%5D=12500&filters%5B0%5D%5Bmax%5D=200000&filters%5B0%5D%5Bminimum%5D=10000&filters%5B0%5D%5Bmaximum%5D=35000&filters%5B1%5D%5Btype%5D=numeric&filters%5B1%5D%5Bkey%5D=house_size&filters%5B1%5D%5Bmin%5D=37.1612&filters%5B1%5D%5Bmax%5D=1858.06&filters%5B1%5D%5Bminimum%5D=&filters%5B1%5D%5Bmaximum%5D=&filters%5B2%5D%5Btype%5D=enum&filters%5B2%5D%5Bkey%5D=bedrooms&filters%5B3%5D%5Btype%5D=enum&filters%5B3%5D%5Bkey%5D=bathrooms" ));;
	}
	public static String extractDomainName(String url)
	{
		URI uri = null;
		try
		{
			uri = new URI(url);
		}
		catch ( URISyntaxException e )
		{
			e.printStackTrace();
		}
		if( url != null )
		{
			String domain = uri.getHost();
			return domain.startsWith("www.") ? domain.substring(4) : domain;
		}
		else
		{
			return null;
		}

	}
}
