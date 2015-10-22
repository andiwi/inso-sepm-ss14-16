package at.ac.tuwien.inso.tl.client.gui.pane;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import eu.schudt.javafx.controls.calendar.DatePicker;
import at.ac.tuwien.inso.tl.client.TicketlineClient;
import at.ac.tuwien.inso.tl.client.gui.controller.LocationsController;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.client.util.Navigator;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class PerformancesPane extends VBox
{
	private static final Logger LOG = Logger.getLogger(PerformancesPane.class);

	private HBox showFilterHBox;
	private HBox dateFilterHBox;
	private HBox timeFilterHBox;

	private DatePicker startDatePicker;

	private TextField showTextField;

	private TextField startDateTextField;
	private TextField endDateTextField;
	private TextField startTimeTextField;
	private TextField endTimeTextField;

	private TableView<PerformanceDto> performancesTableView;

	private TableColumn<PerformanceDto, String> showColumn;
	private TableColumn<PerformanceDto, String> roomColumn;
	private TableColumn<PerformanceDto, String> startsAtColumn;
	
	private BundleManager bm;
	

	private ObservableList<PerformanceDto> performancesTableViewData = FXCollections
			.observableArrayList();

	private List<PerformanceDto> performances;

	private Callback<PerformanceDto, PerformanceDto> performanceSelectionCallback;
	
	private Boolean displayShowFilter = true;

	public PerformancesPane() {
		this(true);
	}
	
	public PerformancesPane(Callback<PerformanceDto, PerformanceDto> cb)
	{
		this();
		this.performanceSelectionCallback = cb;
	}
	public PerformancesPane(Callback<PerformanceDto, PerformanceDto> cb, Boolean displayShowFilter)
	{
		this(displayShowFilter);
		this.performanceSelectionCallback = cb;
	}

	public PerformancesPane(Boolean displayShowFilter)
	{
		this.displayShowFilter = displayShowFilter;
		this.showFilterHBox = new HBox();
		Label l = new Label();
		l.setText(bm.getBundle().getString("label_show"));
		l.getStyleClass().add("textField");
		this.showFilterHBox.getChildren().add(l);
		this.showTextField = new TextField();
		this.showTextField.getStyleClass().add("textField");
		this.showFilterHBox.getChildren().add(this.showTextField);
		if (this.displayShowFilter) {
			this.getChildren().add(this.showFilterHBox);
		}

		this.dateFilterHBox = new HBox();

		l = new Label();
		l.setText(bm.getBundle().getString("label_date"));
		l.getStyleClass().add("textField");
		this.dateFilterHBox.getChildren().add(l);

		this.startDatePicker = new DatePicker();

		this.startDatePicker.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
		this.startDatePicker.getCalendarView().todayButtonTextProperty().set("Today");
		this.startDatePicker.getCalendarView().setShowWeeks(false);
		this.startDatePicker.getStylesheets().add(
				TicketlineClient.class.getResource("/gui/DatePicker.css").toExternalForm());

		this.startDateTextField = new TextField();
		this.startDateTextField.setPromptText(bm.getBundle().getString("text_from"));
		this.startDateTextField.getStyleClass().add("textField");
		this.dateFilterHBox.getChildren().add(this.startDateTextField);

		this.endDateTextField = new TextField();
		this.endDateTextField.setPromptText(bm.getBundle().getString("text_to"));
		this.endDateTextField.getStyleClass().add("textField");
		this.dateFilterHBox.getChildren().add(this.endDateTextField);

		this.timeFilterHBox = new HBox();

		l = new Label(bm.getBundle().getString("label_time"));
		l.getStyleClass().add("textField");
		this.timeFilterHBox.getChildren().add(l);

		this.startTimeTextField = new TextField();
		this.startTimeTextField.setPromptText(bm.getBundle().getString("text_from"));
		this.startTimeTextField.getStyleClass().add("textField");
		this.timeFilterHBox.getChildren().add(this.startTimeTextField);

		this.endTimeTextField = new TextField();
		this.endTimeTextField.setPromptText(bm.getBundle().getString("text_to"));
		this.endTimeTextField.getStyleClass().add("textField");
		this.timeFilterHBox.getChildren().add(this.endTimeTextField);

		this.performancesTableView = new TableView<PerformanceDto>();
		this.performancesTableView.setItems(this.performancesTableViewData);
		performancesTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));

		this.showColumn = new TableColumn<PerformanceDto, String>(bm.getBundle().getString("tableView_show"));
		this.roomColumn = new TableColumn<PerformanceDto, String>(bm.getBundle().getString("tableView_room"));
		this.startsAtColumn = new TableColumn<PerformanceDto, String>(bm.getBundle().getString("tableView_time"));

		this.startsAtColumn
				.setCellValueFactory(new Callback<CellDataFeatures<PerformanceDto, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(CellDataFeatures<PerformanceDto, String> p)
					{
						return new SimpleStringProperty(Formatter.dateTimeShort(p.getValue()
								.getStartsAt()));
					}
				});
		this.roomColumn
				.setCellValueFactory(new Callback<CellDataFeatures<PerformanceDto, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(CellDataFeatures<PerformanceDto, String> p)
					{
						return new SimpleStringProperty(p.getValue().getRoom().getTitle());
					}
				});
		this.showColumn
				.setCellValueFactory(new Callback<CellDataFeatures<PerformanceDto, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(CellDataFeatures<PerformanceDto, String> p)
					{
						return new SimpleStringProperty(p.getValue().getShow().getTitle());
					}
				});

		this.performancesTableView.getColumns().add(this.showColumn);
		this.performancesTableView.getColumns().add(this.roomColumn);
		this.performancesTableView.getColumns().add(this.startsAtColumn);

		// TODO: Bug: event fires if clicking anywhere on tableview
		this.performancesTableView.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				PerformanceDto selected = PerformancesPane.this.performancesTableView
						.getSelectionModel().getSelectedItem();
				if (event.getClickCount() > 1 && selected != null)
				{
					PerformancesPane.this.onPerformancesTableViewDoubleClick(selected);
				}
			}
		});

		ChangeListener<String> filterListener = new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue)
			{
				PerformancesPane.this.onFilterValueChange();
			}
		};

		this.startDateTextField.textProperty().addListener(filterListener);
		this.endDateTextField.textProperty().addListener(filterListener);
		this.startTimeTextField.textProperty().addListener(filterListener);
		this.endTimeTextField.textProperty().addListener(filterListener);
		this.showTextField.textProperty().addListener(filterListener);

		this.getChildren().addAll(this.dateFilterHBox, this.timeFilterHBox,
				this.performancesTableView);
		this.setSpacing(10);
	}
	
	public void setPerformances(List<PerformanceDto> ps)
	{
		this.performances = ps;
		this.performancesTableViewData.addAll(this.performances);
		this.onFilterValueChange();
	}

	public void clear()
	{
		this.performances = null;
		this.performancesTableViewData.clear();
	}

	private void onPerformancesTableViewDoubleClick(PerformanceDto selectedPerformance)
	{
		if (this.performanceSelectionCallback != null)
		{
			this.performanceSelectionCallback.call(selectedPerformance);
		} else
		{
			Navigator.getInstance().gotoPerformance(selectedPerformance);
		}
	}

	private void onFilterValueChange()
	{
		this.performancesTableViewData.clear();

		SimpleDateFormat parser = new SimpleDateFormat("dd.MM.yyyy");

		Date startDate = null;
		Date endDate = null;

		if (!this.startDateTextField.getText().isEmpty())
		{
			try
			{
				startDate = parser.parse(this.startDateTextField.getText());
			} catch (ParseException e1)
			{
			}
		}
		if (!this.endDateTextField.getText().isEmpty())
		{
			try
			{
				endDate = parser.parse(this.endDateTextField.getText());
			} catch (ParseException e1)
			{
			}
		}

		for (PerformanceDto p : this.performances)
		{
			boolean t = this.checkTimeFilter(p);
			String showTitle = p.getShow().getTitle().toLowerCase();
			String filter = this.showTextField.getText().toLowerCase();
			if (!filter.isEmpty() && !showTitle.contains(filter))
			{
				continue;
			}

			if (t && endDate == null && startDate == null)
			{
				this.performancesTableViewData.add(p);
				continue;
			}

			if (t && endDate == null && startDate != null && startDate.before(p.getStartsAt()))
			{
				this.performancesTableViewData.add(p);
				continue;
			}

			if (t && endDate != null && startDate == null && endDate.after(p.getStartsAt()))
			{
				this.performancesTableViewData.add(p);
				continue;
			}

			if (t && endDate != null && startDate != null && endDate.after(p.getStartsAt())
					&& startDate.before(p.getStartsAt()))
			{
				this.performancesTableViewData.add(p);
				continue;
			}
		}
	}

	private boolean checkTimeFilter(PerformanceDto p)
	{
		Integer startH = null;
		Integer startM = null;
		Integer endH = null;
		Integer endM = null;

		NumberFormat nf = NumberFormat.getIntegerInstance();

		if (!this.startTimeTextField.getText().isEmpty())
		{
			try
			{
				startH = nf.parse(this.startTimeTextField.getText()).intValue();
			} catch (ParseException e1)
			{
			}
		}
		if (!this.endTimeTextField.getText().isEmpty())
		{
			try
			{
				endH = nf.parse(this.endTimeTextField.getText()).intValue();
			} catch (ParseException e1)
			{
			}
		}

		Calendar c = GregorianCalendar.getInstance();
		c.setTime(p.getStartsAt());

		if (startH == null && endH == null)
		{
			return true;
		}

		if (startH != null && endH == null)
		{
			LOG.info(c.get(Calendar.HOUR_OF_DAY));
			LOG.info(startH);
			return c.get(Calendar.HOUR_OF_DAY) >= startH;
		}

		if (startH == null && endH != null)
		{
			return c.get(Calendar.HOUR_OF_DAY) <= endH;
		}

		return c.get(Calendar.HOUR_OF_DAY) >= startH && c.get(Calendar.HOUR_OF_DAY) <= endH;
	}

}
