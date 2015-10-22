package at.ac.tuwien.inso.tl.client.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import at.ac.tuwien.inso.tl.client.TicketlineClient;
import at.ac.tuwien.inso.tl.client.gui.controller.CustomerSelectionController;
import at.ac.tuwien.inso.tl.client.gui.controller.OrderSelectionController;
import at.ac.tuwien.inso.tl.client.gui.controller.PerformanceController;
import at.ac.tuwien.inso.tl.client.gui.controller.PerformanceSelectionController;
import at.ac.tuwien.inso.tl.client.gui.controller.ReservationSelectionController;
import at.ac.tuwien.inso.tl.client.gui.controller.ShowsController;
import at.ac.tuwien.inso.tl.client.gui.controller.StartpageController;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;
import javafx.application.HostServices;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Navigator
{
	private static final Logger LOG = Logger.getLogger(Navigator.class);

	private static Navigator instance;

	private BorderPane mainBorderPane;

	private HostServices hostServices;

	private Navigator()
	{
	}

	public static Navigator getInstance()
	{
		if (Navigator.instance == null)
		{
			Navigator.instance = new Navigator();
		}

		return Navigator.instance;
	}

	public void setMainPane(BorderPane mainBorderPane)
	{
		this.mainBorderPane = mainBorderPane;
	}

	public void gotoStartpage()
	{
		this.setCenterFxml("Startpage");
	}

	public void gotoStartpage(String msg)
	{
		StartpageController c = (StartpageController) this.setCenterFxml("Startpage");
		c.setMessage(msg);
	}

	public void gotoCustomers()
	{
		this.setCenterFxml("Customers");
	}

	public void gotoArtists()
	{
		this.setCenterFxml("Artists");
	}

	public void gotoLocations()
	{
		this.setCenterFxml("Locations");
	}

	public void gotoShows()
	{
		this.setCenterFxml("Shows");
	}


	public void gotoShow(ShowDto show)
	{
		ShowsController c = (ShowsController) this.setCenterFxml("Shows");
		c.setShow(show);
	}

	public void gotoPerformance(PerformanceDto performance)
	{
		PerformanceController c = (PerformanceController) this.setCenterFxml("Performance");
		c.setPerformance(performance);
	}
	
	public void gotoReservation(ReservationDto reservation)
	{
		PerformanceController c = (PerformanceController) this.setCenterFxml("Performance");
		c.setReservation(reservation);
	}
	
	public void gotoOrder(OrderDto order)
	{
		PerformanceController c = (PerformanceController) this.setCenterFxml("Performance");
		c.setOrder(order);
	}

	public void gotoPerformance()
	{
		this.setCenterFxml("Performance");
	}

	public void gotoLogin()
	{

		AnchorPane page = (AnchorPane) SpringFxmlLoader.getInstance().load("/gui/ClientLogin.fxml");
		Scene scene = new Scene(page);
		scene.getStylesheets().add("/gui/style.css");

		Stage clientStage = new Stage();
		clientStage.setResizable(false);
		clientStage.setScene(scene);
		clientStage.setTitle(BundleManager.getBundle().getString("app_name"));
		clientStage.centerOnScreen();
		clientStage.show();

		Stage stage = (Stage) this.mainBorderPane.getScene().getWindow();
		stage.close();
	}

	private Object setCenterFxml(String filename)
	{
		FXMLLoader loader = SpringFxmlLoader.getInstance().getLoader("/gui/" + filename + ".fxml");

		try
		{
			this.mainBorderPane.setCenter((Node) loader.load());
		} catch (IOException ioException)
		{
			throw new RuntimeException(ioException);
		}
		return loader.getController();
	}

	public void openCustomerSelection(PerformanceController controller, Event event)
	{
		CustomerSelectionController c = (CustomerSelectionController) this.openWindow(
				"CustomerSelection", event);
		c.setParentController(controller);
	}

	public void openPerformanceSelection(PerformanceController controller, Event event)
	{
		PerformanceSelectionController c = (PerformanceSelectionController) this.openWindow(
				"PerformanceSelection", event);
		c.setParentController(controller);
	}

	public void openReservationSelection(PerformanceController controller, Event event)
	{
		ReservationSelectionController c = (ReservationSelectionController) this.openWindow(
				"ReservationSelection", event);
		c.setParentController(controller);
	}

	public void openOrderSelection(PerformanceController controller, Event event)
	{
		OrderSelectionController c = (OrderSelectionController) this.openWindow("OrderSelection",
				event);
		c.setParentController(controller);
	}

	private Object openWindow(String filename, Event event)
	{
		FXMLLoader loader = SpringFxmlLoader.getInstance().getLoader("/gui/" + filename + ".fxml");
		Stage stage = new Stage();
		try
		{
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(
					TicketlineClient.class.getResource("/gui/style.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node) event.getSource()).getScene().getWindow());
			stage.show();
		} catch (IOException ioException)
		{
			throw new RuntimeException(ioException);
		}
		return loader.getController();
	}

	public HostServices getHostServices()
	{
		return hostServices;
	}

	public void setHostServices(HostServices hostServices)
	{
		this.hostServices = hostServices;
	}

	public void setCursor(Cursor c)
	{
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info(c.toString());
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		LOG.info("######################################");
		this.mainBorderPane.setCursor(c);
	}
}
