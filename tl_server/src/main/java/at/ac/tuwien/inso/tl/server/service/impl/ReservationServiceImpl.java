package at.ac.tuwien.inso.tl.server.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.ac.tuwien.inso.tl.dao.CustomerDao;
import at.ac.tuwien.inso.tl.dao.ReservationDao;
import at.ac.tuwien.inso.tl.dao.TicketDao;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;
import at.ac.tuwien.inso.tl.model.Reservation;
import at.ac.tuwien.inso.tl.model.Ticket;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService
{
	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private CustomerDao customerDao;

	@Override
	public Reservation save(ReservationDto dto) throws ServiceException
	{
		if(dto.getTickets() == null || dto.getTickets().isEmpty() || dto.getCustomer() == null){
			throw new ServiceException();
		}
		
		Reservation r = new Reservation();
		r.setCustomer(this.customerDao.findOne(dto.getCustomer().getId()));
		r.setReservationNumber("R-" + String.format("%06d", (new Random()).nextInt(1000000)));
		
		Reservation result;
		try
		{
			result = reservationDao.save(r);

			List<Ticket> ts = new ArrayList<Ticket>();
			for (TicketDto tdto : dto.getTickets())
			{
				Ticket t = this.ticketDao.findOne(tdto.getId());
				t.setReservation(result);
				ts.add(t);
			}
			this.ticketDao.save(ts);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}

		return result;
	}

	@Override
	public Reservation getReservation(Integer id) throws ServiceException
	{
		try
		{
			return this.reservationDao.findByIdWithRelatedEntities(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Reservation> findReservations(String search) throws ServiceException
	{
		try
		{
			if (search == null || search.isEmpty())
			{
				return this.reservationDao.findAll();
			} else
			{
				return this.reservationDao.findReservationLike(search.toUpperCase());
			}
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(ReservationDto dto) throws ServiceException
	{
		try
		{
			Reservation r = this.reservationDao.findByIdWithRelatedEntities(dto.getId());

			// set new customer (may be the same as before)
			r.setCustomer(this.customerDao.findOne(dto.getCustomer().getId()));
			
			if(dto.getTickets() != null){
				// update tickets
				List<Ticket> ts = new ArrayList<Ticket>();
				List<Integer> ids = new ArrayList<Integer>();
				for (TicketDto tdto : dto.getTickets())
				{
					Ticket t = this.ticketDao.findOne(tdto.getId());
					ts.add(t);
					ids.add(t.getId());
					t.setReservation(r);
				}
				for (Ticket t : r.getTickets())
				{
					if (!ids.contains(t.getId()))
					{
						t.setReservation(null);
						ts.add(t);
					}
				}
				this.ticketDao.save(ts);
			}
			
			try
			{
				reservationDao.save(r);
			} catch (Exception e)
			{
				throw new ServiceException(e);
			}
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Integer id) throws ServiceException
	{
		try
		{
			Reservation r = this.reservationDao.findByIdWithRelatedEntities(id);
			for (Ticket t : r.getTickets())
			{
				t.setReservation(null);
				this.ticketDao.save(t);
			}
			this.reservationDao.delete(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}
}
