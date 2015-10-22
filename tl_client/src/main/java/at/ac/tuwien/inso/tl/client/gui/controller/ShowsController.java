package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import at.ac.tuwien.inso.tl.client.client.ShowService;
import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.gui.pane.PerformancesPane;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Navigator;
import at.ac.tuwien.inso.tl.dto.ShowDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;

@Component
public class ShowsController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(ShowsController.class);

	@Autowired
	private ShowService showService;

	@Autowired
	private AuthService authService;

	@FXML
	private TextField searchTextField;
	@FXML
	private TableView<ShowDto> showsTableView;
	@FXML
	private TableColumn<ShowDto, String> descriptionColumn;
	@FXML
	private TableColumn<ShowDto, String> showTypeColumn;
	@FXML
	private TableColumn<ShowDto, String> titleColumn;
	@FXML
	private VBox detailsVBox;

	@FXML
	private Label titleLabel;

	private PerformancesPane performancesPane;

	private ObservableList<ShowDto> showsTableViewData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("ShowsController initialize");

		this.performancesPane = new PerformancesPane(false);
		this.detailsVBox.getChildren().add(this.performancesPane);

		this.hideDataGridPane();

		// shows table view
		try
		{
			this.showsTableViewData.clear();
			this.showsTableViewData.addAll(this.showService.findShows(""));
		} catch (ServiceException e)
		{
			// TODO
			throw (new RuntimeException(e));
		}

		this.showsTableView.setItems(this.showsTableViewData);
		showsTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));
		this.showsTableView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ShowDto>()
				{
					@Override
					public void changed(ObservableValue<? extends ShowDto> observable,
							ShowDto oldValue, ShowDto newValue)
					{
						ShowsController.this.onShowsTableViewSelectionChange(newValue);
					}
				});

		this.showTypeColumn.setCellValueFactory(new PropertyValueFactory<ShowDto, String>(
				"showType"));
		this.titleColumn.setCellValueFactory(new PropertyValueFactory<ShowDto, String>("title"));
		this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<ShowDto, String>(
				"description"));

		// search text field
		this.searchTextField.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue)
			{
				ShowsController.this.onSearchValueChange(newValue);
			}
		});
	}

	private void onSearchValueChange(String search)
	{
		this.showsTableViewData.clear();
		try
		{
			this.showsTableViewData.addAll(this.showService.findShows(search));
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void onShowsTableViewSelectionChange(ShowDto newValue)
	{
		this.hideDataGridPane();

		if (newValue == null)
		{
			return;
		}

		try
		{
			this.showDataGridPane(this.showService.getShowById(newValue));
		} catch (ServiceException e)
		{
			// TODO
		}
	}

	private void showDataGridPane(ShowDto show)
	{
		this.titleLabel.setText(show.getTitle());
		this.detailsVBox.setVisible(true);
		try
		{
			this.performancesPane.setPerformances(this.showService.getPerformancesByShowId(show));
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
		this.performancesPane.clear();
	}

	public void setShow(ShowDto s)
	{
		if (s == null)
			return;
		try
		{
			showsTableView.getSelectionModel().select(showService.getShowById(s));
			showDataGridPane(showService.getShowById(s));
		} catch (ServiceException e)
		{
		}
	}
}
