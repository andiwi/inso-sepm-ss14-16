package at.ac.tuwien.inso.tl.server.service;

import java.util.List;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.model.Reservation;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface ReservationService
{
	/**
	 * Saves the given Reservation object and returns the saved entity
	 * 
	 * @param reservation
	 *            object to persist
	 * @return the saved entity
	 * @throws ServiceException
	 */
	public Reservation save(ReservationDto reservation) throws ServiceException;

	/**
	 * Returns the reservation object identified by the given id
	 * 
	 * @param id
	 *            of the reservation object
	 * @return the reservation object
	 * @throws ServiceException
	 */
	public Reservation getReservation(Integer id) throws ServiceException;

	/**
	 * Returns a collection of all reservations that match the given string
	 * 
	 * @param search
	 *            string to look for
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Reservation> findReservations(String search) throws ServiceException;

	/**
	 * Saves the changes that were made to the reservation with the id
	 * of the given reservation.
	 * 
	 * @param Reservation				The reservation to update
	 * @return void
	 * @throws ServiceException
	 */
	public void update(ReservationDto dto) throws ServiceException;

	/**
	 * Deletes the reservation with the given id
	 * 
	 * @param id
	 * @return void
	 * @throws ServiceException
	 */
	public void delete(Integer id) throws ServiceException;
}
