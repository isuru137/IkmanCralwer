package com.isamarasinghe;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
import com.isamarasinghe.factory.ContentItem;
import com.isamarasinghe.factory.CrawlerFactory;
import com.isamarasinghe.util.DomainUtil;
import com.isamarasinghe.util.PropertyReader;
import com.isamarasinghe.util.TwitterClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	//Every 15 minutes
	@Scheduled(fixedRate = 900000)
	public void reportCurrentTime() {

		Application.LOGGER.info("Job starts: {}", dateFormat.format(new Date()));

		List<Bookmark> bookmarks =  PropertyReader.readBookmarks( "bookmarks.properties" );
		List<String> userNames = PropertyReader.readUsername( "users.properties" );

		Application.LOGGER.info("URL count: "+ bookmarks.size());

		bookmarks.forEach( bookmark ->{
			String url = bookmark.getUrl();
			String name = bookmark.getName();
			String domainName = DomainUtil.extractDomainName( url );
			List<ContentItem> contentItems = CrawlerFactory.getCrawler( domainName ).crawl( url );

			if( Cache.isNotEmpty( url ))
			{

				List<ContentItem> itemsToNotify = Cache.findNewItems( url, contentItems );
				Application.LOGGER.info("New Items count: "+ itemsToNotify.size() + ", Bookmark: "+ name );

//				TwitterClient.sendDM( "isuru137", contentItems.get( 0 ).toDMString() );

				itemsToNotify.forEach( contentItem -> {

					userNames.forEach( username-> {
						TwitterClient.sendDM( username, contentItem.toString() );
						Application.LOGGER.info("Sent DM to: "+username+", Message : "+ contentItem.toString() );
						Cache.put( url, contentItem );
					} );


				} );
			}
			else
			{
				Application.LOGGER.info("Filling cache ads count: "+contentItems.size()+", Bookmark: "+ name);
				Cache.putAll( url, contentItems);
			}
		} );

		Application.LOGGER.info("Job ends: {}", dateFormat.format(new Date()));
	}
}
