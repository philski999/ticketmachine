package ticketApi;

import java.util.List;

public interface Ticket {
	// The ticket description, aka title
	String getDescription() ;

	// A summary of the ticket
	String getSummary() ;

	// Log messages that have been added to the ticket
	List<String> getLogMessages() ;

	// A unique reference to the ticket
	String getReference() ;
	
	// A URL that can be used to display the ticket.
	String getShowUrl();
	
	// Some kind of target milestone
	String getTargetMilestone();
	
	// The product that the ticket relates to
	String getProduct() ;
	
	// The component of the product that the ticket relates to
	String getComponent();
}
