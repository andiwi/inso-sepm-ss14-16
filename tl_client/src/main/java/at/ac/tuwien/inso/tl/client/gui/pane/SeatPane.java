package at.ac.tuwien.inso.tl.client.gui.pane;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import org.apache.log4j.Logger;

import at.ac.tuwien.inso.tl.client.gui.controller.PerformanceController;
import at.ac.tuwien.inso.tl.client.util.BundleManager;
import at.ac.tuwien.inso.tl.client.util.Formatter;
import at.ac.tuwien.inso.tl.dto.TicketDto;

public class SeatPane extends VBox
{
	private static final Logger LOG = Logger.getLogger(SeatPane.class);

	private PerformanceController controller;
	private TicketDto ticket;
	private Boolean isSelected = false;
	private Boolean isUnavailable = false;
	private SeatsPane seatsPane;
	private BundleManager bm;

	public SeatPane(PerformanceController controller, SeatsPane seatsPane, TicketDto ticket)
	{
		this.getStyleClass().add("seat");
		this.controller = controller;
		this.ticket = ticket;
		this.seatsPane = seatsPane;

		init();
	}

	public TicketDto getTicket()
	{
		return this.ticket;
	}

	public void setIsSelected(boolean value)
	{
		this.isSelected = value;

		if (this.isSelected)
		{
			this.controller.onTicketSelected(this.ticket);
			this.getStyleClass().remove("unselected");
			this.getStyleClass().add("selected");
			this.getStyleClass().remove("reserved");
			this.getStyleClass().remove("ordered");
		} else
		{
			this.controller.onTicketDeselected(this.ticket);
			this.getStyleClass().remove("selected");
			this.getStyleClass().add("unselected");
		}
	}

	public void setIsUnavailable(boolean value)
	{
		this.isUnavailable = value;

		if (this.isUnavailable)
		{
			this.getStyleClass().add("unavailable");
		} else
		{
			this.getStyleClass().remove("unavailable");
		}
	}

	private void init()
	{
		this.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent arg0)
			{
				if (!SeatPane.this.isUnavailable)
				{
					SeatPane.this.setIsSelected(!SeatPane.this.isSelected);
				}
			}
		});

		Label seatNumber = new Label(this.ticket.getSeat().getSeatNumber());
		this.getChildren().add(seatNumber);

		if (this.ticket.getCategory() != null)
		{
			this.getChildren().add(new Label(bm.getBundle().getString("label_category") + this.ticket.getCategory()));
		}

		if (this.ticket.getReservation() != null)
		{
			this.getStyleClass().add("reserved");
			this.getChildren().add(new Label("res."));
			Tooltip t = new Tooltip("Res. Nr. "
					+ this.ticket.getReservation().getReservationNumber());
			Tooltip.install(this, t);
			this.setOnMouseEntered(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					SeatPane.this.seatsPane.startHighlight(SeatPane.this.ticket.getReservation());
				}
			});
			this.setOnMouseExited(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					SeatPane.this.seatsPane.stopHighlight();
				}
			});
		} else if (this.ticket.getOrderItem() != null)
		{
			this.getStyleClass().add("ordered");
			this.getChildren().add(new Label("verk."));
			Tooltip t = new Tooltip("Best. Nr. "
					+ this.ticket.getOrderItem().getOrder().getOrderNumber());
			Tooltip.install(this, t);
			this.setOnMouseEntered(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					SeatPane.this.seatsPane.startHighlight(SeatPane.this.ticket.getOrderItem()
							.getOrder());
				}
			});
			this.setOnMouseExited(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					SeatPane.this.seatsPane.stopHighlight();
				}
			});
		} else
		{
			String s = "";
			if (this.ticket.getCategory() != null && !this.ticket.getCategory().isEmpty())
			{
				s = s + this.ticket.getCategory() + " | ";
			}
			s = s + Formatter.currency(this.ticket.getPrice());
			this.getChildren().add(new Label(s));
		}

		this.setPrefHeight(50);
		this.setPrefWidth(70);
	}
}
