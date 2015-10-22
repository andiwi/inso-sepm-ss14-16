package at.ac.tuwien.inso.tl.server.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.PerformanceDao;
import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.PerformanceService;

@Service
@Transactional
public class PerformanceServiceImpl implements PerformanceService
{
	@Autowired
	private PerformanceDao performanceDao;

	@Override
	public List<Performance> getPerformancesByLocationId(Integer id) throws ServiceException
	{
		try
		{
			return this.performanceDao.findByLocationId(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public Performance getPerformance(Integer id) throws ServiceException
	{
		try
		{
			return this.performanceDao.findById(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Performance> getPerformancesByArtistId(Integer id) throws ServiceException
	{
		try
		{
			return this.performanceDao.findByArtistId(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Performance> getPerformancesByShowId(Integer id) throws ServiceException
	{
		try
		{
			return this.performanceDao.findByShowId(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

}