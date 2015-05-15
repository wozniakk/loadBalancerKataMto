package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentage;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;

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

	@Test
	public void oneServer_oneVm_fillsAllServerSpace() {
		
		Server server = a(server().withCapacity(1));
		Vm vm = a(vm().withSize(1));
		balance(aListOfServersWith(server), aListOfVmsWith(vm));
		
		assertThat(server, hasLoadPercentage(100.0d));
		assertThat("server should contain vm", server.contains(vm));
		
	}
	
	@Test
	public void oneServer_oneVm_fillsHalfOfServerSpace() {
		
		Server server = a(server().withCapacity(2));
		Vm vm = a(vm().withSize(1));
		balance(aListOfServersWith(server), aListOfVmsWith(vm));
		
		assertThat(server, hasLoadPercentage(50.0d));
		assertThat("server should contain vm", server.contains(vm));
		
	}
	
	@Test
	public void oneServer_fewVms_shouldFillOntHeServer() {
		
		Server server = a(server().withCapacity(100));
		Vm firstVM = a(vm().withSize(1));
		Vm secondVm = a(vm().withSize(1));
		balance(aListOfServersWith(server), aListOfVmsWith(firstVM, secondVm));

		assertThat(server, hasVmsCountOf(2));
		assertThat("server should contain vm", server.contains(firstVM));
		assertThat("server should contain vm", server.contains(secondVm));
		
	}
	
	private Matcher<? super Server> hasVmsCountOf(int expectedCount) {
		return new ServerVmsCountMatcher(expectedCount);
	}

	private Vm a(VmBuilder builder) {
		return builder.build();
	}

	private Vm[] aListOfVmsWith(Vm... vm) {
		return vm;
	}

	private void balance(Server[] aListOfServersWith, Vm[] anEmptyListOfVms) {
		new ServerLoadBalancer().balance(aListOfServersWith, anEmptyListOfVms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[]{};
	}

	private Server[] aListOfServersWith(Server server) {
		return new Server[]{ server };
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}
	
}
