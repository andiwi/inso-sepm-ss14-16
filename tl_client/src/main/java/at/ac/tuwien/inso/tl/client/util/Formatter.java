package at.ac.tuwien.inso.tl.client.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class Formatter
{
	public static String currency(int amount)
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		return nf.format(amount / 100);
	}
	
	public static String currency(float amount)
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		return nf.format(amount / 100);
	}

	public static String dateTimeShort(Date date)
	{
		if (date == null)
		{
			return "";
		}
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,
				Locale.GERMANY);
		return df.format(date);
	}

	public static String dateShort(Date date)
	{
		if (date == null)
		{
			return "";
		}
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY);
		return df.format(date);
	}
}
