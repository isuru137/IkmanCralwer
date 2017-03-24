package com.isamarasinghe.factory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class IkmanCrawler extends Crawler
{
	public static void main( String[] args )
	{
		new IkmanCrawler().crawl( "http://ikman.lk/en/ads/rajagiriya/houses?sort=date&by_paying_member=0&type=for_rent&filters%5B0%5D%5Btype%5D=money&filters%5B0%5D%5Bkey%5D=price&filters%5B0%5D%5Bmin%5D=12500&filters%5B0%5D%5Bmax%5D=200000&filters%5B0%5D%5Bminimum%5D=10000&filters%5B0%5D%5Bmaximum%5D=35000&filters%5B1%5D%5Btype%5D=numeric&filters%5B1%5D%5Bkey%5D=house_size&filters%5B1%5D%5Bmin%5D=37.1612&filters%5B1%5D%5Bmax%5D=1858.06&filters%5B1%5D%5Bminimum%5D=&filters%5B1%5D%5Bmaximum%5D=&filters%5B2%5D%5Btype%5D=enum&filters%5B2%5D%5Bkey%5D=bedrooms&filters%5B3%5D%5Btype%5D=enum&filters%5B3%5D%5Bkey%5D=bathrooms"  ).forEach( item->
		{
			System.out.println(item.toString());
		});
	}


	@Override
	public List<ContentItem> crawl( String url )
	{
		List<ContentItem> contentItems = new ArrayList<>(  );
		Document doc = null;
		try
		{
			doc = Jsoup.connect(url).get();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		if( doc!= null )
		{
			Elements ads = doc.select("div[class='item-content']");

			ads.forEach( ad -> {
				Elements title = ad.getElementsByAttributeValue( "class", "item-title h4" );
				Elements roomDesc = ad.getElementsByAttributeValue( "class", "item-meta" );
				Elements price = ad.getElementsByAttributeValue( "class", "item-info" );

				ContentItem contentItem = new ContentItem();
				contentItem.setTitle( title.get( 0 ).html() );
				contentItem.setUrl( title.get( 0 ).absUrl( "href" ) );
				if( roomDesc != null && roomDesc.size() > 0 )
				{
					contentItem.setAdditionalInfo( roomDesc.get( 0 ).html().replaceAll( "<span>", "" ).replaceAll( "</span>", "" ) );
				}
				else
				{
					contentItem.setAdditionalInfo( "" );
				}
				contentItem.setPrice( price.get( 0 ).child( 0 ).html() );
				contentItems.add( contentItem );
			} );
		}
		return contentItems;
	}
}
