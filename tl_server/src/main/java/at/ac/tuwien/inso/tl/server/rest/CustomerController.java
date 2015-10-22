package at.ac.tuwien.inso.tl.server.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.MessageDto;
import at.ac.tuwien.inso.tl.dto.MessageType;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.CustomerService;
import at.ac.tuwien.inso.tl.server.util.DtoToEntity;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController
{
	private static final Logger LOG = Logger.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<CustomerDto> findCustomers(
			@RequestParam(value = "search", required = false) String search)
			throws ServiceException
	{
		if (search != null)
		{
			try
			{
				search = URLDecoder.decode(search, "UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				LOG.error(e);
			}
		}
		return EntityToDto.convertCustomers(this.customerService.findCustomers(search));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public CustomerDto getCustomerById(@PathVariable("id") Integer id) throws ServiceException
	{
		LOG.info("getCustomer() called");
		return EntityToDto.convertWithReservationsAndOrders(customerService.getCustomer(id));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public MessageDto createCustomer(@Valid @RequestBody CustomerDto customer)
			throws ServiceException
	{
		LOG.info("createCustomer() called");

		Integer id = this.customerService.save(DtoToEntity.convert(customer)).getId();

		MessageDto msg = new MessageDto();
		msg.setType(MessageType.SUCCESS);
		msg.setText(id.toString());

		return msg;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public MessageDto updateCustomer(@PathVariable("id") Integer id,
			@Valid @RequestBody CustomerDto customer) throws ServiceException
	{
		LOG.info("updateCustomer() called");

		Integer resultId = this.customerService.save(DtoToEntity.convert(customer)).getId();

		MessageDto msg = new MessageDto();
		msg.setType(MessageType.SUCCESS);
		msg.setText(resultId.toString());

		return msg;
	}

}
