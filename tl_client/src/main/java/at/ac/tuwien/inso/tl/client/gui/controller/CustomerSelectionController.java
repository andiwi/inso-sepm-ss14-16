package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.client.CustomerService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.Sex;

@Component
public class CustomerSelectionController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(CustomersController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AuthService authService;

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

	private ObservableList<CustomerDto> customersTableViewData = FXCollections
			.observableArrayList();

	private PerformanceController parentController;

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("CustomersController initialize");

		// table view
		try
		{
			this.customersTableViewData.clear();
			this.customersTableViewData.addAll(this.customerService.findCustomers(""));
		} catch (ServiceException e)
		{
			// TODO
			throw (new RuntimeException(e));
		}

		this.customersTableView.setItems(this.customersTableViewData);
		customersTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));

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

		// search text field
		this.searchTextField.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue)
			{
				CustomerSelectionController.this.onSearchValueChange(newValue);
			}
		});

		// TODO: Bug: event fires if clicking anywhere on tableview
		this.customersTableView.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				CustomerDto selected = CustomerSelectionController.this.customersTableView
						.getSelectionModel().getSelectedItem();
				if (event.getClickCount() > 1 && selected != null)
				{
					CustomerSelectionController.this.onCustomersTableViewDoubleClick(selected);
				}
			}
		});
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

	private void onCustomersTableViewDoubleClick(CustomerDto customer)
	{
		this.parentController.setCustomer(customer);
		Stage stage = (Stage) this.customersTableView.getScene().getWindow();
		stage.close();
	}

	public void setParentController(PerformanceController c)
	{
		this.parentController = c;
	}
}