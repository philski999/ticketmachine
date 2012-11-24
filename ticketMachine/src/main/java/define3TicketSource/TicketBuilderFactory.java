package define3TicketSource;

import org.springframework.stereotype.Component;

import ticketApi.TicketBuilder;

/**
 * A factory that allows new ticket builders to be created.
 */
@Component
class TicketBuilderFactory {
	TicketBuilder newBuilder() {
		return new Define3TicketBuilder();
	}
}
