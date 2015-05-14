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
	public void oneServer_zeroVms_serverStaysEmpty() {
		
		Server server = a(server().withCapacity(1));
		
		balance(aListOfServersWith(server), anEmptyListOfVms());
		
		assertThat(server, hasLoadPercentage(0.0d));
		
	}

	private void balance(Object aListOfServersWith, Object anEmptyListOfVms) {
		
	}

	private Matcher<? super Server> hasLoadPercentage(double d) {
		return null;
	}

	private Object anEmptyListOfVms() {
		return null;
	}

	private Object aListOfServersWith(Server server) {
		return null;
	}

	private Server a(ServerBuilder withCapacity) {
		return null;
	}

	private ServerBuilder server() {
		return null;
	}
	
}
