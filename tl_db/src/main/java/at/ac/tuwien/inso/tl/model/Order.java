package at.ac.tuwien.inso.tl.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Purchase")
public class Order implements Serializable
{
	private static final long serialVersionUID = 4871431571987100831L;

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderedAt;

	@Enumerated(value = EnumType.STRING)
	private MethodOfPayment methodOfPayment;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderItem> orderItems;

	@Column(nullable = false)
	private String orderNumber;

	@Column
	private String paymentReference;

	@Column
	private Integer priceTotal;

	@Column
	private String billingStreet;

	@Column
	private String billingPostcode;

	@Column
	private String billingCity;

	@Column
	private String billingCountry;

	@Column
	private String deliveryStreet;

	@Column
	private String deliveryPostcode;

	@Column
	private String deliveryCity;

	@Column
	private String deliveryCountry;

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

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
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

	public String getBillingStreet()
	{
		return billingStreet;
	}

	public void setBillingStreet(String billingStreet)
	{
		this.billingStreet = billingStreet;
	}

	public String getBillingPostcode()
	{
		return billingPostcode;
	}

	public void setBillingPostcode(String billingPostcode)
	{
		this.billingPostcode = billingPostcode;
	}

	public String getBillingCity()
	{
		return billingCity;
	}

	public void setBillingCity(String billingCity)
	{
		this.billingCity = billingCity;
	}

	public String getBillingCountry()
	{
		return billingCountry;
	}

	public void setBillingCountry(String billingCountry)
	{
		this.billingCountry = billingCountry;
	}

	public String getDeliveryStreet()
	{
		return deliveryStreet;
	}

	public void setDeliveryStreet(String deliveryStreet)
	{
		this.deliveryStreet = deliveryStreet;
	}

	public String getDeliveryPostcode()
	{
		return deliveryPostcode;
	}

	public void setDeliveryPostcode(String deliveryPostcode)
	{
		this.deliveryPostcode = deliveryPostcode;
	}

	public String getDeliveryCity()
	{
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity)
	{
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryCountry()
	{
		return deliveryCountry;
	}

	public void setDeliveryCountry(String deliveryCountry)
	{
		this.deliveryCountry = deliveryCountry;
	}

	public List<OrderItem> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}

	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}
}
