package at.ac.tuwien.inso.tl.datagenerator;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import at.ac.tuwien.inso.tl.datagenerator.generator.ArticleGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.CustomerGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.DataGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.EmployeeGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.LocationGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.NewsGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.OrderGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.PerformanceGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.ReservationGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.RoomGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.SeatGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.ShowGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.TicketGenerator;
import at.ac.tuwien.inso.tl.datagenerator.generator.ArtistGenerator;

public class DataGeneratorMain
{
	private static final Logger LOG = Logger.getLogger(DataGeneratorMain.class);
	private ApplicationContext context;

	public static void main(String[] args)
	{
		DataGeneratorMain generator = new DataGeneratorMain();
		generator.generateData();
	}

	private void generateData()
	{
		context = new ClassPathXmlApplicationContext("/datagenerator-context.xml");

		LOG.info("---------- START DATA GENERATION ----------");

		DataGenerator eg = (EmployeeGenerator) context.getBean("employeeGenerator");
		eg.generate();

		DataGenerator ng = (NewsGenerator) context.getBean("newsGenerator");
		ng.generate();

		DataGenerator cg = (CustomerGenerator) context.getBean("customerGenerator");
		cg.generate();

		DataGenerator lg = (LocationGenerator) context.getBean("locationGenerator");
		lg.generate();

		// DataGenerator ag =
		// (ArtistGenerator)context.getBean("artistGenerator");
		// ag.generate();

		DataGenerator g = (RoomGenerator) context.getBean("roomGenerator");
		g.generate();

		g = (ShowGenerator) context.getBean("showGenerator");
		g.generate();

		g = (ArtistGenerator) context.getBean("artistGenerator");
		g.generate();

		g = (PerformanceGenerator) context.getBean("performanceGenerator");
		g.generate();

		g = (SeatGenerator) context.getBean("seatGenerator");
		g.generate();

		g = (TicketGenerator) context.getBean("ticketGenerator");
		g.generate();

		g = (ReservationGenerator) context.getBean("reservationGenerator");
		g.generate();

		g = (OrderGenerator) context.getBean("orderGenerator");
		g.generate();

		g = (ArticleGenerator) context.getBean("articleGenerator");
		g.generate();

		LOG.info("---------- DATA GENERATION COMPLETE ----------");
	}
}
