package ticketMachine;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ticketApi.TicketSource;
import ticketWriter.TicketWriter;


public class TicketMachine {
	public static void main(String[] args) {
		AbstractApplicationContext appContext =
				new ClassPathXmlApplicationContext(new String[] {"ticketMachine/ticketMachine.xml", "ticketWriter/ticketWriter.xml", "define3TicketSource/define3TicketSource.xml"});
			
		appContext.registerShutdownHook();
		
		TicketSource s = appContext.getBean("ticketSource", TicketSource.class);
		TicketWriter t = appContext.getBean("ticketWriter", TicketWriter.class);

		// Transfer all the tickets from the repository to the sink
		s.fill(t);
		
		// And close the sink
		t.closeOutput();
	}
}
