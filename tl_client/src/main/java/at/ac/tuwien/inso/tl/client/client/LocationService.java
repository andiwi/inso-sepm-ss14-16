package at.ac.tuwien.inso.tl.client.client;

import java.util.List;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.LocationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;

public interface LocationService
{

	public List<LocationDto> findLocations(String search) throws ServiceException;

	public LocationDto getLocationById(LocationDto location) throws ServiceException;

	public List<PerformanceDto> getPerformancesByLocationId(LocationDto location)
			throws ServiceException;
}
