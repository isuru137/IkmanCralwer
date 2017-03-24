package com.isamarasinghe.util;

/**
 * SUMMARY
 *
 * @author Isuru Samarasinghe
 * @version 1, 2017-03-22.
 */
public class StringUtils
{
	public static boolean isNullOrEmpty( String input )
	{
		return input == null || input.length() == 0;
	}

	public static boolean isNN( String input )
	{
		return !isNullOrEmpty( input );
	}
}
