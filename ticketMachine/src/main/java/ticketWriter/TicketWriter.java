package ticketWriter;

import java.io.IOException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ticketApi.Ticket;
import ticketApi.TicketSink;
import ticketApi.TicketSource;

@Component
public class TicketWriter implements TicketSink {
	
	TicketSource ticketSource;
	private ObjectMapper mapper;
	private JsonGenerator generator;
	
	@Autowired
	void setTicketSource(TicketSource aSource) {
		ticketSource = aSource;
	}
	
	// Insert a ticket into the writer
	public void addTicket(Ticket ticket) {
		if (generator == null) {
			startOutput();
		}
		
		if (generator != null) {
			try {
				mapper.writeValue(generator, ticket);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Close the output
	public void closeOutput() {
		try {
			generator.writeEndArray();
			generator.close();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Initialise the output
	private void startOutput() {
		mapper = new ObjectMapper();
		JsonFactory f = new JsonFactory();
		
		try {
			generator = f.createJsonGenerator(System.out);
			generator.useDefaultPrettyPrinter();
			generator.writeStartArray();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			mapper = null;
			generator = null;
		} catch (IOException e) {
			e.printStackTrace();
			mapper = null;
			generator = null;
		}
	}
}
