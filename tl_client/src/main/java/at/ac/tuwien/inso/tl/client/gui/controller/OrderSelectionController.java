package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.client.OrderService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.OrderDto;

@Component
public class OrderSelectionController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(OrderSelectionController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private AuthService authService;

	@FXML
	private TextField searchTextField;

	@FXML
	private TableView<OrderDto> ordersTableView;
	@FXML
	private TableColumn<OrderDto, String> orderNumberColumn;
	@FXML
	private TableColumn<OrderDto, String> orderedAtColumn;
	@FXML
	private TableColumn<OrderDto, String> customerColumn;

	private ObservableList<OrderDto> ordersTableViewData = FXCollections.observableArrayList();

	private PerformanceController parentController;

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("CustomersController initialize");

		// table view
		try
		{

			this.ordersTableViewData.clear();
			List<OrderDto> os = this.orderService.findOrders("");
			LOG.info(os);
			this.ordersTableViewData.addAll(os);
		} catch (ServiceException e)
		{
			// TODO
			throw (new RuntimeException(e));
		}

		this.ordersTableView.setItems(this.ordersTableViewData);
		ordersTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));

		// TODO: orderNumber, nicht id
		this.orderNumberColumn.setCellValueFactory(new PropertyValueFactory<OrderDto, String>(
				"orderNumber"));
		this.orderedAtColumn
				.setCellValueFactory(new Callback<CellDataFeatures<OrderDto, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(CellDataFeatures<OrderDto, String> p)
					{
						return new SimpleStringProperty(Formatter.dateShort(p.getValue()
								.getOrderedAt()));
					}
				});
		this.customerColumn
				.setCellValueFactory(new Callback<CellDataFeatures<OrderDto, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(CellDataFeatures<OrderDto, String> p)
					{
						CustomerDto c = p.getValue().getCustomer();
						return new SimpleStringProperty(c.getFirstname() + " " + c.getLastname());
					}
				});

		// search text field
		this.searchTextField.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue)
			{
				OrderSelectionController.this.onSearchValueChange(newValue);
			}
		});

		// TODO: Bug: event fires if clicking anywhere on tableview
		this.ordersTableView.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				OrderDto selected = OrderSelectionController.this.ordersTableView
						.getSelectionModel().getSelectedItem();
				if (event.getClickCount() > 1 && selected != null)
				{
					OrderSelectionController.this.onOrdersTableViewDoubleClick(selected);
				}
			}
		});
	}

	private void onSearchValueChange(String search)
	{
		this.ordersTableViewData.clear();
		try
		{
			this.ordersTableViewData.addAll(this.orderService.findOrders(search));
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void onOrdersTableViewDoubleClick(OrderDto order)
	{
		this.parentController.setOrder(order);
		Stage stage = (Stage) this.ordersTableView.getScene().getWindow();
		stage.close();
	}

	public void setParentController(PerformanceController c)
	{
		this.parentController = c;
	}
}
