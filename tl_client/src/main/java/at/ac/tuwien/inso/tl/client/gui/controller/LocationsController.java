package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.client.LocationService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.gui.pane.PerformancesPane;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.client.util.Navigator;
import at.ac.tuwien.inso.tl.dto.LocationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;

@Component
public class LocationsController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(LocationsController.class);

	@Autowired
	private LocationService locationService;

	@Autowired
	private AuthService authService;

	@FXML
	private TextField searchTextField;
	@FXML
	private TableView<LocationDto> locationsTableView;
	@FXML
	private TableColumn<LocationDto, String> titleColumn;
	@FXML
	private TableColumn<LocationDto, String> streetColumn;
	@FXML
	private TableColumn<LocationDto, String> postcodeColumn;
	@FXML
	private TableColumn<LocationDto, String> cityColumn;
	@FXML
	private TableColumn<LocationDto, String> countryColumn;
	@FXML
	private VBox detailsVBox;

	@FXML
	private Label titleLabel;
	@FXML
	private Label descriptionLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postcodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label countryLabel;

	private PerformancesPane performancesPane;

	private ObservableList<LocationDto> locationsTableViewData = FXCollections
			.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		LOG.info("LocationsController initialize");

		this.performancesPane = new PerformancesPane();
		this.detailsVBox.getChildren().add(this.performancesPane);

		this.hideDataGridPane();

		// shows table view
		try
		{

			this.locationsTableViewData.clear();
			this.locationsTableViewData.addAll(this.locationService.findLocations(""));
		} catch (ServiceException e)
		{
			// TODO
			throw (new RuntimeException(e));
		}

		this.locationsTableView.setItems(this.locationsTableViewData);
		locationsTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));
		this.locationsTableView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<LocationDto>()
				{
					@Override
					public void changed(ObservableValue<? extends LocationDto> observable,
							LocationDto oldValue, LocationDto newValue)
					{
						LocationsController.this.onLocationsTableViewSelectionChange(newValue);
					}
				});

		this.titleColumn
				.setCellValueFactory(new PropertyValueFactory<LocationDto, String>("title"));
		this.countryColumn.setCellValueFactory(new PropertyValueFactory<LocationDto, String>(
				"country"));
		this.streetColumn.setCellValueFactory(new PropertyValueFactory<LocationDto, String>(
				"street"));
		this.postcodeColumn.setCellValueFactory(new PropertyValueFactory<LocationDto, String>(
				"postcode"));
		this.cityColumn.setCellValueFactory(new PropertyValueFactory<LocationDto, String>("city"));

		// search text field
		this.searchTextField.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue)
			{
				LocationsController.this.onSearchValueChange(newValue);
			}
		});
	}

	private void onSearchValueChange(String search)
	{
		this.locationsTableViewData.clear();
		try
		{
			this.locationsTableViewData.addAll(this.locationService.findLocations(search));
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void onLocationsTableViewSelectionChange(LocationDto newValue)
	{
		this.hideDataGridPane();

		if (newValue == null)
		{
			return;
		}

		try
		{
			this.showDataGridPane(this.locationService.getLocationById(newValue));
		} catch (ServiceException e)
		{
			// TODO
		}
	}

	private void showDataGridPane(LocationDto location)
	{
		this.titleLabel.setText(location.getTitle());
		this.descriptionLabel.setText(location.getDescription());
		this.streetLabel.setText(location.getStreet());
		this.postcodeLabel.setText(location.getPostcode());
		this.cityLabel.setText(location.getCity());
		this.countryLabel.setText(location.getCountry());
		this.detailsVBox.setVisible(true);

		try
		{
			this.performancesPane.setPerformances(this.locationService
					.getPerformancesByLocationId(location));
		} catch (ServiceException e)
		{
			// TODO
			throw (new RuntimeException(e));
		}
	}

	private void hideDataGridPane()
	{
		this.detailsVBox.setVisible(false);
		this.titleLabel.setText("");
		this.descriptionLabel.setText("");
		this.streetLabel.setText("");
		this.postcodeLabel.setText("");
		this.cityLabel.setText("");
		this.countryLabel.setText("");
		this.performancesPane.clear();
	}

}