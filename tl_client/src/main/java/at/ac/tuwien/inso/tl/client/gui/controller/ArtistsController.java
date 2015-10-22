package at.ac.tuwien.inso.tl.client.gui.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.application.HostServices;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import at.ac.tuwien.inso.tl.client.client.ArtistService;
import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.gui.pane.PerformancesPane;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.client.util.Navigator;
import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.ParticipationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;

@Component
public class ArtistsController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(ArtistsController.class);

	private HostServices hostServices;

	@Autowired
	private ArtistService artistService;

	@Autowired
	private AuthService authService;

	@FXML
	private TextField searchTextField;
	@FXML
	private TableView<ArtistDto> artistsTableView;
	@FXML
	private TableColumn<ArtistDto, String> firstnameColumn;
	@FXML
	private TableColumn<ArtistDto, String> lastnameColumn;
	@FXML
	private TableColumn<ArtistDto, String> descriptionColumn;
	@FXML
	private VBox detailsVBox;

	@FXML
	private Label firstnameLabel;
	@FXML
	private Label lastnameLabel;
	@FXML
	private Hyperlink descriptionLabel;

	private PerformancesPane performancesPane;

	private ObservableList<ArtistDto> artistsTableViewData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("ArtistsController initialize");

		setHostServices(Navigator.getInstance().getHostServices());

		this.performancesPane = new PerformancesPane();
		this.detailsVBox.getChildren().add(this.performancesPane);

		this.hideDataGridPane();

		// artists table view
		try
		{
			this.authService.login("marvin", "42");
			this.artistsTableViewData.clear();
			this.artistsTableViewData.addAll(this.artistService.findArtists(""));
		} catch (ServiceException e)
		{
			// TODO
			throw (new RuntimeException(e));
		}

		this.artistsTableView.setItems(this.artistsTableViewData);
		artistsTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));
		this.artistsTableView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ArtistDto>()
				{
					@Override
					public void changed(ObservableValue<? extends ArtistDto> observable,
							ArtistDto oldValue, ArtistDto newValue)
					{
						ArtistsController.this.onArtistsTableViewSelectionChange(newValue);
					}
				});

		this.firstnameColumn.setCellValueFactory(new PropertyValueFactory<ArtistDto, String>(
				"firstname"));
		this.lastnameColumn.setCellValueFactory(new PropertyValueFactory<ArtistDto, String>(
				"lastname"));
		this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<ArtistDto, String>(
				"description"));

		// search text field
		this.searchTextField.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue)
			{
				ArtistsController.this.onSearchValueChange(newValue);
			}
		});
	}

	private void onSearchValueChange(String search)
	{
		this.artistsTableViewData.clear();
		try
		{
			this.artistsTableViewData.addAll(this.artistService.findArtists(search));
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void onArtistsTableViewSelectionChange(ArtistDto newValue)
	{
		this.hideDataGridPane();

		if (newValue == null)
		{
			return;
		}

		try
		{
			this.showDataGridPane(this.artistService.getArtistById(newValue));
		} catch (ServiceException e)
		{
			// TODO
		}
	}

	private void showDataGridPane(ArtistDto artist)
	{

		this.firstnameLabel.setText(artist.getFirstname());
		this.lastnameLabel.setText(artist.getLastname());
		this.descriptionLabel.setText(artist.getDescription());
		this.detailsVBox.setVisible(true);

		try
		{
			this.performancesPane.setPerformances(this.artistService
					.getPerformancesByArtistId(artist));
		} catch (ServiceException e)
		{
			// TODO
			throw (new RuntimeException(e));
		}
	}

	private void hideDataGridPane()
	{
		this.detailsVBox.setVisible(false);
		this.firstnameLabel.setText("");
		this.lastnameLabel.setText("");
		this.descriptionLabel.setText("");
		this.performancesPane.clear();
	}

	@FXML
	private void onBioClicked(ActionEvent ae)
	{

		LOG.info("-------------- On bio clicked ------------");
		LOG.info("---- Ya shall open this: " + descriptionLabel.getText());
		hostServices.showDocument(descriptionLabel.getText());
	}

	public void setHostServices(HostServices hostServices)
	{
		// TODO Auto-generated method stub

		LOG.info("-------------- HostServices is set ------------");
		this.hostServices = hostServices;
	}
}
