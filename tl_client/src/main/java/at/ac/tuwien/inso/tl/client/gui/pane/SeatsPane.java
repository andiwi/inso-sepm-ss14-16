package at.ac.tuwien.inso.tl.client.gui.pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import at.ac.tuwien.inso.tl.client.gui.controller.PerformanceController;
import at.ac.tuwien.inso.tl.client.util.ShoppingBasket;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.SeatDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SeatsPane extends GridPane
{
	private PerformanceController controller;
	private ShoppingBasket shoppingBasket;
	private List<SeatPane> seatPanes;

	public SeatsPane(PerformanceController controller, ShoppingBasket shoppingBasket)
	{
		this.controller = controller;
		this.shoppingBasket = shoppingBasket;
		this.seatPanes = new ArrayList<SeatPane>();
	}

	public void showSeats(PerformanceDto p, ReservationDto r, OrderDto o)
	{
		int rows = 0;
		int cols = 0;
		Map<Integer, Map<Integer, TicketDto>> m = new HashMap<Integer, Map<Integer, TicketDto>>();

		// calculate how many cols and rows there are
		// and fill the hashmap for drawing
		for (TicketDto t : p.getTickets())
		{
			t.setPerformance(p);
			SeatDto s = t.getSeat();
			rows = Math.max(s.getRow(), rows);
			cols = Math.max(s.getColumn(), cols);
			Map<Integer, TicketDto> n;
			if (m.containsKey(s.getRow()))
			{
				n = m.get(s.getRow());
			} else
			{
				n = new HashMap<Integer, TicketDto>();
			}
			n.put(s.getColumn(), t);
			m.put(s.getRow(), n);
		}

		this.clear();

		for (int row = 0; row <= rows; row++)
		{
			RowConstraints c = new RowConstraints();
			c.setMinHeight(40);
			this.getRowConstraints().add(c);
		}

		for (int col = 0; col <= cols; col++)
		{
			ColumnConstraints c = new ColumnConstraints();
			c.setMinWidth(40);
			this.getColumnConstraints().add(c);
			for (int row = 0; row <= rows; row++)
			{
				if (m.containsKey(row) && m.get(row).containsKey(col))
				{
					TicketDto t = m.get(row).get(col);
					SeatPane sp = new SeatPane(this.controller, this, t);
					this.seatPanes.add(sp);
					this.add(sp, col, row);
					if (this.shoppingBasket.containsTicket(t))
					{
						sp.setIsSelected(true);
					}

					if (t.getOrderItem() != null
							&& (o == null || !o.equals(t.getOrderItem().getOrder())))
					{
						sp.setIsUnavailable(true);
					}

					if (t.getReservation() != null && (r == null || !r.equals(t.getReservation())))
					{
						sp.setIsUnavailable(true);
					}
				}
			}
		}

		this.setHgap(5);
		this.setVgap(5);
		this.setAlignment(Pos.CENTER);
	}

	public void clear()
	{
		this.getChildren().clear();
		this.getColumnConstraints().clear();
		this.getRowConstraints().clear();
	}

	public void startHighlight(ReservationDto reservation)
	{
		for (SeatPane sp : this.seatPanes)
		{
			if (reservation.equals(sp.getTicket().getReservation()))
			{
				sp.getStyleClass().add("highlight");
			}
		}
	}

	public void stopHighlight()
	{
		for (SeatPane sp : this.seatPanes)
		{
			sp.getStyleClass().remove("highlight");
		}
	}

	public void startHighlight(OrderDto order)
	{
		for (SeatPane sp : this.seatPanes)
		{
			if (sp.getTicket().getOrderItem() != null
					&& order.equals(sp.getTicket().getOrderItem().getOrder()))
			{
				sp.getStyleClass().add("highlight");
			}
		}
	}
}
