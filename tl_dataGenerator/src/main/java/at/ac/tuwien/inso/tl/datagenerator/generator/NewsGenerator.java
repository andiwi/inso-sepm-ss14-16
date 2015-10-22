package at.ac.tuwien.inso.tl.datagenerator.generator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.NewsDao;
import at.ac.tuwien.inso.tl.model.News;

@Component
public class NewsGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(NewsGenerator.class);

	@Autowired
	private NewsDao dao;

	public void generate()
	{
		LOG.info("+++++ Generate News Data +++++");

		Calendar cal = Calendar.getInstance();

		News n = new News();
		cal.set(2013, 7, 10);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Pacific Rim (Kinostart: 18. Juli 2013)");
		n.setDescription("Fremdartige und monströse Kreaturen, die als Kaiju bekannt sind und aus den Tiefen des Pazifischen Ozeans emporsteigen, haben in einem Krieg bereits Millionen Menschenleben gefordert. Sogar eine Atombombe konnte die außerirdischen Wesen nicht stoppen. Um die Kaiju zu besiegen, entwickelten zwei Wissenschaftler überdimensionale Roboter, die \"Jäger\", welche von zwei Piloten durch eine neurale Brücke gesteuert werden. Doch selbst die Jäger scheinen nicht gegen die außerirdische Macht anzukommen. Mit ihren letzten beiden Verteidigern, dem abgehalfterten Ex-Piloten Raleigh Becket (Charlie Hunnam) und der unerfahrenen Novizin Mako Mori (Rinko Kikuchi) holt die Menschheit zum alles entscheidenden Schlag gegen die Kaiju aus. Mit Unterstützung von Makos Adoptivvater, dem legendären britischen Jäger-Piloten Stacker Pentecost (Idris Elba), begibt sich das ungleiche Duo auf die gefährliche Mission, um die Menschheit vor dem Ende zu bewahren.");
		dao.save(n);

		n = new News();
		cal.set(2013, 9, 1);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Wolverine: Weg des Kriegers  (Kinostart: 08. August 2013)");
		n.setDescription("Logan (Hugh Jackman), auch bekannt als der Mutant Wolverine, wird unter zwielichtigen Umständen nach Japan gelockt, welches er seit dem zweiten Weltkrieg nicht mehr betreten hat. In einer Welt von Smaurai und Yakuza findet er sich unerwartet auf der Flucht mit einer ebenso schönen wie mysteriösen Erbin wieder. Zum ersten Mal wird er mit der Aussicht auf Sterblichkeit konfrontiert, in einer Zeit in der er an seine emotionalen und physischen Grenzen gedrückt wird. Er ist nicht nur gezwungen gegen den übermächtigen Gegner und seinen größten Erzfeind, Silver Samurai (Will Yun Lee), anzukämpfen, sondern auch gegen die Schatten seiner Vergangenheit. Während er versucht seinen Gegner trotz des tödlichen Samurai-Stahls zu besiegen muss er gleichzeitig einen eigenen inneren Kampf gegen seine Existenz als Mutant ausfechten. Doch an jedem aussichtslos erscheinenden Problem wächst er und wird dadurch letztendlich stärker als jemals zuvor.");
		dao.save(n);

		n = new News();
		cal.set(2014, 4, 9);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Gov’t Mule");
		n.setDescription("Southern Rock vom Feinsten erwartet die Besucher des Wiener W.U.K. am Freitag, 12. Juli, wenn sich Ausnahme-Songwriter und –Slidegitarrist Warren Haynes mit seiner Band Gov’t Mule die Ehre gibt.");
		dao.save(n);

		n = new News();
		cal.set(2014, 4, 19);
		n.setCreatedOn(cal.getTime());
		n.setTitle("La Bohème");
		n.setDescription("Giacomo Puccinis Meisterwerk im Römersteinbruch! Mit \"La Bohème\" bringen die Opernfestspiele St. Margarethen vom 10. Juli bis 25. August 2013 ein berauschendes Operndrama auf die Bühne.");
		dao.save(n);

		n = new News();
		cal.set(2014, 4, 19);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Leben ohne Müll");
		n.setDescription("Ein Leben ohne Müll? Dorothea Kocsis hat diesen Versuch erfolgreich realisiert – durch Vermeiden, Wiederverwenden und Recyceln konnte sie alles, was in ihrem Fünf-Personen-Haushalt an Müll anfällt, schrittweise reduzieren. Mit geändertem Einkaufsverhalten schlägt sie dem Konsumwahn ein Schnippchen und gewinnt vor allem eines: Zeit und Energie. Frei nach dem Motto: Der Einkaufsweg ist das Ziel, slow shopping – slow-littering – slow-living!");
		dao.save(n);

		n = new News();
		cal.set(2014, 5, 14);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Gironcoli: Context");
		n.setDescription("Die Schau im Belvedere wird keine herkömmliche Retrospektive sein, sondern das Werk Bruno Gironcolis erstmalig in ein Netzwerk von Beziehungen zu klassischen wie zu wichtigen zeitgenössischen Positionen stellen.");
		dao.save(n);

		n = new News();
		cal.set(2014, 5, 14);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Toni Innauer - Am Puls des Erfolgs und kritische Punkte");
		n.setDescription("Toni Innauer liest aus seinen beiden Bestsellern und erzählt brandaktuelle und nostalgisch humorvolle Episoden aus der jahrzehntelang erfolgreichen Kultur des Österreichischen Skisprungsports. Die Superadler und ihre Vorgänger sind der fliegende Beweis dafür, dass man mit den Weltbesten mithalten kann, wenn der \"Spirit auf excellence\" gelebt wird und faire Spielregeln bewusst gepflegt werden. Der Spitzensport spiegelt aber auch gnadenlos die belastenden Entwicklungen, die wir alle im Wirtschafts- und Berufsleben erfahren, wenn beim Kampf um Erfolg und Marktanteile das gesunde Augenmaß verloren geht und Gier die Oberhand gewinnt.");
		dao.save(n);

		n = new News();
		cal.set(2014, 5, 14);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Ein Sommernachtstraum");
		n.setDescription("Die romantischen Gärten von Schloss Pötzleinsdorf werden für Shakespeares vielleicht beste Komödie zu den Wäldern rund um Athen. Das Stück verfolgt die abenteuerlichen Erlebnisse vier junger Liebender, die nachts aus dem engen Korsett des Athener Hoflebens in die Freiheit der Wälder ausbrechen. Doch der Wald ist auch Heimat der Feenkönigin Titania, ihrem Gemahl Oberon und seinem treuem Begleiter Puck. Die Feenwelt ist nach einem Streit zwischen Oberon und Titania aus den Fugen geraten und als sich zu den mythischen Elfen die Liebenden und schließlich auch noch eine Gruppe Amateurschauspieler gesellen, ist das Chaos perfekt.");
		dao.save(n);

		n = new News();
		cal.set(2014, 5, 19);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Elfie Semotan");
		n.setDescription("Die Grenzen zwischen Werbefotografie und künstlerischer Fotografie sind in der Arbeit von Elfie Semotan, die Künstler wie Martin Kippenberger inspirierte, fließend. Dass von Elfie Semotan daher nicht als Modefotografin im klassischen Sinne gesprochen werden kann, führt die Schau eindrucksvoll vor Augen. Elfie Semotan (*1941), die Grande Dame der österreichischen Fotografie, arbeitete nach ihrer Ausbildung als Modedesignerin jahrelang als Modell, bevor sie Ende der 1960er-Jahre hinter die Kamera wechselte und mit ihren lyrischen Modefotografien internationalen Ruhm erlangte.");
		dao.save(n);

		n = new News();
		cal.set(2014, 5, 19);
		n.setCreatedOn(cal.getTime());
		n.setTitle("Theater Asou - Der Leuchtturmwärter");
		n.setDescription("Michael Hofkirchner hat sich, nach dem in Jordanien ausgezeichnetem Stück Konnichiwa, der Meister, an sein zweites Clownsolo gewagt. Wie es sich so 'am Ende der Welt' lebt, erfahren Sie in den Aufführungen des Theaters Asou. Ein einsamer Leuchtturmwärter auf einer Insel am Rande der Welt zwischen Wasser und Himmel, umgeben von freundlichen Fischen und frechen Möwen. Ein helles Licht und eine große Aufgabe. Käpt`n Ahab, Käpt`n Sparrow, Käpt`n Onedin, Käpt`n Nemo, Käpt`n Kirk und Columbus, sie alle brauchen den Leuchtturmwärter, sein Licht und seine Passion zu putzen!");
		dao.save(n);
	}
}
