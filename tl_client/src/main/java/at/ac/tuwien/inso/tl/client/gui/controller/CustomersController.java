package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.client.CustomerService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.exception.ValidationException;
import at.ac.tuwien.inso.tl.client.gui.pane.PerformancesPane;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.client.util.Navigator;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.FieldError;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.OrderItemDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.Sex;

@Component
public class CustomersController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(CustomersController.class);

	@Autowired
	private CustomerService customerService;

	@FXML
	private TextField searchTextField;

	@FXML
	private TableView<CustomerDto> customersTableView;
	@FXML
	private TableColumn<CustomerDto, String> customerNumberColumn;
	@FXML
	private TableColumn<CustomerDto, String> firstnameColumn;
	@FXML
	private TableColumn<CustomerDto, String> lastnameColumn;
	@FXML
	private TableColumn<CustomerDto, String> sexColumn;
	@FXML
	private TableColumn<CustomerDto, String> dateOfBirthColumn;
	@FXML
	private TableColumn<CustomerDto, String> emailColumn;
	@FXML
	private TableColumn<CustomerDto, String> telephoneColumn;
	@FXML
	private TableColumn<CustomerDto, Integer> pointsColumn;
	
	@FXML
	private VBox reservationsAndOrdersVBox;
	
	@FXML
	private TableView<ReservationDto> reservationsTableView;
	@FXML
	private TableColumn<ReservationDto, String> reservationNumberColumn;

	@FXML
	private TableView<OrderDto> ordersTableView;
	@FXML
	private TableColumn<OrderDto, String> orderNumberColumn;
	@FXML
	private TableColumn<OrderDto, String> orderDateColumn;
	@FXML
	private TableColumn<OrderDto, String> orderPriceColumn;

	@FXML
	private GridPane formGridPane;
	@FXML
	private Label customerNumberLabel;
	@FXML
	private ComboBox<Sex> sexComboBox;
	@FXML
	private TextField firstnameTextField;
	@FXML
	private TextField lastnameTextField;
	@FXML
	private TextField dateofbirthTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField telephoneTextField;
	@FXML
	private Label pointsTextField;

	@FXML
	private Button saveButton;
	@FXML
	private Hyperlink cancelHyperlink;

	private ObservableList<CustomerDto> customersTableViewData = FXCollections
			.observableArrayList();

	private CustomerDto displayedCustomer;
	
	private BundleManager bm;

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("CustomersController initialize");

		this.hideForm();

		//main table view data
		try
		{
			this.customersTableViewData.clear();
			this.customersTableViewData.addAll(this.customerService.findCustomers(""));
		} catch (ServiceException e)
		{
			LOG.error(e);
		}
		this.customersTableView.setItems(this.customersTableViewData);
		customersTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));
		
		//main table view columns
		this.customersTableView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<CustomerDto>()
				{
					@Override
					public void changed(ObservableValue<? extends CustomerDto> observable,
							CustomerDto oldValue, CustomerDto newValue)
					{
						CustomersController.this.onTableViewSelectionChange(newValue);
					}
				});

		this.customerNumberColumn
				.setCellValueFactory(new PropertyValueFactory<CustomerDto, String>("customerNumber"));
		this.firstnameColumn.setCellValueFactory(new PropertyValueFactory<CustomerDto, String>(
				"firstname"));
		this.lastnameColumn.setCellValueFactory(new PropertyValueFactory<CustomerDto, String>(
				"lastname"));
		this.sexColumn
				.setCellValueFactory(new Callback<CellDataFeatures<CustomerDto, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(CellDataFeatures<CustomerDto, String> p)
					{
						String s = "";
						if (p.getValue().getSex() == Sex.Herr)
						{
							s = "Herr";
						}
						if (p.getValue().getSex() == Sex.Frau)
						{
							s = "Frau";
						}
						return new SimpleStringProperty(s);
					}
				});
		this.dateOfBirthColumn
				.setCellValueFactory(new Callback<CellDataFeatures<CustomerDto, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(CellDataFeatures<CustomerDto, String> p)
					{
						Date d = p.getValue().getDateOfBirth();
						return new SimpleStringProperty(Formatter.dateShort(d));
					}
				});
		this.emailColumn
				.setCellValueFactory(new PropertyValueFactory<CustomerDto, String>("email"));
		this.telephoneColumn.setCellValueFactory(new PropertyValueFactory<CustomerDto, String>(
				"telephone"));
		this.pointsColumn.setCellValueFactory(new PropertyValueFactory<CustomerDto, Integer>(
				"points"));
		
		//order table view click event
		this.ordersTableView.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				OrderDto selected = CustomersController.this.ordersTableView
						.getSelectionModel().getSelectedItem();
				if (event.getClickCount() > 1 && selected != null)
				{
					Navigator.getInstance().gotoOrder(selected);
				}
			}
		});

		//order table view click columns
		this.orderNumberColumn.setCellValueFactory(new PropertyValueFactory<OrderDto, String>("orderNumber"));
		this.orderDateColumn
			.setCellValueFactory(new Callback<CellDataFeatures<OrderDto, String>, ObservableValue<String>>()
			{
				@Override
				public ObservableValue<String> call(CellDataFeatures<OrderDto, String> p)
				{
					Date d = p.getValue().getOrderedAt();
					return new SimpleStringProperty(Formatter.dateShort(d));
				}
			});
		this.orderPriceColumn
			.setCellValueFactory(new Callback<CellDataFeatures<OrderDto, String>, ObservableValue<String>>()
			{
				@Override
				public ObservableValue<String> call(CellDataFeatures<OrderDto, String> p)
				{
					return new SimpleStringProperty(Formatter.currency(p.getValue().getPriceTotal()));
				}
			});

		//reservation table view click event
		this.reservationsTableView.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				ReservationDto selected = CustomersController.this.reservationsTableView
						.getSelectionModel().getSelectedItem();
				if (event.getClickCount() > 1 && selected != null)
				{
					Navigator.getInstance().gotoReservation(selected);
				}
			}
		});
		
		//reservation table view click columns
		this.reservationNumberColumn.setCellValueFactory(new PropertyValueFactory<ReservationDto, String>("reservationNumber"));

		// search text field event
		this.searchTextField.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue)
			{
				CustomersController.this.onSearchValueChange(newValue);
			}
		});
		
		//sex combo box
		Callback<ListView<Sex>, ListCell<Sex>> sexComboBoxCallback = new Callback<ListView<Sex>, ListCell<Sex>>()
		{

			@Override
			public ListCell<Sex> call(ListView<Sex> arg0)
			{
				return new ListCell<Sex>()
				{
					@Override
					protected void updateItem(Sex item, boolean empty)
					{
						super.updateItem(item, empty);

						this.setText(item == null ? "" : (item == Sex.Herr ? "Herr" : "Frau"));

					}
				};
			}
		};
		this.sexComboBox.getItems().addAll(Sex.values());
		this.sexComboBox.setCellFactory(sexComboBoxCallback);
		this.sexComboBox.setButtonCell(sexComboBoxCallback.call(null));
	}

	private void onSearchValueChange(String search)
	{
		this.customersTableViewData.clear();
		try
		{
			this.customersTableViewData.addAll(this.customerService.findCustomers(search));
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void onTableViewSelectionChange(CustomerDto newValue)
	{
		this.hideForm();

		if (newValue == null)
		{
			return;
		}

		try
		{
			this.showForm(this.customerService.getCustomerById(newValue));
		} catch (ServiceException e)
		{
			// TODO
		}
	}

	@FXML
	private void onNewButtonClick()
	{
		this.hideForm();
		this.formGridPane.setVisible(true);
		this.saveButton.setVisible(true);
		this.cancelHyperlink.setVisible(true);
	}

	private void onCreateButtonClick()
	{

		try
		{
			this.validateForm();
		} catch (ValidationException e)
		{
			Dialogs.showErrorDialog((Stage) this.customersTableView.getScene().getWindow(),
					e.toString());
			return;
		}

		CustomerDto customer = new CustomerDto();

		customer.setFirstname(this.firstnameTextField.getText());
		customer.setLastname(this.lastnameTextField.getText());
		customer.setSex(this.sexComboBox.getValue());
		if (!this.dateofbirthTextField.getText().isEmpty())
		{
			SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
			try
			{
				customer.setDateOfBirth(parser.parse(this.dateofbirthTextField.getText()));
			} catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		customer.setEmail(this.emailTextField.getText());
		customer.setTelephone(formatPhoneNum(this.telephoneTextField.getText()));

		try
		{
			this.customerService.createCustomer(customer);
			this.onSearchValueChange(this.searchTextField.getText());
		} catch (ServiceException e)
		{
			// TODO
		}

		this.hideForm();
		
		
	}

	private void onUpdateButtonClick()
	{

		try
		{
			this.validateForm();
		} catch (ValidationException e)
		{
			Dialogs.showErrorDialog((Stage) this.customersTableView.getScene().getWindow(),
					e.toString());
			return;
		}

		CustomerDto customer = new CustomerDto();

		customer.setId(this.displayedCustomer.getId());
		customer.setCustomerNumber(this.customerNumberLabel.getText());
		customer.setFirstname(this.firstnameTextField.getText());
		customer.setLastname(this.lastnameTextField.getText());
		customer.setSex(this.sexComboBox.getValue());
		if (!this.dateofbirthTextField.getText().isEmpty())
		{
			SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");
			try
			{
				customer.setDateOfBirth(parser.parse(this.dateofbirthTextField.getText()));
			} catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		customer.setEmail(this.emailTextField.getText());
		customer.setTelephone(formatPhoneNum(this.telephoneTextField.getText()));

		try
		{
			this.customerService.updateCustomer(customer);
			this.onSearchValueChange(this.searchTextField.getText());
		} catch (ServiceException e)
		{
			// TODO
		}

		this.hideForm();

	}

	private String formatPhoneNum(String phoneNum)
	{

		phoneNum = phoneNum.replace(" ", "");

		if (phoneNum.substring(0, 2).equals("00"))
		{

			phoneNum = "+" + phoneNum.substring(2);
			return formatter(phoneNum);

		} else if (phoneNum.substring(0, 2).equals("06") || phoneNum.substring(0, 2).equals("01"))
		{

			phoneNum = "+43" + phoneNum.substring(1);
			return formatter(phoneNum);

		} else if (phoneNum.substring(0, 2).equals("43"))
		{

			phoneNum = "+" + phoneNum;
			return formatter(phoneNum);

		} else
		{

			phoneNum = "+43" + phoneNum.substring(1);
			return formatter(phoneNum);

		}
	}

	private String formatter(String phoneNum)
	{

		phoneNum = phoneNum.substring(0, 3) + " " + phoneNum.substring(3, 6) + " "
				+ phoneNum.substring(6, 10) + " " + phoneNum.substring(10);
		LOG.info(phoneNum);

		return phoneNum;
	}

	@FXML
	private void onSaveButtonClick()
	{
		if (this.displayedCustomer == null)
		{
			this.onCreateButtonClick();
		} else
		{
			this.onUpdateButtonClick();
		}
	}

	@FXML
	private void onCancelButtonClick()
	{
		this.hideForm();
	}

	private void showForm(CustomerDto customer)
	{
		this.displayedCustomer = customer;

		this.customerNumberLabel.setText(customer.getCustomerNumber());
		this.sexComboBox.setValue(customer.getSex());
		this.firstnameTextField.setText(customer.getFirstname());
		this.lastnameTextField.setText(customer.getLastname());
		this.dateofbirthTextField.setText(Formatter.dateShort(customer.getDateOfBirth()));
		this.emailTextField.setText(customer.getEmail());
		this.telephoneTextField.setText("00"
				+ customer.getTelephone().replace(" ", "").substring(1));
		this.pointsTextField.setText(Integer.toString(customer.getPoints()));
		this.saveButton.setVisible(true);
		this.cancelHyperlink.setVisible(true);
		
		this.reservationsAndOrdersVBox.setVisible(true);
		this.reservationsTableView.getItems().addAll(customer.getReservations());
		this.ordersTableView.getItems().addAll(customer.getOrders());

		this.formGridPane.setVisible(true);
	}

	private void hideForm()
	{
		this.displayedCustomer = null;

		this.formGridPane.setVisible(false);

		this.customerNumberLabel.setText("");
		this.sexComboBox.getSelectionModel().clearSelection();
		this.firstnameTextField.setText("");
		this.lastnameTextField.setText("");
		this.dateofbirthTextField.setText("");
		this.emailTextField.setText("");
		this.telephoneTextField.setText("");
		this.pointsTextField.setText("");

		this.saveButton.setVisible(false);
		this.cancelHyperlink.setVisible(false);

		this.reservationsAndOrdersVBox.setVisible(false);
		this.ordersTableView.getItems().clear();
		this.reservationsTableView.getItems().clear();

		this.customersTableView.getSelectionModel().clearSelection();
	}

	private void validateForm() throws ValidationException
	{
		List<FieldError> errors = new ArrayList<FieldError>();

		if (this.firstnameTextField.getText().isEmpty())
		{
			errors.add(new FieldError("Vorname", bm.getExceptionBundle().getString("vorname_error")));
		}
		if (this.lastnameTextField.getText().isEmpty())
		{
			errors.add(new FieldError("Nachname", bm.getExceptionBundle().getString("nachname_error")));
		}
		if (this.sexComboBox.getValue() == null)
		{
			errors.add(new FieldError("Geschlecht", bm.getExceptionBundle().getString("geschlecht_error")));
		}

		if (this.dateofbirthTextField.getText().isEmpty())
		{

				errors.add(new FieldError("Geburtsdatum",
						bm.getExceptionBundle().getString("birthday_error")));
		}

		if (this.emailTextField.getText().isEmpty())
		{
			if (!this.emailTextField.getText().contains("@"))
			{
				errors.add(new FieldError("Email",
						bm.getExceptionBundle().getString("email_error")));
			}
		}

		if (this.telephoneTextField.getText().isEmpty())
		{
			if (!this.telephoneTextField.getText().matches("[0-9 ]+"))
			{
				errors.add(new FieldError("Telefon",
						bm.getExceptionBundle().getString("telephone_error")));
			}
		}

		if (!errors.isEmpty())
		{
			throw new ValidationException(
					bm.getExceptionBundle().getString("customer_error"),
					errors);
		}
	}
}