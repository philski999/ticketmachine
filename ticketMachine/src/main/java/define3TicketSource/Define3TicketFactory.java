package define3TicketSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import ticketApi.AbstractTicketFactory;
import ticketApi.TicketBuilder;
import ticketApi.TicketSource;

public class Define3TicketFactory implements AbstractTicketFactory {
	
	private TicketBuilder builder;
	private RestTemplate restTemplate;
	
	private int loadLimit;
	private String repositoryUrl;
	
	@Autowired
	void setTicketBuilder(TicketBuilder aBuilder) {
		builder = aBuilder;
	}
	
	@Autowired
	void setRestTemplate(RestTemplate aTemplate) {
		restTemplate = aTemplate;
	}
	
    // The maximum number of tickets to be downloaded. Set to -1 for unlimited.
	public void setInitialLoadLimit(Integer aLoadLimit) {
		loadLimit = aLoadLimit;
	}

	public void setRepositoryUrl(String aRepositoryUrl) {
		repositoryUrl = aRepositoryUrl;
	}
	
	
	public TicketSource getTicketSource() {
		Define3TicketSource source = new Define3TicketSource();
		source.setInitialLoadLimit(loadLimit);
		source.setRepositoryUrl(repositoryUrl);
		source.setRestTemplate(restTemplate);
		source.setTicketBuilder(builder);
		return source;
	}
}
