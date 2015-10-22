package at.ac.tuwien.inso.tl.server.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.ShowDao;
import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.model.Show;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.ShowService;

@Service
@Transactional
public class ShowServiceImpl implements ShowService
{
	@Autowired
	private ShowDao showDao;

	@Override
	public Show getShow(Integer id) throws ServiceException
	{
		try
		{
			return showDao.findByIdWithPerformances(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Show> findShows(String search) throws ServiceException
	{
		try
		{
			if (search == null || search.isEmpty())
			{
				return showDao.findAll();
			} else
			{
				return showDao.findShowsByTitleShowTypeOrDescription(search.toUpperCase());
			}
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Show> getShowsByArtistId(Integer id) throws ServiceException
	{
		try
		{
			return this.showDao.findShowsByArtistId(id);
		} catch (Exception e)
		{
			throw e;
			//throw new ServiceException(e);
		}
	}
}
