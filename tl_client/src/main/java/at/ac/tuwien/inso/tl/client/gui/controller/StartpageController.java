package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.StringConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.client.NewsService;
import at.ac.tuwien.inso.tl.client.client.ShowService;
import at.ac.tuwien.inso.tl.client.client.TicketService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.gui.pane.NewsPane;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Navigator;
import at.ac.tuwien.inso.tl.dto.EmployeeDto;
//import at.ac.tuwien.inso.tl.dto.EmployeeDto;
import at.ac.tuwien.inso.tl.dto.NewsDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

@Component
public class StartpageController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(StartpageController.class);
	private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";

	@Autowired
	private NewsService newsService;

	@Autowired
	private AuthService authService;

	@Autowired
	private ShowService showService;

	@Autowired
	private TicketService ticketService;
	

	@FXML
	private VBox mainVBox;
	@FXML
	private VBox newsVBox;
	@FXML
	private ComboBox<String> showTypesComboBox;

	// BarChart
	@FXML
	private BarChart<String, Number> top10BarChart;
	ObservableList<String> showNames;
	@FXML
	CategoryAxis showAxis;
	XYChart.Series<String, Number> series;

	// TableView
	@FXML
	TableView<ShowDto> top10TableView;
	@FXML
	TableColumn<ShowDto, String> top10_title;
	@FXML
	TableColumn<ShowDto, String> top10_description;
	private ObservableList<ShowDto> top10List;

	// ShowTypes
	private ObservableList<String> showTypes;
	String selectedShowType;

	private List<ShowDto> allShows;
	private List<TicketDto> allTickets;

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("startpage controller init");
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);

		// Add ShowTypes
		showTypes = FXCollections.observableArrayList();
		showTypes.add("Movie");
		showTypes.add("Festival");
		showTypes.add("Concert");
		showTypes.add("Musical");
		showTypes.add("Opera");
		showTypes.add("Theater");

		showTypesComboBox.getItems().clear();
		showTypesComboBox.getItems().setAll(showTypes);

		List<NewsDto> news = null;
		try
		{
			EmployeeDto employee = new EmployeeDto();
			employee.setLastTimeLoggedIn(this.authService.getUserStatus().getLastTimeLoggedIn());

			news = this.newsService.getNewsByDate(employee);

		} catch (ServiceException e) {
			LOG.error(e);
		}

		
		 for(NewsDto n : news)
		 { 
			 String newsText = new String(n.getNewsText());
			 String title = new String(n.getTitle());
		  
			 this.newsVBox.getChildren().add(new NewsPane(title,
			 df.format(n.getSubmittedOn()), newsText)); 
		}
		 

		// Bar Chart
		showAxis = new CategoryAxis();
		showAxis.setLabel("Show");
		showAxis.setAutoRanging(false);

		showNames = FXCollections.observableArrayList();
		showAxis.setCategories(showNames);

		series = new XYChart.Series<>();

		top10BarChart.getData().add(series);
		
		NumberAxis na = (NumberAxis)top10BarChart.getYAxis();
		na.setTickLabelFormatter(new StringConverter<Number>()
		{
			
			@Override
			public String toString(Number arg0)
			{
				return String.format("%.1f", arg0);
			}
			
			@Override
			public Number fromString(String arg0)
			{
				return Double.parseDouble(arg0);
			}
		});

		// TableView
		top10List = FXCollections.observableArrayList();

		top10_title.setCellValueFactory(new PropertyValueFactory<ShowDto, String>("title"));
		top10_description.setCellValueFactory(new PropertyValueFactory<ShowDto, String>(
				"description"));

		top10TableView.getItems().clear();
		top10TableView.getItems().setAll(top10List);
		top10TableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));

		showTypesComboBox.getSelectionModel().select(0);
		showTypeSelected();
	}

	public void setMessage(String msg)
	{
		final HBox hb = new HBox();
		Label l = new Label(msg);
		l.getStyleClass().add("success-message");
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().add(l);
		hb.setFillHeight(true);
		HBox.setHgrow(l, Priority.ALWAYS);
		this.mainVBox.getChildren().add(hb);
		VBox.setVgrow(hb, Priority.SOMETIMES);

		FadeTransition ft = new FadeTransition(Duration.millis(3000), hb);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.setOnFinished(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent arg0)
			{
				StartpageController.this.mainVBox.getChildren().remove(hb);
			}
		});
		SequentialTransition seqTransition = new SequentialTransition(new PauseTransition(
				Duration.millis(2000)), // wait a second
				ft);
		seqTransition.play();
	}

	@FXML
	public void onShowAllNewsClicked()
	{
		List<NewsDto> news = null;
		try
		{
			news = this.newsService.getNews();
		} catch (ServiceException e)
		{
			LOG.error(e);
			throw (new RuntimeException(e));
		}

		this.newsVBox.getChildren().clear();

		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);

		for (NewsDto n : news)
		{
			String newsText = new String(n.getNewsText());
			String title = new String(n.getTitle());

			this.newsVBox.getChildren().add(
					new NewsPane(title, df.format(n.getSubmittedOn()), newsText));
		}
	}

	@FXML
	private void showTypeSelected()
	{
		selectedShowType = showTypesComboBox.getSelectionModel().getSelectedItem().toUpperCase();
		if (selectedShowType == null)
			return;

		LOG.debug("ShowType selected: " + selectedShowType);

		try
		{
			allShows = showService.findShows(selectedShowType);
			allTickets = ticketService.getAllTickets();
		} catch (ServiceException e)
		{
			LOG.error(e);
			throw (new RuntimeException(e));
		}

		int[][] soldTickets = new int[allShows.size()][2];
		for (int i = 0; i < allShows.size(); i++)
		{
			soldTickets[i][0] = i;
			soldTickets[i][1] = 0;
			for (int j = 0; j < allTickets.size(); j++)
			{
				int showIdTicket = -1;
				try
				{
					showIdTicket = allTickets.get(j).getPerformance().getShow().getId();
				} catch (NullPointerException e)
				{
					LOG.error(e);
					// throw(new RuntimeException(e));
				}

				int showId = allShows.get(i).getId();
				if (showId == showIdTicket && allTickets.get(j).getOrderItem() != null) // Wenn Ticket zur Show gehoert
				{
					soldTickets[i][1]++;
				}
			}
		}

		// Nach verkauften Tickets sortieren
		Arrays.sort(soldTickets, new Comparator<int[]>()
		{
			@Override
			public int compare(final int[] entry1, final int[] entry2)
			{
				final Integer val1 = entry1[1];
				final Integer val2 = entry2[1];
				return val2.compareTo(val1);
			}
		});

		// top10 befuellen
		series.getData().clear();
		showNames.clear();
		top10List.clear();
		for (int i = 0; i < Math.min(allShows.size(), 10); i++)
		{
			int showId = soldTickets[i][0];
			int sold = soldTickets[i][1];
			String showName = allShows.get(showId).getTitle();

			// BarChart
			showNames.add(showName);
			series.getData().add(new XYChart.Data<String, Number>(showName, sold));

			// TableView
			top10List.add(allShows.get(showId));
		}
		// Top10 auffuellen
		/*
		 * for(int i=allShows.size(); i<10; i++) {
		 * showNames.add("none"+String.valueOf(i)); series.getData().add(new
		 * XYChart.Data<String, Number>("none"+String.valueOf(i), 0)); }
		 */
		showAxis.setCategories(showNames);

		top10TableView.getItems().clear();
		top10TableView.getItems().addAll(top10List);

		// top10BarChart.getData().clear();
		// top10BarChart.getData().add(series);
	}

	@FXML
	private void top10TableViewClicked()
	{
		ShowDto selectedShow = top10TableView.getSelectionModel().getSelectedItem();

		if (selectedShow == null)
			return;

		Navigator.getInstance().gotoShow(selectedShow);
		// Navigator.getInstance().openPerformanceSelection(this, event);
	}

}
