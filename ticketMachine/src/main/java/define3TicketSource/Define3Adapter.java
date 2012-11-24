package define3TicketSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ticketApi.Ticket;
import ticketApi.TicketBuilder;


/**
 *  A stateless adapter onto the #define3 data source.
 *  Key responsibilities are knowing how to pull data from the data source
 *  and knowing how to convert that data to Java objects.
 */
@Component
class Define3Adapter {
	
	private Pattern responseExtractor;
	private TicketBuilderFactory ticketBuilderFactory;
	private String repositoryUrl;
	private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(Define3Adapter.class);

	public void setRepositoryUrl(String aRepositoryUrl) {
		repositoryUrl = aRepositoryUrl;
	}

	public void setRestTemplate(RestTemplate aTemplate) {
		restTemplate = aTemplate;
	}
	
	@Autowired
	public void setTicketBuilderFactory(TicketBuilderFactory f) {
		ticketBuilderFactory = f;
	}
	
	public void init() {
		// A regular expression that will strip the while(1); cross site scripting protection from responses
		responseExtractor = Pattern.compile("while\\(1\\);/\\*(.*)\\*/");
	}

	public Object getTicketIndex() {
		// Pull the index
		logger.debug("Pulling the #define3 ticket index");
		ResponseEntity<String> response = restTemplate.getForEntity(repositoryUrl, String.class);
			
		return extractParsedResponse(response);
	}	

	
	@SuppressWarnings("unchecked")
	public Ticket readTicket(Map<String, Object> parsedTicketEntry) {
		TicketBuilder builder = ticketBuilderFactory.newBuilder();
		
		String href = (String) parsedTicketEntry.get("href");
		builder.setTicketReference(href).addSummary((String) parsedTicketEntry.get("summary"));
		
		ResponseEntity<String> response = restTemplate.getForEntity(href + "?columns=log,description,product,component,milestone", String.class);
		Map<String, Object> ticketResponse = (Map<String, Object>)extractParsedResponse(response);
		
		builder.addDescription((String) ticketResponse.get("description"));

		List<Object> logEntries = (List<Object>)ticketResponse.get("log");

		for (Object rawLogEntry : logEntries)
		{
			Map<String, Object> logEntry = (Map<String, Object>)rawLogEntry;
			if ("comment".equals(logEntry.get("field")) && logEntry.get("old") == null) {
				// Process comments added through #define
				builder.addLogEntry((String) logEntry.get("new"));
				logger.debug("log for " + href + " -> " + (String) logEntry.get("new"));
			}
		}
		
		builder.setMilestone(getInteriorNode(ticketResponse, Arrays.asList("milestone", "name")));
		builder.setProduct(getInteriorNode(ticketResponse, Arrays.asList("product", "name")));
		builder.setComponent(getInteriorNode(ticketResponse, Arrays.asList("component", "name")));
		
		Ticket t = builder.createTicketInstance();
		
		return t;
	}
	
	// Parse a response from the #define3 data source
	private Object extractParsedResponse(ResponseEntity<String> response) {
		Object parsedResponse = null;
		
		Matcher m = responseExtractor.matcher(response.getBody());
		if (m.matches()) {
			String cleanResponse = m.group(1);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				parsedResponse = mapper.readValue(cleanResponse, Object.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return parsedResponse;
	}
	
	
	@SuppressWarnings("unchecked")
	private String getInteriorNode(Map<String, Object> tree, List<String> path) {
		if (tree == null || path == null)
			return null;
		else if (path.size() == 1)
			return ((String)tree.get(path.get(0)));
		else
			return getInteriorNode((Map<String, Object>)tree.get(path.get(0)), path.subList(1, path.size()));
	}
}
