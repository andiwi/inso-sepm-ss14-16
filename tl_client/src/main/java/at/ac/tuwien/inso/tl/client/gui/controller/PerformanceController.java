package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.client.client.ArticleService;
import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.client.CustomerService;
import at.ac.tuwien.inso.tl.client.client.OrderService;
import at.ac.tuwien.inso.tl.client.client.PerformanceService;
import at.ac.tuwien.inso.tl.client.client.ReservationService;
import at.ac.tuwien.inso.tl.client.client.ShowService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.exception.ValidationException;
import at.ac.tuwien.inso.tl.client.gui.pane.SeatsPane;
import at.ac.tuwien.inso.tl.client.gui.pane.ShowPane;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.client.util.Navigator;
import at.ac.tuwien.inso.tl.client.util.ShoppingBasket;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.FieldError;
import at.ac.tuwien.inso.tl.dto.MethodOfPayment;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.OrderItemDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.Sex;
import at.ac.tuwien.inso.tl.dto.ShowDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

@Component
public class PerformanceController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(PerformanceController.class);

	//
	// dependencies
	//

	@Autowired
	private ShowService showService;

	@Autowired
	private PerformanceService performanceService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private AuthService authService;

	@Autowired
	private ArticleService articleService;
	
	//
	// gui elements
	//

	@FXML
	private Button selectedCustomerButton;
	@FXML
	private Button selectedCustomerAnonymButton;
	@FXML
	private Label selectedPerformanceLabel;
	@FXML
	private Label selectedOrderReservationLabel;
	@FXML
	private Label selectedPriceLabel;
	@FXML
	private Label pointsLabel;
	@FXML
	private ComboBox<MethodOfPayment> methodOfPaymentComboBox;

	@FXML
	private VBox ticketsVBox;
	@FXML
	private VBox leftVBox;

	@FXML
	private Button saveButton;
	@FXML
	private Button orderButton;
	@FXML
	private Button reserveButton;
	@FXML
	private Button cancelButton;

	@FXML
	private HBox merchandiseHbox;
	
	@FXML
	private TableView<ArticleDto> articleTableView;
	@FXML
	private TableColumn<ArticleDto, String> articleNameColumn;
	@FXML
	private TableColumn<ArticleDto, String> articlePriceColumn;
	@FXML
	private TableColumn<ArticleDto, String> articleDescriptionColumn;
	@FXML
	private TextField amountTextField;
	@FXML
	private TextField articleSearchTextField;
	@FXML
	private Label customerLabel;
	
	private BundleManager bm;

	private SeatsPane seatsPane;

	//
	//
	//

	private PerformanceDto performance;
	private CustomerDto customer;
	private ReservationDto reservation;
	private OrderDto order;

	private ShoppingBasket shoppingBasket;

	private ObservableList<ShowDto> showComboBoxData = FXCollections.observableArrayList();
	private ObservableList<PerformanceDto> performanceComboBoxData = FXCollections
			.observableArrayList();

	//
	//
	//

	private ObservableList<ArticleDto> articleTableViewData = FXCollections.observableArrayList();

	//
	//
	//

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		this.shoppingBasket = new ShoppingBasket(this);
		this.seatsPane = new SeatsPane(this, this.shoppingBasket);
		this.leftVBox.getChildren().add(this.seatsPane);

		this.onNewShoppingBasketButtonClick();

		this.selectedPerformanceLabel.setCursor(Cursor.HAND);

		this.selectedPerformanceLabel.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0)
			{
				Navigator.getInstance().openPerformanceSelection(PerformanceController.this, arg0);
			}
		});
		
		this.articleTableViewData.clear();

		try
		{
			this.articleTableViewData.addAll(this.articleService.findArticles(""));
		} catch (ServiceException e)
		{
			LOG.error(e);
		}

		this.articleTableView.setItems(this.articleTableViewData);
		articleTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));

		this.articleNameColumn.setCellValueFactory(new PropertyValueFactory<ArticleDto, String>("title"));
		this.articlePriceColumn.setCellValueFactory(new Callback<CellDataFeatures<ArticleDto, String>, ObservableValue<String>>()
			{
				@Override
				public ObservableValue<String> call(CellDataFeatures<ArticleDto, String> p)
				{
					return new SimpleStringProperty(Formatter.currency(p.getValue().getPrice()));
					
				}
			});
		this.articleDescriptionColumn
				.setCellValueFactory(new PropertyValueFactory<ArticleDto, String>("description"));
		
		// search text field
		this.articleSearchTextField.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue)
			{
				PerformanceController.this.onSearchValueChange(newValue);
			}
		});
		
		amountTextField.setText("1");
		//method of payment combo box
		Callback<ListView<MethodOfPayment>, ListCell<MethodOfPayment>> methodOfPaymentComboBoxCallback = new Callback<ListView<MethodOfPayment>, ListCell<MethodOfPayment>>()
		{

			@Override
			public ListCell<MethodOfPayment> call(ListView<MethodOfPayment> arg0)
			{
				return new ListCell<MethodOfPayment>()
				{
					@Override
					protected void updateItem(MethodOfPayment item, boolean empty)
					{
						super.updateItem(item, empty);

						this.setText(item == null ? "" : item.toString());

					}
				};
			}
		};
		this.methodOfPaymentComboBox.getItems().addAll(MethodOfPayment.values());
		this.methodOfPaymentComboBox.setCellFactory(methodOfPaymentComboBoxCallback);
		this.methodOfPaymentComboBox.setButtonCell(methodOfPaymentComboBoxCallback.call(null));
	}
	
	private void onSearchValueChange(String search)
	{
		this.articleTableViewData.clear();
		try
		{
			this.articleTableViewData.addAll(this.articleService.findArticles(search));
		} catch (ServiceException e)
		{
			LOG.error(e);
		}
	}


	@FXML
	private void onSelectCartButtonClicked()
	{
		ArticleDto article = this.articleTableView.getSelectionModel().getSelectedItem();
		LOG.info("onSelectCartButtonClicked");
		try
		{
			int amount=Integer.parseInt(amountTextField.getText());
			if(amount>0)
				article.setAmount(amount);
			else
				throw new NumberFormatException();
			article.setAmount(Integer.parseInt(this.amountTextField.getText())); 
		} catch (NumberFormatException e)
		{
			Dialogs.showErrorDialog((Stage) this.articleTableView.getScene().getWindow(),
					bm.getExceptionBundle().getString("amount_error"));
			return;
		}
		
		LOG.info("Id: "+article.getId()+" "+article.getAmount() +"x : "+article.getTitle()+" in den Warenkorb");
		this.shoppingBasket.addArticle(article);

	}

	public void setCustomer(CustomerDto customer)
	{
		this.customer = customer;
		if (customer == null)
		{
			this.selectedCustomerButton.setText(bm.getBundle().getString("button_searchPoints"));
		} else
		{
			this.selectedCustomerButton.setText(customer.getFirstname() + " "
					+ customer.getLastname() + " (" + customer.getCustomerNumber() + ")");
			this.pointsLabel.setText(Integer.toString(customer.getPoints()));
		}
	}

	/**
	 * 
	 * load performance by id from server. set label, let seatsPane handle
	 * rendering of room plan
	 * 
	 * @param performance
	 */
	public void setPerformance(PerformanceDto performance)
	{

		if (performance == null)
		{
			this.performance = null;
			this.selectedPerformanceLabel.setText(bm.getBundle().getString("label_searchPerformance"));
			return;
		}

		try
		{
			this.performance = this.performanceService.getPerformanceById(performance);
			this.selectedPerformanceLabel.setText(this.performance.getShow().getTitle() + " "
					+ Formatter.dateTimeShort(this.performance.getStartsAt()));
			this.seatsPane.showSeats(this.performance, this.reservation, this.order);
		} catch (ServiceException e)
		{
			LOG.error(e);
		}
	}

	public PerformanceDto getPerformance()
	{
		return this.performance;
	}

	public void setOrder(OrderDto order)
	{
		if (order == null)
		{
			this.order = null;
			return;
		}

		try
		{
			this.order = this.orderService.getOrderById(order);
			this.selectedOrderReservationLabel.setText(bm.getBundle().getString("label_order") +" " + this.order.getOrderNumber()
					+ " "+ bm.getBundle().getString("label_from")+" " + Formatter.dateShort(this.order.getOrderedAt()));
			this.selectedOrderReservationLabel.setVisible(true);
			this.saveButton.setVisible(true);
			this.reserveButton.setVisible(false);
			this.orderButton.setVisible(false);
			this.cancelButton.setVisible(true);

			this.methodOfPaymentComboBox.getSelectionModel().select(order.getMethodOfPayment());
			
			this.seatsPane.clear();
			this.shoppingBasket.clear();
			this.setPerformance(null);
			this.setReservation(null);

			for (OrderItemDto oi : this.order.getOrderItems())
			{
				if (oi.getTicket() != null) {
				this.shoppingBasket.addTicket(oi.getTicket());
				}
				if (oi.getArticle() != null) {
				this.shoppingBasket.addArticle(oi.getArticle());
				}
			}
			if (this.shoppingBasket.getPerformanceList().size() == 1)
			{
				this.setPerformance(this.shoppingBasket.getPerformanceList().get(0));
			}
			this.setCustomer(this.order.getCustomer());
		} catch (ServiceException e)
		{
			LOG.error(e);
		}
	}

	public void setReservation(ReservationDto reservation)
	{
		if (reservation == null)
		{
			this.reservation = null;
			return;
		}

		try
		{
			this.reservation = this.reservationService.getReservationById(reservation);
			this.selectedOrderReservationLabel.setText(bm.getBundle().getString("label_reservation")+" "
					+ this.reservation.getReservationNumber());
			this.selectedOrderReservationLabel.setVisible(true);
			this.saveButton.setVisible(true);
			this.reserveButton.setVisible(false);
			this.orderButton.setVisible(true);
			this.cancelButton.setVisible(true);

			this.seatsPane.clear();
			this.shoppingBasket.clear();
			this.setPerformance(null);
			this.setOrder(null);

			this.shoppingBasket.addAll(this.reservation.getTickets());
			if (this.shoppingBasket.getPerformanceList().size() == 1)
			{
				this.setPerformance(this.shoppingBasket.getPerformanceList().get(0));
			}
			this.setCustomer(reservation.getCustomer());
		} catch (ServiceException e)
		{
			LOG.error(e);
		}
	}
	
	public void removeArticle(ArticleDto article) {
		this.shoppingBasket.removeArticle(article);
	}

	//
	// event handlers
	//

	/**
	 * called by shopping basket when addTicket or removeTicket is called (even
	 * if the ticket is not actually added/removed)
	 */
	public void onShoppingBasketChange()
	{
		this.ticketsVBox.getChildren().clear();

		for (Map.Entry<PerformanceDto, Set<TicketDto>> entry : this.shoppingBasket.entrySet())
		{
			this.ticketsVBox.getChildren()
					.add(new ShowPane(this, entry.getKey(), entry.getValue()));
		}
		
		for (Map.Entry<ArticleDto, Integer> entry : this.shoppingBasket.merchandiseEntrySet())
		{
			this.ticketsVBox.getChildren()
					.add(new ShowPane(this, entry.getKey(), entry.getValue()));
		}

		if (this.ticketsVBox.getChildren().isEmpty())
		{
			
			Label m = new Label(bm.getBundle().getString("label_noArticle"));
			m.setStyle("-fx-padding:10 0;");
			this.ticketsVBox.getChildren().add(m);
		}

		this.selectedPriceLabel.setText(Formatter.currency(this.shoppingBasket.getTotalPrice()));
	}

	/**
	 * called by seat pane when its status changes to isSelected = true
	 */
	public void onTicketSelected(TicketDto ticket)
	{
		this.shoppingBasket.addTicket(ticket);
	}

	/**
	 * called by seat pane when its status changes to isSelected = false
	 */
	public void onTicketDeselected(TicketDto ticket)
	{
		this.shoppingBasket.removeTicket(ticket);
	}

	/**
	 * called when reserve button is clicked
	 */
	
	@FXML
	private void onReserveButtonClick()
	{
		DialogResponse dr = null;
		String text = bm.getBundle().getString("text_attention");
		try
		{
			Navigator.getInstance().setCursor(Cursor.WAIT);
			this.validateForReserve();
			if (this.shoppingBasket.getArticleList().size() != 0)	{
				dr = Dialogs.showConfirmDialog(new Stage(),
						bm.getExceptionBundle().getString("articleReservation_error"),  text,"Ticketline", DialogOptions.YES_NO);
				if(dr==DialogResponse.YES)
				{	
					new Thread(new ReserveTask()).start();
					Navigator.getInstance().setCursor(Cursor.DEFAULT);
				}
			}
			else {
				new Thread(new ReserveTask()).start();
				Navigator.getInstance().setCursor(Cursor.DEFAULT);
			}
			
		} catch (ValidationException e)
		{
			Dialogs.showErrorDialog((Stage) this.leftVBox.getScene().getWindow(), e.toString());
		}
	}

	/**
	 * called when order button is clicked
	 */
	@FXML
	private void onOrderButtonClick()
	{
		try
		{
			this.validateForOrder();
			OrderTask ot = new OrderTask();
			ot.setDialogResponse(this.bonusPointsDialog());
			Navigator.getInstance().setCursor(Cursor.WAIT);
			new Thread(ot).start();
		} catch (ValidationException e)
		{
			Dialogs.showErrorDialog((Stage) this.leftVBox.getScene().getWindow(), e.toString());
		}
	}
	
	protected DialogResponse bonusPointsDialog()
	{		
		if (this.customer.getPoints() >= 100) {
			String text = bm.getBundle().getString("label_normalPrice");
			text = String.format(
					text,
					Formatter.currency(this.shoppingBasket.getTotalPrice()),
					100,
					Formatter.currency((float)(this.shoppingBasket.getTotalPrice() * 0.75)));
			return Dialogs.showConfirmDialog(new Stage(), text,
					String.format(bm.getBundle().getString("text_doBonus"), 100), "Ticketline", DialogOptions.YES_NO);
		}
		
		if (this.customer.getPoints() >= 50) {
			String text = bm.getBundle().getString("label_normalPrice");
			text = String.format(
					text,
					Formatter.currency(this.shoppingBasket.getTotalPrice()),
					50,
					Formatter.currency((float)(this.shoppingBasket.getTotalPrice() * 0.9)));
			return Dialogs.showConfirmDialog(new Stage(), text,
					String.format(bm.getBundle().getString("text_doBonus"), 50), "Ticketline", DialogOptions.YES_NO);
		}
		
		return DialogResponse.NO;
	}

	/**
	 * self-explanatory method names ...
	 */

	@FXML
	private void onSaveButtonClick()
	{
		Navigator.getInstance().setCursor(Cursor.WAIT);
		new Thread(new SaveTask()).start();
	}

	@FXML
	private void onCancelButtonClick()
	{
		Navigator.getInstance().setCursor(Cursor.WAIT);
		new Thread(new CancelTask()).start();
	}

	@FXML
	private void onSelectCustomerButtonClick(ActionEvent event)
	{
		Navigator.getInstance().openCustomerSelection(this, event);
	}

	@FXML
	private void onSelectCustomerAnonymButtonClick()
	{
		CustomerDto c = new CustomerDto();
		c.setId(1);
		try
		{
			setCustomer(customerService.getCustomerById(c));
		} catch (ServiceException e)
		{
			LOG.error(e);
		}
	}

	@FXML
	private void onSelectPerformanceButtonClick(ActionEvent event)
	{
		Navigator.getInstance().openPerformanceSelection(this, event);
	}

	@FXML
	private void onSelectReservationButtonClick(ActionEvent event)
	{
		Navigator.getInstance().openReservationSelection(this, event);
	}

	@FXML
	private void onSelectOrderButtonClick(ActionEvent event)
	{
		Navigator.getInstance().openOrderSelection(this, event);
	}

	@FXML
	private void onNewShoppingBasketButtonClick()
	{
		this.shoppingBasket.clear();
		this.seatsPane.clear();
		this.setCustomer(null);
		this.setPerformance(null);
		this.setOrder(null);
		this.setReservation(null);
		this.methodOfPaymentComboBox.getSelectionModel().clearSelection();
		this.saveButton.setVisible(false);
		this.cancelButton.setVisible(false);
		this.reserveButton.setVisible(true);
		this.orderButton.setVisible(true);
		this.selectedOrderReservationLabel.setText(bm.getBundle().getString("label_newCart"));
		this.selectedOrderReservationLabel.setVisible(true);
	}

	//
	// validation
	//

	private void validateForReserve() throws ValidationException
	{
		List<FieldError> errors = new ArrayList<FieldError>();

		if (this.customer == null)
		{
			errors.add(new FieldError(bm.getBundle().getString("tableView_customer"), bm.getExceptionBundle().getString("noCustomer_error")));
		}

		if (this.shoppingBasket.getTicketList().size() == 0)
		{
			errors.add(new FieldError(bm.getBundle().getString("label_tickets"), bm.getExceptionBundle().getString("noTicket_error")));
		}

		if (!errors.isEmpty())
		{
			throw new ValidationException(
					bm.getExceptionBundle().getString("reservation_error"),
					errors);
		}
	}

	private void validateForOrder() throws ValidationException
	{
		List<FieldError> errors = new ArrayList<FieldError>();

		if (this.customer == null)
		{
			errors.add(new FieldError(bm.getBundle().getString("tableView_customer"), bm.getExceptionBundle().getString("noCustomer_error")));
		}

		if (this.shoppingBasket.getTicketList().size() == 0 && this.shoppingBasket.getArticleList().size() == 0)
		{
			errors.add(new FieldError(bm.getBundle().getString("label_tickets"), bm.getExceptionBundle().getString("noArticle_error")));
		}
		
		if (this.methodOfPaymentComboBox.getSelectionModel().isEmpty())
		{
			errors.add(new FieldError(bm.getBundle().getString("label_methodofpayment"), bm.getBundle().getString("noMethodOfPayment_error")));
		}

		if (!errors.isEmpty())
		{
			throw new ValidationException(
					bm.getExceptionBundle().getString("order_error"),
					errors);
		}
	}

	//
	// tasks - needed to show waiting cursor while waiting for server response when reserving and ordering
	//

	private class ReserveTask extends Task<Void>
	{
		private ReservationDto loaded;

		@Override
		protected Void call() throws Exception
		{
			try
			{
				ReservationDto r = PerformanceController.this.reservationService.reserveTickets(
						PerformanceController.this.shoppingBasket.getTicketList(),
						PerformanceController.this.customer);
				this.loaded = PerformanceController.this.reservationService.getReservationById(r);
			} catch (ServiceException e)
			{
				LOG.error(e.getMessage());
			} finally
			{
				Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						Navigator.getInstance().setCursor(Cursor.DEFAULT);
						Navigator.getInstance().gotoStartpage(
								bm.getBundle().getString("text_reservationSaved") + loaded.getReservationNumber());
					}
				});
			}
			return null;
		}
	}

	private class SaveTask extends Task<Void>
	{
		@Override
		protected Void call() throws Exception
		{
			try
			{
				if (PerformanceController.this.reservation != null)
				{
					PerformanceController.this.reservationService.update(
							PerformanceController.this.reservation,
							PerformanceController.this.shoppingBasket.getTicketList(),
							PerformanceController.this.customer);
				}
				if (PerformanceController.this.order != null)
				{
					PerformanceController.this.orderService.update(
							PerformanceController.this.order,
							PerformanceController.this.shoppingBasket.getTicketList(),
							PerformanceController.this.shoppingBasket.getArticleList(),
							PerformanceController.this.customer,
							PerformanceController.this.methodOfPaymentComboBox.getSelectionModel().getSelectedItem());
				}
			} catch (ServiceException e)
			{
				LOG.error(e);
			} finally
			{
				Platform.runLater(new Runnable()
				{
					@Override
					public void run()
					{
						Navigator.getInstance().setCursor(Cursor.DEFAULT);

						if (PerformanceController.this.reservation != null)
						{
						Navigator.getInstance().gotoStartpage(
								BundleManager.getBundle().getString("text_reservationSaved") + PerformanceController.this.reservation.getReservationNumber());
						}
						if (PerformanceController.this.order != null)
						{
						Navigator.getInstance().gotoStartpage(
								BundleManager.getBundle().getString("text_orderSaved") + PerformanceController.this.order.getOrderNumber());
						}
					}
				});
			}
			return null;
		}
	}

	private class CancelTask extends Task<Void>
	{
		@Override
		protected Void call() throws Exception
		{
			if (PerformanceController.this.reservation != null)
			{
				try
				{
					PerformanceController.this.reservationService.cancel(PerformanceController.this.reservation);
				} catch (ServiceException e)
				{
					LOG.error(e);
				} finally
				{
					Platform.runLater(new Runnable()
					{
						@Override
						public void run()
						{
							Navigator.getInstance().setCursor(Cursor.DEFAULT);
							Navigator.getInstance().gotoStartpage(
									bm.getBundle().getString("text_theReservation") + PerformanceController.this.reservation.getReservationNumber()
									+ bm.getBundle().getString("text_canceled"));
						}
					});
				}
			}
			
			if (PerformanceController.this.order != null)
			{
				try
				{
					PerformanceController.this.orderService.cancel(PerformanceController.this.order);
				} catch (ServiceException e)
				{
					LOG.error(e);
				} finally
				{
					Platform.runLater(new Runnable()
					{
						@Override
						public void run()
						{
							Navigator.getInstance().setCursor(Cursor.DEFAULT);
							Navigator.getInstance().gotoStartpage(
									bm.getBundle().getString("text_theOrder") + PerformanceController.this.order.getOrderNumber() + bm.getBundle().getString("text_canceled"));
						}
					});
				}
			}
			return null;
		}
	}
	
	private class OrderTask extends Task<Void>
	{
		private OrderDto loaded;
		private DialogResponse dr;
		
		public void setDialogResponse(DialogResponse dr)
		{
			this.dr = dr;
		}

		@Override
		protected Void call() throws Exception
		{
			if (PerformanceController.this.reservation == null)
			{
				try
				{
					OrderDto o = PerformanceController.this.orderService.orderTicketsAndArticles(
							PerformanceController.this.shoppingBasket.getTicketList(),
							PerformanceController.this.shoppingBasket.getArticleList(),
							PerformanceController.this.customer,
							PerformanceController.this.methodOfPaymentComboBox.getSelectionModel().getSelectedItem(),
							this.dr == DialogResponse.YES);
					this.loaded = PerformanceController.this.orderService.getOrderById(o);
				} catch (ServiceException e)
				{
					LOG.error(e.getMessage());
				} finally
				{
					Platform.runLater(new Runnable()
					{
						@Override
						public void run()
						{
							Navigator.getInstance().setCursor(Cursor.DEFAULT);
							Navigator.getInstance().gotoStartpage(
									bm.getBundle().getString("text_ordered") + loaded.getOrderNumber());
						}
					});
				}
			} else
			{
				try
				{
					OrderDto o = PerformanceController.this.orderService.orderReservationAndArticles(
							PerformanceController.this.shoppingBasket.getTicketList(),
							PerformanceController.this.shoppingBasket.getArticleList(),
							PerformanceController.this.reservation,
							PerformanceController.this.customer,
							PerformanceController.this.methodOfPaymentComboBox.getSelectionModel().getSelectedItem(),
							this.dr == DialogResponse.YES);
					this.loaded = PerformanceController.this.orderService.getOrderById(o);
				} catch (ServiceException e)
				{
					LOG.error(e.getMessage());
				} finally
				{
					Platform.runLater(new Runnable()
					{	
						@Override
						public void run()
						{
							Navigator.getInstance().setCursor(Cursor.DEFAULT);
							Navigator.getInstance().gotoStartpage(
									bm.getBundle().getString("text_reserved") + loaded.getOrderNumber());
						}
					});
				}
			}
			return null;
		}
	}
}
