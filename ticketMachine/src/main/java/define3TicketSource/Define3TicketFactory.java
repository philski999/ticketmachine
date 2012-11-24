package define3TicketSource;

import org.springframework.beans.factory.annotation.Autowired;

import ticketApi.AbstractTicketFactory;
import ticketApi.TicketSource;

/**
 * The ticket factory creates pre-configured, ready to use, ticket sources.
 */
public class Define3TicketFactory implements AbstractTicketFactory {
	
	private int loadLimit;
	private Define3TicketEnumerator enumerator ;
	
    // The maximum number of tickets to be downloaded. Set to -1 for unlimited.
	public void setInitialLoadLimit(Integer aLoadLimit) {
		loadLimit = aLoadLimit;
	}

	@Autowired
	public void setEnumerator(Define3TicketEnumerator enumerator) {
		this.enumerator = enumerator;
	}

	public TicketSource getTicketSource() {
		Define3TicketSource source = new Define3TicketSource();
		source.setInitialLoadLimit(loadLimit);
		source.setTicketEnumerator(enumerator);
		return source;
	}
}
