package com.isamarasinghe;

import com.isamarasinghe.factory.ContentItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class Cache
{
	private static HashMap<String, List<String>> cacheItems = new HashMap<>(  );

	public static boolean isNotEmpty( String url )
	{
		return cacheItems.containsKey( url ) && cacheItems.get( url ) != null;
	}

	public static void putAll( String url, List<ContentItem> contentItems )
	{
		List<String> contentItemsUrls =
				contentItems.stream()
						.map(ContentItem::getUrl)
						.collect( Collectors.toList() );

		cacheItems.put( url, contentItemsUrls );
	}


	public static List<ContentItem> findNewItems( String url, List<ContentItem> contentItems )
	{
		List<String> cachedUrls = cacheItems.get( url );

		List<ContentItem> newContentItems = contentItems.stream()
				.filter( contentItem -> !cachedUrls.contains( contentItem.getUrl() ) )
				.collect( Collectors.toList() );

		return newContentItems;
	}

	public static void main( String[] args )
	{
		List<ContentItem> list = new ArrayList<>(  );
		ContentItem cm1 = new ContentItem();
		cm1.setUrl( "1" );
		list.add( cm1 );
		ContentItem cm2 = new ContentItem();
		cm2.setUrl( "2" );
		list.add( cm2 );
		ContentItem cm3 = new ContentItem();
		cm3.setUrl( "3" );
		list.add( cm3 );
		ContentItem cm4 = new ContentItem();
		cm4.setUrl( "4" );
		list.add( cm4 );

		putAll( "a", list );

		List<ContentItem> list2 = new ArrayList<>(  );
		ContentItem cmx = new ContentItem();
		cmx.setUrl( "2" );
		list2.add( cmx );
		ContentItem cmxx = new ContentItem();
		cmxx.setUrl( "3" );
		ContentItem cmxxx = new ContentItem();
		cmxxx.setUrl( "5" );
		list2.add( cmxxx );
		findNewItems( "a", list2 ).forEach( System.out::println );
	}

	public static void put( String url, ContentItem contentItem )
	{
		List<String> list =  cacheItems.get( url );
		if( list == null )
		{
			list = new ArrayList<>( 1 );
			cacheItems.put( url, list );
		}
		list.add( contentItem.getUrl() );
	}
}
