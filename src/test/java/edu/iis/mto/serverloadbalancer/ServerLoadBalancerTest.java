package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;

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

	private Vm[] anEmptyListOfVms() {
		return new Vm[]{};
	}

	private Server[] aListOfServersContains(Server server) {
		return new Server[]{ server }; 
	}
	
}
