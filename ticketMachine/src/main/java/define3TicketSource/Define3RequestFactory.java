package define3TicketSource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.http.client.CommonsClientHttpRequestFactory;

public class Define3RequestFactory extends CommonsClientHttpRequestFactory {
	
	// TODO: Update to httpclient 4, probably easiest with shift to Spring 3.1
	Define3RequestFactory(String username, String password, int timeoutInMilliseconds, String proxyHost, Integer proxyPort)
	{
		super(makeClient(username, password, proxyHost, proxyPort));
		
		this.setReadTimeout(timeoutInMilliseconds);
	}
	
	static private HttpClient makeClient(String username, String password, String proxyHost, Integer proxyPort) {
		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());

		httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials( username, password));
		
        if (proxyHost != null && !proxyHost.isEmpty()) {
        	httpClient.getHostConfiguration().setProxy(proxyHost, proxyPort);
        }
        
        return httpClient;
	}
}
