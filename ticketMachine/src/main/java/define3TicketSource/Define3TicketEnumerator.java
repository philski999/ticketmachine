package define3TicketSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ticketApi.Ticket;
import ticketApi.TicketSink;

/**
 * A stateless layer on top of the adapter that knows how to iterate over tickets.
 */
@Component
class Define3TicketEnumerator {

	Define3Adapter adapter;
	
    private Logger logger = LoggerFactory.getLogger(Define3TicketEnumerator.class);
	
    @Autowired
    public void setAdapter(Define3Adapter adapter) {
    	this.adapter = adapter;
    }
    
	public Object getTicketIndex() {
		return adapter.getTicketIndex();
	}
    
	@SuppressWarnings("unchecked")
	public void fill(TicketSink sink, Object parsedTicketIndex, int loadLimit) {
		for (Object element : (List<Object>)parsedTicketIndex) {
			// Allow a limit to be set on the number of tickets to load
			if (loadLimit == 0)
				break;
			if (loadLimit > 0)
				loadLimit--;
			
			Ticket t = adapter.readTicket((Map<String, Object>)element) ;
			sink.addTicket(t);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ticket> findAllTickets(Object parsedTicketIndex, int loadLimit) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		
		for (Object element : (List<Object>)parsedTicketIndex) {
			// Allow a limit to be set on the number of tickets to load
			if (loadLimit == 0)
				break;
			if (loadLimit > 0)
				loadLimit--;
			
			Ticket t = adapter.readTicket((Map<String, Object>)element) ;
			ticketList.add(t);
		}
		
		return ticketList;
	}
}
