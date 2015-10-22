package at.ac.tuwien.inso.tl.server.util;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.NewsDto;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.OrderItemDto;
import at.ac.tuwien.inso.tl.dto.ParticipationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.model.Artist;
import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.model.News;
import at.ac.tuwien.inso.tl.model.Order;
import at.ac.tuwien.inso.tl.model.OrderItem;
import at.ac.tuwien.inso.tl.model.Participation;
import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.model.Sex;
import at.ac.tuwien.inso.tl.model.Show;

public class DtoToEntity
{

	public static News convert(NewsDto newsDto)
	{
		News news = new News();
		news.setTitle(newsDto.getTitle());
		news.setDescription(newsDto.getNewsText());
		return news;
	}

	public static Customer convert(CustomerDto dto)
	{
		Customer customer = new Customer();

		customer.setId(dto.getId());

		customer.setPoints(dto.getPoints());

		customer.setCustomerNumber(dto.getCustomerNumber());

		customer.setFirstname(dto.getFirstname());
		customer.setLastname(dto.getLastname());

		if (dto.getSex() == at.ac.tuwien.inso.tl.dto.Sex.Herr)
		{
			customer.setSex(Sex.m);
		}
		if (dto.getSex() == at.ac.tuwien.inso.tl.dto.Sex.Frau)
		{
			customer.setSex(Sex.f);
		}
		customer.setDateOfBirth(dto.getDateOfBirth());

		customer.setEmail(dto.getEmail());
		customer.setTelephone(dto.getTelephone());

		return customer;
	}

	//
	// ARTIST
	//

	public static Artist convert(ArtistDto dto)
	{
		Artist artist = new Artist();
		List<Participation> dtoList = new ArrayList<Participation>();

		artist.setId(dto.getId());
		artist.setFirstname(dto.getFirstname());
		artist.setLastname(dto.getLastname());
		artist.setDescription(dto.getDescription());

		for (ParticipationDto p : dto.getParticipations())
		{
			dtoList.add(convert(p));
		}

		artist.setParticipations(dtoList);

		return artist;
	}

	public static Participation convert(ParticipationDto dto)
	{
		Participation model = new Participation();

		model.setId(dto.getID());
		model.setArtistRole(dto.getArtistRole());
		model.setArtist(convert(dto.getArtist()));

		Show show = new Show();
		show.setId(dto.getShow().getId());
		model.setShow(show);

		return model;
	}

	//
	// SHOW
	//

	public static Performance convert(PerformanceDto dto)
	{
		Performance performance = new Performance();

		performance.setId(dto.getId());
		performance.setDuration(dto.getDuration());
		// TODO performance.setRoom(dto.getRoom());
		// TODO performance.setShow(dto.getShow());
		performance.setStartsAt(dto.getStartsAt());

		return performance;

	}

	//
	// ORDER
	//

	public static OrderItem convert(OrderItemDto orderItem)
	{
		OrderItem dto = new OrderItem();
		dto.setTitle(orderItem.getTitle());
		return dto;
	}

	public static at.ac.tuwien.inso.tl.model.MethodOfPayment convert(
			at.ac.tuwien.inso.tl.dto.MethodOfPayment method)
	{
		if (method == null)
			return null;
		switch (method) {
		case CREDITCARD:
			return at.ac.tuwien.inso.tl.model.MethodOfPayment.CREDITCARD;
		case CASH:
			return at.ac.tuwien.inso.tl.model.MethodOfPayment.CASH;
		default:
			return at.ac.tuwien.inso.tl.model.MethodOfPayment.BANKACCOUNT;
		}
	}

	public static Order convert(OrderDto order)
	{
		Order orderdto = new Order();
		orderdto.setId(order.getId());
		orderdto.setOrderedAt(order.getOrderedAt());
		orderdto.setMethodOfPayment(convert(order.getMethodOfPayment()));
		orderdto.setCustomer(convert(order.getCustomer()));
		List<OrderItem> liste = new ArrayList<OrderItem>();
		for (OrderItemDto oi : order.getOrderItems()){
			liste.add(convert(oi));
		}
		orderdto.setOrderItems(liste);
		orderdto.setPaymentReference(order.getPaymentReference());
		orderdto.setPriceTotal(order.getPriceTotal());

		/*
		 * orderdto.setBillingStreet(order.getBillingAdress().getStreet());
		 * orderdto.setBillingCity(order.getBillingAdress().getCity());
		 * orderdto.setBillingCountry(order.getBillingAdress().getCountry());
		 * orderdto.setBillingPostcode(order.getBillingAdress().getPostcode());
		 * 
		 * orderdto.setDeliveryStreet(order.getDeliveryAdress().getStreet());
		 * orderdto.setDeliveryCity(order.getDeliveryAdress().getCity());
		 * orderdto.setBillingCountry(order.getDeliveryAdress().getCountry());
		 * orderdto
		 * .setDeliveryPostcode(order.getDeliveryAdress().getPostcode());
		 */

		return orderdto;
	}
}
