package com.isamarasinghe.util;

import com.isamarasinghe.Bookmark;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class PropertyReader
{
	public static void main(String[] args) {

		List<Bookmark> bookmarks =  readBookmarks( "bookmarks.properties" );
		bookmarks.forEach( bookmark -> System.out.println(bookmark.getName()) );

		List<String> users =  readUsername( "users.properties" );
		users.forEach(System.out::println);

	}

	public static List<Bookmark> readBookmarks( String filePath )
	{
		List<Bookmark> bookmarks = new ArrayList<>(  );
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filePath);

			// load a properties file
			prop.load(input);

			for ( int i = 1;  ; i++ )
			{
				String name = prop.getProperty( String.format( "bookmark%s.name", i ));
				String url = prop.getProperty( String.format( "bookmark%s.url", i ));
				if( url == null || url.length() == 0)
				{
					break;
				}
				else
				{
					Bookmark bookmark = new Bookmark(name, url);
					bookmarks.add( bookmark );
				}

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bookmarks;
	}

	public static List<String> readUsername( String filePath )
	{
		List<String> usernames = new ArrayList<>(  );
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filePath);

			// load a properties file
			prop.load(input);

			for ( int i = 1;  ; i++ )
			{
				String username = prop.getProperty( String.format( "username%s", i ));
				if( StringUtils.isNullOrEmpty( username ))
				{
					break;
				}
				else
				{
					usernames.add( username );
				}

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return usernames;
	}




}
