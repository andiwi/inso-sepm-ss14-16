package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
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
import at.ac.tuwien.inso.tl.client.client.ReservationService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;

@Component
public class ReservationSelectionController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(ReservationSelectionController.class);

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private AuthService authService;

	@FXML
	private TextField searchTextField;

	@FXML
	private TableView<ReservationDto> reservationsTableView;
	@FXML
	private TableColumn<ReservationDto, String> reservationNumberColumn;
	@FXML
	private TableColumn<ReservationDto, String> customerColumn;

	private ObservableList<ReservationDto> reservationsTableViewData = FXCollections
			.observableArrayList();

	private PerformanceController parentController;

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("CustomersController initialize");

		// table view
		try
		{
			this.reservationsTableViewData.clear();
			this.reservationsTableViewData.addAll(this.reservationService.findReservations(""));
		} catch (ServiceException e)
		{
			// TODO
			throw (new RuntimeException(e));
		}

		this.reservationsTableView.setItems(this.reservationsTableViewData);
		reservationsTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));

		this.reservationNumberColumn
				.setCellValueFactory(new PropertyValueFactory<ReservationDto, String>(
						"reservationNumber"));
		this.customerColumn
				.setCellValueFactory(new Callback<CellDataFeatures<ReservationDto, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(CellDataFeatures<ReservationDto, String> p)
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
				ReservationSelectionController.this.onSearchValueChange(newValue);
			}
		});

		// TODO: Bug: event fires if clicking anywhere on tableview
		this.reservationsTableView.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				ReservationDto selected = ReservationSelectionController.this.reservationsTableView
						.getSelectionModel().getSelectedItem();
				if (event.getClickCount() > 1 && selected != null)
				{
					ReservationSelectionController.this
							.onReservationsTableViewDoubleClick(selected);
				}
			}
		});
	}

	private void onSearchValueChange(String search)
	{
		this.reservationsTableViewData.clear();
		try
		{
			this.reservationsTableViewData.addAll(this.reservationService.findReservations(search));
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void onReservationsTableViewDoubleClick(ReservationDto reservation)
	{
		this.parentController.setReservation(reservation);
		Stage stage = (Stage) this.reservationsTableView.getScene().getWindow();
		stage.close();
	}

	public void setParentController(PerformanceController c)
	{
		this.parentController = c;
	}
}