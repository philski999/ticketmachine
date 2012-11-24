package ticketWriter;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TicketMachine {
	public static void main(String[] args) {
		AbstractApplicationContext appContext =
				new ClassPathXmlApplicationContext(new String[] {"ticketWriter/ticketMachine.xml", "ticketWriter/ticketWriter.xml", "define3TicketSource/define3TicketSource.xml"});
			
		appContext.registerShutdownHook();
		
		TicketWriter t = appContext.getBean("ticketWriter", TicketWriter.class);
		t.writeTickets();
	}
}
