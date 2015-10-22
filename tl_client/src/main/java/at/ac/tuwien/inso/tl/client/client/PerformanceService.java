package at.ac.tuwien.inso.tl.client.client;

import java.util.List;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;

public interface PerformanceService
{

	public List<PerformanceDto> findPerformances(String search) throws ServiceException;

	public PerformanceDto getPerformanceById(PerformanceDto performance) throws ServiceException;

}
