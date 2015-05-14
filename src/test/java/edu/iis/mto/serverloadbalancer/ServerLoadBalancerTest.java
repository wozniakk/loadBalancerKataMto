package edu.iis.mto.serverloadbalancer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {

	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}
	
	@Test
	public void oneServer_zeroVms_serverShouldStayEmpty() {
		
		Server server = a(server().withCapacity(1));
	
		balance(aListOfServersContains(server), anEmptyListOfVms());
		
		assertThat(server, hasLoadPercentage(0.0d));
		
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private void balance(Server[] aListOfServersContains, Vm[] anEmptyListOfVms) {
		new ServerLoadBalancer().balance(aListOfServersContains, anEmptyListOfVms);
	}

	private Matcher<? super Server> hasLoadPercentage(double expectedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[]{};
	}

	private Server[] aListOfServersContains(Server server) {
		return new Server[]{ server }; 
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}
	
}
