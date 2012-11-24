package ticketFinder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ticketApi.AbstractTicketFactory;
import ticketApi.Ticket;
import ticketApi.TicketSource;

@Component
public class TicketFinder {
	
	private TicketSource ticketSource;

	@Autowired
	void setTicketFactory(AbstractTicketFactory aFactory) {
	}
	
	@Autowired
	void setTicketSource(TicketSource aTicketSource) {
		ticketSource = aTicketSource;
	}
	
	public List<Ticket> findTickets(String aCommentFragment) {
		return ticketSource.findAllTickets();
	}
}
