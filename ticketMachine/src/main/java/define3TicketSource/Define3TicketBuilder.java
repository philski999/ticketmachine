package define3TicketSource;

import org.springframework.stereotype.Component;

import ticketApi.Ticket;
import ticketApi.TicketBuilder;

/**
 * A component that builds #define3 ticket structures from pieces of ticket information
 */
@Component
class Define3TicketBuilder implements TicketBuilder {

	public TicketBuilder setProduct(String product) {
		ticket.setProduct(product);
		return this;
	}

	public TicketBuilder setComponent(String component) {
		ticket.setComponent(component);
		return this;
	}

	public TicketBuilder setMilestone(String milestone) {
		ticket.setTargetMilestone(milestone);
		return this;
	}

	private Define3Ticket ticket = new Define3Ticket();

	public Ticket createTicketInstance() {
		return ticket;
	}

	public Ticket createTicketInstance(Ticket existingTicket) {
		Define3Ticket t = new Define3Ticket(existingTicket);
		return t;
	}

	public TicketBuilder setTicketReference(String reference) {
		ticket.setReference(reference);
		return this;
	}

	public TicketBuilder addSummary(String summary) {
		ticket.setSummary(summary);
		return this;
	}

	public TicketBuilder addLogEntry(String logEntry) {
		ticket.addLogEntry(logEntry);
		return this;
	}

	public TicketBuilder addDescription(String description) {
		ticket.setDescription(description);
		return this;
	}

	public TicketBuilder reset() {
		ticket = new Define3Ticket();
		return this;
	}

}
