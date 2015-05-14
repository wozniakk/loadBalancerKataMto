package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;

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

	@Test
	public void oneServer_oneVm_fillsAllServerCapacity() {
		
		Server server = a(server().withCapacity(1));
		
		Vm vm = a(vm().withSize(1));
		
		balance(aListOfServersContains(server), aListOfVmsWith(vm));
		
		assertThat(server, hasLoadPercentage(100.0d));
		assertThat("server should contains vm", server.contains(vm));
		
	}
	
	@Test
	public void oneServer_oneVm_shouldFillHalfOfTheServerSpace() {
		
		Server server = a(server().withCapacity(2));
		Vm vm = a(vm().withSize(1));
		balance(aListOfServersContains(server), aListOfVmsWith(vm));
		
		assertThat(server, hasLoadPercentage(50.0d));
		assertThat("server should contsain vm", server.contains(vm));
		
	}
	
	private Vm[] aListOfVmsWith(Vm vm) {
		return new Vm[]{ vm };
	}

	private Vm a(VmBuilder builder) {
		return builder.build();
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
