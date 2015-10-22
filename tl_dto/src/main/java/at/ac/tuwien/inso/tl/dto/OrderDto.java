package at.ac.tuwien.inso.tl.dto;

import java.util.Date;
import java.util.List;

public class OrderDto
{
	private Integer id;
	private Date orderedAt;
	private MethodOfPayment methodOfPayment;
	private CustomerDto customer;
	private List<OrderItemDto> orderItems;
	private String paymentReference;
	private Integer priceTotal;
	private AdressDto billingAdress;
	private AdressDto deliveryAdress;
	private String orderNumber;
	private Boolean useBonusPoints;
	private ReservationDto reservation;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Date getOrderedAt()
	{
		return orderedAt;
	}

	public void setOrderedAt(Date orderedAt)
	{
		this.orderedAt = orderedAt;
	}

	public MethodOfPayment getMethodOfPayment()
	{
		return methodOfPayment;
	}

	public void setMethodOfPayment(MethodOfPayment methodOfPayment)
	{
		this.methodOfPayment = methodOfPayment;
	}

	public CustomerDto getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerDto customer)
	{
		this.customer = customer;
	}

	public List<OrderItemDto> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems)
	{
		this.orderItems = orderItems;
	}

	public String getPaymentReference()
	{
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference)
	{
		this.paymentReference = paymentReference;
	}

	public Integer getPriceTotal()
	{
		return priceTotal;
	}

	public void setPriceTotal(Integer priceTotal)
	{
		this.priceTotal = priceTotal;
	}

	public AdressDto getBillingAdress()
	{
		return billingAdress;
	}

	public void setBillingAdress(AdressDto billingAdress)
	{
		this.billingAdress = billingAdress;
	}

	public AdressDto getDeliveryAdress()
	{
		return deliveryAdress;
	}

	public void setDeliveryAdress(AdressDto deliveryAdress)
	{
		this.deliveryAdress = deliveryAdress;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderedAt == null) ? 0 : orderedAt.hashCode());
		result = prime * result + ((paymentReference == null) ? 0 : paymentReference.hashCode());
		result = prime * result + ((priceTotal == null) ? 0 : priceTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDto other = (OrderDto) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderedAt == null)
		{
			if (other.orderedAt != null)
				return false;
		} else if (!orderedAt.equals(other.orderedAt))
			return false;
		if (paymentReference == null)
		{
			if (other.paymentReference != null)
				return false;
		} else if (!paymentReference.equals(other.paymentReference))
			return false;
		if (priceTotal == null)
		{
			if (other.priceTotal != null)
				return false;
		} else if (!priceTotal.equals(other.priceTotal))
			return false;
		return true;
	}

	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public Boolean getUseBonusPoints()
	{
		return useBonusPoints;
	}

	public void setUseBonusPoints(Boolean useBonusPoints)
	{
		this.useBonusPoints = useBonusPoints;
	}

	public ReservationDto getReservation()
	{
		return reservation;
	}

	public void setReservation(ReservationDto reservation)
	{
		this.reservation = reservation;
	}

}
