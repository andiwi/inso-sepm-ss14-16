package at.ac.tuwien.inso.tl.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.util.Navigator;

@Component
public class MainController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(MainController.class);

	@Autowired
	private AuthService authService;
	
	@FXML
	BorderPane mainBorderPane;

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("ClientMainController initialize");
       try
       {
    	   this.authService.login("marvin", "42");
       } catch (ServiceException e)
       {
    	   LOG.error(e);
       }

		Navigator.getInstance().setMainPane(mainBorderPane);
		Navigator.getInstance().gotoStartpage();
	}

	@FXML
	private void onMenuStartpageClick()
	{
		Navigator.getInstance().gotoStartpage();
	}

	@FXML
	private void onMenuCustomersClick()
	{
		Navigator.getInstance().gotoCustomers();
	}

	@FXML
	private void onMenuArtistsClick()
	{
		Navigator.getInstance().gotoArtists();
	}

	@FXML
	private void onMenuLocationsClick()
	{
		Navigator.getInstance().gotoLocations();
	}

	@FXML
	private void onMenuShowsClick()
	{
		Navigator.getInstance().gotoShows();
	}

	@FXML
	private void onMenuPerformanceClick()
	{
		Navigator.getInstance().gotoPerformance();
	}

	@FXML
	private void onMenuLogoutClick()
	{
		try
		{
			this.authService.logout();
			Navigator.getInstance().gotoLogin();
		} catch (ServiceException e)
		{
			LOG.error("Logout failed: " + e.getMessage(), e);
		}
	}


	@FXML
	private void handleExit(ActionEvent event)
	{
		try
		{
			this.authService.logout();
		} catch (ServiceException e)
		{
			LOG.error("Logout failed: " + e.getMessage(), e);
		}

		Platform.exit();
	}
}