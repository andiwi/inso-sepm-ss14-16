package at.ac.tuwien.inso.tl.datagenerator.generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.LocationDao;
import at.ac.tuwien.inso.tl.model.Location;

@Component
public class LocationGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(LocationGenerator.class);

	@Autowired
	LocationDao dao;

	public void generate()
	{
		LOG.info("+++++ Generate Location Data +++++");

		Location l = new Location();
		l.setId(1);
		l.setTitle("Wiener Staatsoper");
		l.setDescription("Die Wiener Staatsoper, das „Erste Haus am Ring“, ist eines der bekanntesten Opernhäuser der Welt und befindet sich im 1. Wiener Gemeindebezirk Innere Stadt. Sie wurde am 25. Mai 1869 mit einer Premiere von Don Giovanni von Mozart eröffnet. Aus den Mitgliedern des Staatsopernorchesters rekrutieren sich u. a. die Wiener Philharmoniker. Der Chor der Wiener Staatsoper tritt extern als Konzertvereinigung Wiener Staatsopernchor auf.");
		l.setStreet("Opernring 2");
		l.setPostcode("1010");
		l.setCity("Wien");
		l.setCountry("Österreich");
		this.dao.save(l);

		l = new Location();
		l.setId(2);
		l.setTitle("Großes Festspielhaus");
		l.setDescription("Das Große Festspielhaus in Salzburg (von 1960 bis 1962 Neues Festspielhaus, seit 1963 Großes Festspielhaus) ist eine der Spielstätten der Salzburger Festspiele und befindet sich in der Altstadt, es ist teilweise in den Mönchsberg hinein gebaut.");
		l.setStreet("Hofstallgasse 1");
		l.setPostcode("5020");
		l.setCity("Salzburg");
		l.setCountry("Österreich");
		this.dao.save(l);

		l = new Location();
		l.setId(3);
		l.setTitle("Flex");
		l.setDescription("Das Flex in Wien ist ein zwischen der U-Bahn-Station Schottenring und der Augartenbrücke gelegener Musikclub und in dieser Funktion Schauplatz von Auftritten lokaler und internationaler Musikgruppen und DJs.");
		l.setStreet("Augartenbrücke 1");
		l.setPostcode("1010");
		l.setCity("Wien");
		l.setCountry("Österreich");
		this.dao.save(l);
	}
}
