package at.ac.tuwien.inso.tl.client.gui.controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.client.client.ArticleService;
import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.exception.ValidationException;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.Sex;

@Component
public class ShopController implements Initializable
{
	private static final Logger LOG = Logger.getLogger(ShopController.class);

	@Autowired
	private ArticleService articleService;

	@Autowired
	private AuthService authService;

	@FXML
	private TableView<ArticleDto> cartTableView;
	@FXML
	private TableView<ArticleDto> articleTableView;

	@FXML
	private TableColumn<ArticleDto, String> articleNameColumn;
	@FXML
	private TableColumn<ArticleDto, String> articlePriceColumn;
	@FXML
	private TableColumn<ArticleDto, String> articleDescriptionColumn;

	@FXML
	private TableColumn<ArticleDto, Integer> cartAmountColumn;
	@FXML
	private TableColumn<ArticleDto, String> cartArticleColumn;
	@FXML
	private TableColumn<ArticleDto, Integer> cartPriceColumn;

	@FXML
	private ComboBox<String> paymentComboBox;

	@FXML
	private TextField amountTextField;

	@FXML
	private Label customerLabel;

	private ObservableList<String> paymentSelection;

	private ObservableList<ArticleDto> articleTableViewData = FXCollections.observableArrayList();
	private ObservableList<ArticleDto> cartTableViewData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle resBundle)
	{
		LOG.info("ArtistsController initialize");

		this.articleTableViewData.clear();
		this.cartTableViewData.clear();
		cartTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));
		articleTableView.setPlaceholder(new Text(BundleManager.getBundle().getString("tableView_blank")));

		paymentSelection = FXCollections.observableArrayList();
		paymentSelection.add("CREDITCARD");
		paymentSelection.add("CASH");
		paymentSelection.add("BANKACCOUNT");

		paymentComboBox.getItems().clear();
		paymentComboBox.getItems().setAll(paymentSelection);

		try
		{
			this.articleTableViewData.addAll(this.articleService
					.findArticles(""));
		} catch (ServiceException e)
		{
			e.printStackTrace();
		}

		this.articleTableView.setItems(this.articleTableViewData);

		/*
		 * this.articleTableView.getSelectionModel().selectedItemProperty().
		 * addListener(new ChangeListener<ArticleDto>() {
		 * 
		 * @Override public void changed(ObservableValue<? extends ArticleDto>
		 * observable, ArticleDto oldValue, ArticleDto newValue) {
		 * ShopController.this.onTableViewSelectionChange(newValue); } });
		 */

		this.articleNameColumn.setCellValueFactory(new PropertyValueFactory<ArticleDto, String>(
				"title"));
		this.articlePriceColumn.setCellValueFactory(new Callback<CellDataFeatures<ArticleDto, String>, ObservableValue<String>>()
				{
			@Override
			public ObservableValue<String> call(CellDataFeatures<ArticleDto, String> p)
			{
				return new SimpleStringProperty(Formatter.currency(p.getValue().getPrice()));
			}
		});
		this.articleDescriptionColumn
				.setCellValueFactory(new PropertyValueFactory<ArticleDto, String>("description"));

	}

	@FXML
	private void onSelectCartButtonClicked()
	{
		LOG.info("onSelectCartButtonClicked");
		try
		{
			int anzahl = Integer.parseInt(amountTextField.getText());
			LOG.info("Anzahl an Artikeln: " + anzahl);
		} catch (NumberFormatException e)
		{
			Dialogs.showErrorDialog((Stage) this.cartTableView.getScene().getWindow(),
					"Bitte eine gültige Anzahl eingeben");
			return;
		}

	}

	@FXML
	private void onSelectPayClicked()
	{
		LOG.info("onSelectPayClicked");
	}

	@FXML
	private void paymentSelected()
	{
		LOG.info("paymentSelected");
	}

	/*
	 * protected void onTableViewSelectionChange(ArticleDto newValue) {
	 * this.hideForm(); if (newValue == null) { return; }
	 * 
	 * try { this.showForm(this.articleService.findArticleById(newValue)); }
	 * catch (ServiceException e) {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * private void hideForm() {
	 * 
	 * } private void showForm(ArticleDto findArticleById) {
	 * 
	 * }
	 */
}
