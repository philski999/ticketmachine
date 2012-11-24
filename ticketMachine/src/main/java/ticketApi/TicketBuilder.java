package ticketApi;

public interface TicketBuilder {
	
	/**
	 * Builds a ticket from the current accumulated state
	 * 
	 * @return
	 */
	Ticket createTicketInstance() ;
	
	/**
	 * Create a new ticket instance based on an existing one.
	 * 
	 * @param existingTicket
	 * @return a new Ticket instance with the same values as the existing one.
	 */
	Ticket createTicketInstance(Ticket existingTicket);
	
	/**
	 * Reset the builder to its initial state allowing it to be used to create a new ticket.
	 */
	TicketBuilder reset() ;
	
	/**
	 * Set the identity of the ticket
	 * 
	 * @param reference an identifier for a ticket
	 */
	TicketBuilder setTicketReference(String reference);
	
	/**
	 * Add a summary of the ticket
	 * 
	 * @param summary A summary of the ticket
	 */
	TicketBuilder addSummary(String summary);
	
	
	/**
	 * Add a log entry to a ticket
	 * 
	 * @param logEntry A log entry to be stored in the ticket
	 */
	TicketBuilder addLogEntry(String logEntry);
	
	/**
	 * Add a description of the ticket
	 * 
	 * @param description a description of the ticket
	 */
	TicketBuilder addDescription(String description);
	
	/**
	 * @param product that the ticket relates to
	 * @return
	 */
	TicketBuilder setProduct(String product) ;
	
	/**
	 * @param component of the product that the ticket relates to
	 * @return
	 */
	TicketBuilder setComponent(String component);
	
	/**
	 * @param milestone that the ticket should be completed by
	 * @return
	 */
	TicketBuilder setMilestone(String milestone);
}
