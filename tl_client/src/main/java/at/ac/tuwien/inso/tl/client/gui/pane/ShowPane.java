package at.ac.tuwien.inso.tl.client.gui.pane;

import java.util.Set;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.apache.log4j.Logger;

import at.ac.tuwien.inso.tl.client.gui.controller.PerformanceController;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

public class ShowPane extends HBox
{
	private static final Logger LOG = Logger.getLogger(ShowPane.class);

	private VBox leftVBox;
	private VBox rightVBox;
	
	private VBox leftVBox2;
	private VBox rightVBox2;

	private PerformanceController controller;
	private Set<TicketDto> tickets;
	private PerformanceDto performance;
	
	private Integer amount;
	private ArticleDto article;
	
	private BundleManager bm;

	public ShowPane(PerformanceController controller, PerformanceDto performance,
			Set<TicketDto> tickets)
	{
		this.controller = controller;
		this.performance = performance;
		this.tickets = tickets;

		init();
	}

	public ShowPane(PerformanceController controller, 
			ArticleDto article, Integer amount) {
		this.controller = controller;
		this.amount = amount;
		this.article = article;
		
		initMerchandise();
	}

	private void init()
	{
		this.setSpacing(5.0);
		this.leftVBox = new VBox();
		this.leftVBox.setFillWidth(true);

		this.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0)
			{
				ShowPane.this.controller.setPerformance(ShowPane.this.performance);
			}
		});

		this.setCursor(Cursor.HAND);

		Label l = new Label(this.performance.getShow().getTitle() + " - "
				+ Formatter.dateTimeShort(this.performance.getStartsAt()));
		this.leftVBox.getChildren().add(l);

		for (TicketDto ticket : this.tickets)
		{
			Label tl = new Label("Ticket: " + ticket.getTicketNumber() + ", "+bm.getBundle().getString("label_seat")+" "
					+ ticket.getSeat().getSeatNumber() + ", "
					+ Formatter.currency(ticket.getPrice()));
			tl.setStyle("-fx-padding: 0 0 0 20;");
			this.leftVBox.getChildren().add(tl);
		}

		this.rightVBox = new VBox();
		this.rightVBox.setFillWidth(true);
		Button b = new Button(bm.getBundle().getString("button_show"));
		b.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				ShowPane.this.controller.setPerformance(ShowPane.this.performance);
			}
		});
		this.rightVBox.getChildren().add(b);
		this.rightVBox.setAlignment(Pos.CENTER_RIGHT);

		this.setStyle("-fx-padding: 10 0 10 0;-fx-border-color: #999999;-fx-border-style: solid;-fx-border-width: 0 0 1 0;");

		this.getChildren().addAll(this.leftVBox, this.rightVBox);
	}
	
	private void initMerchandise()
	{
		this.setSpacing(5.0);
		this.leftVBox2 = new VBox();
		this.leftVBox2.setFillWidth(true);


		//Label l = new Label(this.performance.getShow().getTitle() + " - "
		//		+ Formatter.dateTimeShort(this.performance.getStartsAt()));
		Label l = new Label(bm.getBundle().getString("label_article"));
		this.leftVBox2.getChildren().add(l);

		
			Label tl = new Label(amount + "x " + article.getTitle() + ", "
					+ Formatter.currency(article.getPrice()));
			tl.setStyle("-fx-padding: 0 0 0 20;");
			this.leftVBox2.getChildren().add(tl);
		

		this.rightVBox2 = new VBox();
		this.rightVBox2.setFillWidth(true);
		Button b = new Button(bm.getBundle().getString("button_delete"));
		
		b.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent arg0)
					{
						//ShowPane.this.controller.setPerformance(ShowPane.this.performance);
						ShowPane.this.controller.removeArticle(article);
					}
				});
		
		
		this.rightVBox2.getChildren().add(b);
		this.rightVBox2.setAlignment(Pos.CENTER_RIGHT);

		this.setStyle("-fx-padding: 10 0 10 0;-fx-border-color: #999999;-fx-border-style: solid;-fx-border-width: 0 0 1 0;");

		this.getChildren().addAll(this.leftVBox2, this.rightVBox2);
	}
	
}
