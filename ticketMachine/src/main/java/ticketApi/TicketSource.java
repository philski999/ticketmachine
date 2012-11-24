package ticketApi;

import java.util.List;

public interface TicketSource {	
	void init();
	
	// Transfer all tickets to the sink - enables incremental processing
	void fill(TicketSink sink);
	
	// List all tickets
	List<Ticket> findAllTickets();
}
