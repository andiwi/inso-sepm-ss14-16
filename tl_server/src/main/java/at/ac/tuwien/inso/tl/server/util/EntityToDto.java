package at.ac.tuwien.inso.tl.server.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import at.ac.tuwien.inso.tl.dto.AdressDto;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.LocationDto;
import at.ac.tuwien.inso.tl.dto.NewsDto;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.OrderItemDto;
import at.ac.tuwien.inso.tl.dto.ParticipationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.RoomDto;
import at.ac.tuwien.inso.tl.dto.SeatDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;
import at.ac.tuwien.inso.tl.model.Article;
import at.ac.tuwien.inso.tl.model.Artist;
import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.model.Location;
import at.ac.tuwien.inso.tl.model.News;
import at.ac.tuwien.inso.tl.model.Order;
import at.ac.tuwien.inso.tl.model.OrderItem;
import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.model.Reservation;
import at.ac.tuwien.inso.tl.model.Room;
import at.ac.tuwien.inso.tl.model.Seat;
import at.ac.tuwien.inso.tl.model.Sex;
import at.ac.tuwien.inso.tl.model.Show;
import at.ac.tuwien.inso.tl.model.Ticket;

public class EntityToDto
{
	private static final Logger LOG = Logger.getLogger(EntityToDto.class);

	//
	// NEWS
	//
	public static List<NewsDto> convertNews(List<News> news)
	{
		List<NewsDto> ret = new ArrayList<NewsDto>();
		if (null != news)
		{
			for (News n : news)
			{
				NewsDto dto = convert(n);

				ret.add(dto);
			}
		}

		return ret;
	}

	public static NewsDto convert(News news)
	{
		NewsDto dto = new NewsDto();

		dto.setTitle(news.getTitle());
		dto.setNewsText(news.getDescription());
		dto.setSubmittedOn(news.getCreatedOn());

		return dto;
	}

	//
	// ARTICLE
	//
	public static ArticleDto convert(Article article)
	{
		ArticleDto dto = new ArticleDto();

		dto.setId(article.getId());
		dto.setAvailable(article.getAvailable());
		dto.setDescription(article.getDescription());
		dto.setPrice(article.getPrice());
		dto.setTitle(article.getTitle());
		/*if (article.getShow() != null)
		{
			dto.setShow(convert(article.getShow()));
		}*/
		return dto;
	}

	public static List<ArticleDto> convertArticles(List<Article> articles)
	{
		List<ArticleDto> ret = new ArrayList<ArticleDto>();
		if (null != articles)
		{
			for (Article n : articles)
			{
				ArticleDto dto = convert(n);

				ret.add(dto);
			}
		}

		return ret;
	}

	//
	// CUSTOMER
	//

	public static List<CustomerDto> convertCustomers(List<Customer> customers)
	{
		List<CustomerDto> ret = new ArrayList<CustomerDto>();
		if (null != customers)
		{
			for (Customer n : customers)
			{
				CustomerDto dto = convert(n);

				ret.add(dto);
			}
		}

		return ret;
	}

	public static CustomerDto convert(Customer customer)
	{
		CustomerDto dto = new CustomerDto();

		dto.setId(customer.getId());

		dto.setPoints(customer.getPoints());

		dto.setCustomerNumber(customer.getCustomerNumber());

		if (customer.getSex() == Sex.m)
		{
			dto.setSex(at.ac.tuwien.inso.tl.dto.Sex.Herr);
		}
		if (customer.getSex() == Sex.f)
		{
			dto.setSex(at.ac.tuwien.inso.tl.dto.Sex.Frau);
		}
		dto.setDateOfBirth(customer.getDateOfBirth());

		dto.setFirstname(customer.getFirstname());
		dto.setLastname(customer.getLastname());

		dto.setEmail(customer.getEmail());
		dto.setTelephone(customer.getTelephone());

		return dto;
	}
	
	public static CustomerDto convertWithReservationsAndOrders(Customer customer) {
		CustomerDto dto = convert(customer);

		List<ReservationDto> reservations = new ArrayList<ReservationDto>();
		if (null != customer.getReservations())
		{
			for (Reservation r : customer.getReservations())
			{
				reservations.add(convert(r));
			}
		}
		dto.setReservations(reservations);

		List<OrderDto> orders = new ArrayList<OrderDto>();
		if (null != customer.getOrders())
		{
			for (Order o : customer.getOrders())
			{
				orders.add(convert(o));
			}
		}
		dto.setOrders(orders);
		
		return dto;
	}

	//
	// ARTIST
	//

	public static List<ArtistDto> convertArtists(List<Artist> artists)
	{
		List<ArtistDto> returnList = new ArrayList<ArtistDto>();
		if (null != artists)
		{
			for (Artist n : artists)
			{
				ArtistDto dto = convert(n);

				returnList.add(dto);
			}
		}

		return returnList;
	}

	public static ArtistDto convert(Artist artist)
	{
		ArtistDto dto = new ArtistDto();
		// List<ParticipationDto> partList = new ArrayList<ParticipationDto>();

		dto.setId(artist.getId());
		dto.setFirstname(artist.getFirstname());
		dto.setLastname(artist.getLastname());
		dto.setDescription(artist.getDescription());

		// for(ParticipationDto p : dto.getParticipations()) {
		// partList.add(p);
		// }
		//
		// dto.setParticipations(partList);

		return dto;
	}

	//
	// LOCATION
	//

	public static List<LocationDto> convertLocations(List<Location> locations)
	{
		List<LocationDto> returnList = new ArrayList<LocationDto>();
		if (null != locations)
		{
			for (Location l : locations)
			{
				returnList.add(convert(l));
			}
		}
		return returnList;
	}

	public static LocationDto convert(Location location)
	{
		LocationDto dto = new LocationDto();

		dto.setId(location.getId());
		dto.setTitle(location.getTitle());
		dto.setDescription(location.getDescription());
		dto.setStreet(location.getStreet());
		dto.setPostcode(location.getPostcode());
		dto.setCity(location.getCity());
		dto.setCountry(location.getCountry());

		return dto;
	}

	//
	// TICKET
	//

	public static TicketDto convert(Ticket ticket)
	{

		TicketDto dto = new TicketDto();
		dto.setId(ticket.getId());
		dto.setPrice(ticket.getPrice());
		dto.setSeat(convert(ticket.getSeat()));
		dto.setTicketNumber(ticket.getTicketNumber());
		if (ticket.getReservation() != null)
		{
			dto.setReservation(convert(ticket.getReservation()));
		}
		if (ticket.getOrderItem() != null)
		{
			dto.setOrderItem(convert(ticket.getOrderItem()));
		}
		return dto;
	}

	public static TicketDto convertWithPerformance(Ticket ticket)
	{
		TicketDto dto = convert(ticket);
		dto.setPerformance(convert(ticket.getPerformance()));
		return dto;
	}

	public static TicketDto convertWithoutOrderItemWithPerformance(Ticket ticket)
	{
		TicketDto dto = new TicketDto();
		dto.setId(ticket.getId());
		dto.setPrice(ticket.getPrice());
		dto.setSeat(convert(ticket.getSeat()));
		dto.setTicketNumber(ticket.getTicketNumber());
		if (ticket.getReservation() != null)
		{
			dto.setReservation(convert(ticket.getReservation()));
		}
		dto.setPerformance(convert(ticket.getPerformance()));
		return dto;
	}

	public static List<TicketDto> convertTickets(List<Ticket> tickets)
	{
		List<TicketDto> returnList = new ArrayList<TicketDto>();
		if (null != tickets)
		{
			for (Ticket t : tickets)
			{
				returnList.add(convert(t));
			}
		}
		return returnList;
	}

	public static List<TicketDto> convertTicketsWithPerformance(List<Ticket> tickets)
	{
		List<TicketDto> returnList = new ArrayList<TicketDto>();
		if (null != tickets)
		{
			for (Ticket t : tickets)
			{
				returnList.add(convertWithPerformance(t));
			}
		}
		return returnList;
	}

	//
	// RESERVATION
	//

	public static ReservationDto convert(Reservation reservation)
	{
		ReservationDto dto = convertWithoutCustomer(reservation);
		dto.setCustomer(convert(reservation.getCustomer()));
		return dto;
	}
	
	public static ReservationDto convertWithoutCustomer(Reservation reservation)
	{
		ReservationDto dto = new ReservationDto();
		dto.setId(reservation.getId());
		dto.setReservationNumber(reservation.getReservationNumber());
		return dto;
	}
	
	public static ReservationDto convertWithTickets(Reservation reservation)
	{
		ReservationDto dto = convert(reservation);
		dto.setTickets(convertTicketsWithPerformance(reservation.getTickets()));
		return dto;
	}

	public static List<ReservationDto> convertReservations(List<Reservation> reservations)
	{
		List<ReservationDto> returnList = new ArrayList<ReservationDto>();
		if (null != reservations)
		{
			for (Reservation r : reservations)
			{
				returnList.add(convert(r));
			}
		}
		return returnList;
	}

	//
	// PERFORMANCE
	//

	public static PerformanceDto convert(Performance performance)
	{
		PerformanceDto dto = new PerformanceDto();
		dto.setId(performance.getId());
		dto.setStartsAt(performance.getStartsAt());
		dto.setDuration(performance.getDuration());
		dto.setShow(convertWithoutPerformances(performance.getShow()));
		dto.setRoom(convert(performance.getRoom()));

		return dto;
	}

	public static PerformanceDto convertWithoutShow(Performance performance)
	{
		PerformanceDto dto = new PerformanceDto();

		dto.setId(performance.getId());
		dto.setStartsAt(performance.getStartsAt());
		dto.setDuration(performance.getDuration());
		dto.setRoom(convert(performance.getRoom()));

		return dto;
	}

	public static PerformanceDto convertWithTickets(Performance performance)
	{
		PerformanceDto dto = convert(performance);
		dto.setTickets(convertTickets(performance.getTickets()));
		return dto;
	}

	public static List<PerformanceDto> convertPerformances(List<Performance> performances)
	{
		List<PerformanceDto> returnList = new ArrayList<PerformanceDto>();

		if (performances != null)
		{
			for (Performance perf : performances)
			{
				returnList.add(convert(perf));
			}

		}

		return returnList;
	}

	public static List<PerformanceDto> convertPerformancesWithoutShow(List<Performance> performances)
	{
		List<PerformanceDto> returnList = new ArrayList<PerformanceDto>();

		if (performances != null)
		{
			for (Performance perf : performances)
			{
				returnList.add(convertWithoutShow(perf));
			}

		}

		return returnList;
	}

	//
	// SEAT
	//

	public static SeatDto convert(Seat seat)
	{
		SeatDto dto = new SeatDto();
		dto.setId(seat.getId());
		dto.setColumn(seat.getColumn());
		dto.setRow(seat.getRow());
		dto.setSeatNumber(seat.getSeatNumber());
		return dto;
	}

	//
	// SHOW
	//

	public static List<ShowDto> convertShows(List<Show> shows)
	{
		List<ShowDto> returnList = new ArrayList<ShowDto>();
		if (null != shows)
		{
			for (Show n : shows)
			{
				ShowDto dto = convertWithoutPerformances(n);

				returnList.add(dto);
			}
		}

		return returnList;
	}

	public static ShowDto convert(Show show)
	{
		ShowDto dto = new ShowDto();

		dto.setId(show.getId());
		dto.setTitle(show.getTitle());
		dto.setDescription(show.getDescription());
		dto.setShowType(show.getShowType().toString());
		dto.setPerformances(convertPerformancesWithoutShow(show.getPerformances()));

		return dto;
	}

	public static ShowDto convertWithoutPerformances(Show show)
	{
		ShowDto dto = new ShowDto();

		dto.setId(show.getId());
		dto.setTitle(show.getTitle());
		dto.setDescription(show.getDescription());
		dto.setShowType(show.getShowType().toString());

		return dto;
	}

	//
	// ORDER & ORDERITEM
	//

	public static OrderItemDto convert(OrderItem orderItem)
	{
		OrderItemDto dto = new OrderItemDto();
		dto.setTitle(orderItem.getTitle());
		
		if (orderItem.getTicket()!= null) {
			dto.setTicket(convertWithoutOrderItemWithPerformance(orderItem.getTicket()));
		}
		if (orderItem.getArticle()!= null) {
			ArticleDto temp = convert(orderItem.getArticle());
			temp.setAmount(orderItem.getAmount());
			dto.setArticle(temp);
		}
		dto.setOrder(convert(orderItem.getOrder()));
		return dto;
	}

	public static at.ac.tuwien.inso.tl.dto.MethodOfPayment convert(
			at.ac.tuwien.inso.tl.model.MethodOfPayment method)
	{
		if (method == null)
			return null;
		switch (method) {
		case CREDITCARD:
			return at.ac.tuwien.inso.tl.dto.MethodOfPayment.CREDITCARD;
		case CASH:
			return at.ac.tuwien.inso.tl.dto.MethodOfPayment.CASH;
		default:
			return at.ac.tuwien.inso.tl.dto.MethodOfPayment.BANKACCOUNT;
		}
	}

	public static OrderDto convert(Order order)
	{
		OrderDto orderdto = new OrderDto();
		orderdto.setId(order.getId());
		orderdto.setOrderNumber(order.getOrderNumber());
		orderdto.setOrderedAt(order.getOrderedAt());
		orderdto.setMethodOfPayment(convert(order.getMethodOfPayment()));
		orderdto.setCustomer(convert(order.getCustomer()));
		orderdto.setPaymentReference(order.getPaymentReference());
		orderdto.setPriceTotal(order.getPriceTotal());

		AdressDto billingAdress = new AdressDto();
		billingAdress.setCity(order.getBillingCity());
		billingAdress.setCountry(order.getBillingCountry());
		billingAdress.setPostcode(order.getBillingPostcode());
		billingAdress.setStreet(order.getBillingStreet());

		orderdto.setBillingAdress(billingAdress);

		AdressDto deliveryAdress = new AdressDto();
		deliveryAdress.setCity(order.getDeliveryCity());
		deliveryAdress.setCountry(order.getDeliveryCountry());
		deliveryAdress.setPostcode(order.getDeliveryPostcode());
		deliveryAdress.setStreet(order.getDeliveryStreet());

		orderdto.setDeliveryAdress(deliveryAdress);
		return orderdto;
	}

	public static OrderDto convertWithItems(Order order)
	{
		OrderDto orderdto = convert(order);
		List<OrderItemDto> l = new ArrayList<OrderItemDto>();
		for (OrderItem oi : order.getOrderItems())
		{
			l.add(convert(oi));
		}
		orderdto.setOrderItems(l);
		return orderdto;
	}

	public static List<OrderDto> convertOrdersWithItems(List<Order> orders)
	{
		List<OrderDto> ret = new ArrayList<OrderDto>();
		if (null != orders)
		{
			for (Order n : orders)
			{
				OrderDto dto = convertWithItems(n);

				ret.add(dto);
			}
		}

		return ret;
	}

	public static List<OrderDto> convertOrders(List<Order> orders)
	{
		List<OrderDto> ret = new ArrayList<OrderDto>();
		if (null != orders)
		{
			for (Order n : orders)
			{
				OrderDto dto = convert(n);

				ret.add(dto);
			}
		}

		return ret;
	}

	//
	// ROOM
	//

	public static RoomDto convert(Room room)
	{
		RoomDto dto = new RoomDto();

		dto.setId(room.getId());
		dto.setTitle(room.getTitle());

		return dto;
	}
}
