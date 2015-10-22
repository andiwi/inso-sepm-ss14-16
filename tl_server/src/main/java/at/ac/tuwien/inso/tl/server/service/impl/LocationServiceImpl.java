package at.ac.tuwien.inso.tl.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.LocationDao;
import at.ac.tuwien.inso.tl.model.Location;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService
{

	@Autowired
	private LocationDao locationDao;

	@Override
	public Location getLocation(Integer id) throws ServiceException
	{
		try
		{
			return this.locationDao.findOne(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Location> findLocations(String search) throws ServiceException
	{
		try
		{
			if (search == null || search.isEmpty())
			{
				return this.locationDao.findAll();
			} else
			{
				return this.locationDao.findLocationLike(search.toUpperCase());
			}
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	public void setLocationDao(LocationDao locationDao)
	{
		this.locationDao = locationDao;
	}

}
