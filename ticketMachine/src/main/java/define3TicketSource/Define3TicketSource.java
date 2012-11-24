package define3TicketSource;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ticketApi.Ticket;
import ticketApi.TicketSink;
import ticketApi.TicketSource;


/**
 * A ticket source that provides access to tickets stored in #define 3
 */
class Define3TicketSource implements TicketSource {
	private boolean initialised;

    private Logger logger = LoggerFactory.getLogger(Define3TicketSource.class);
    
    // The maximum number of tickets to be downloaded. Set to -1 for unlimited.
	private int loadLimit;
	private Object parsedTicketIndex;
	private Define3TicketEnumerator ticketEnumerator;
	
	public void setInitialLoadLimit(Integer aLoadLimit) {
		loadLimit = aLoadLimit;
	}
	
	public void setTicketEnumerator(Define3TicketEnumerator ticketEnumerator) {
		this.ticketEnumerator = ticketEnumerator;
	}

	public void fill(TicketSink sink) {
		init();
		ticketEnumerator.fill(sink, parsedTicketIndex, loadLimit);
	}
	
	public List<Ticket> findAllTickets() {
		init();
		return ticketEnumerator.findAllTickets(parsedTicketIndex, loadLimit);
	}
	
	private void init() {
		if (!initialised) {
			// Pull the index
			parsedTicketIndex = ticketEnumerator.getTicketIndex();
			logger.trace("#define3 ticket source initialisation complete");
			
			initialised = true;			
		}
	}
}
