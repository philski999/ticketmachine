package define3TicketSource;

import java.util.ArrayList;
import java.util.List;

import ticketApi.Ticket;

/**
 * The ticket structure supported by #define 3
 */
class Define3Ticket implements Ticket {

	private String description;
	private String summary;
	private List<String> logMessages = new ArrayList<String>();
	private String reference;
	private String showUrl;
	private String product;
	private String component;
	private String targetMilestone;
	
	public Define3Ticket(Ticket existingTicket) {
		Define3Ticket existingDefine3Ticket = (Define3Ticket)existingTicket;
		description = existingDefine3Ticket.description;
		summary = existingDefine3Ticket.summary;
		reference = existingDefine3Ticket.reference;
		logMessages = new ArrayList<String>(existingDefine3Ticket.logMessages);
	}

	public Define3Ticket() {
	}

	void setSummary(String aSummary) {
		summary = aSummary;
	}
	
	void addLogEntry(String logEntry) {
		logMessages.add(logEntry);
	}
	
	void setReference(String aReference) {
		reference = aReference;
		
		// Also set the URL for showing the ticket
		showUrl = reference.replaceAll("/api/2.0/", "/project/").replaceAll("/issues/","/workflow/standard/show/");
	}
	
	void setDescription(String aDescription) {
		description = aDescription;
	}

	@Override
	public String toString() {
		return "{ reference:\"" + reference +
			   "\", showUrl:\"" + showUrl +
			   "\", description:\"" + description +
			   "\", summary:\"" + summary +
			   "\", log:\""	+ logMessages + "\"}";
	}

	public String getDescription() {
		return description;
	}

	public String getSummary() {
		return summary;
	}

	public List<String> getLogMessages() {
		return logMessages;
	}

	public String getReference() {
		return reference;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setTargetMilestone(String targetMilestone) {
		this.targetMilestone = targetMilestone;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public String getProduct() {
		return product;
	}

	public String getTargetMilestone() {
		return targetMilestone;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}
}
