package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentage;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasVmsCountOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

	@Test
	public void twoServers_oneVm_shouldAssignToLessLoadedServer() {
		
		Server firstServer = a(server().withCapacity(10).withCurrentLoadOf(10.0d));
		Server secondServer = a(server().withCapacity(10).withCurrentLoadOf(50.0d));
		
		Vm vm = a(vm().withSize(1));
		
		balance(aListOfServersWith(firstServer, secondServer), aListOfVmsWith(vm));
		assertThat("less loaded server should contain vm", firstServer.contains(vm));
		
	}
	
	@Test
	public void oneServer_tooLoadedToAddNewVm() {
		
		Server server = a(server().withCapacity(10).withCurrentLoadOf(100.0d));
		Vm vm = a(vm().withSize(20));
		
		balance(aListOfServersWith(server), aListOfVmsWith(vm));
		assertThat("server should not contain vm", !server.contains(vm));
		
	}
	
	@Test
	public void fewServers_fewVms_shouldAssignEquallyToServers() {
		
		Server server1 = a(server().withCapacity(10));
        Server server2 = a(server().withCapacity(20));
        
        Vm vm1 = a(vm().withSize(2));
        Vm vm2 = a(vm().withSize(4));
        Vm vm3 = a(vm().withSize(8));
        
        balance(aListOfServersWith(server1, server2), aListOfVmsWith(vm1, vm2, vm3));
        
        assertThat("The server 1 should contain the vm 1", server1.contains(vm1));
        assertThat("The server 2 should contain the vm 2", server2.contains(vm2));
        assertThat("The server 1 should contain the vm 3", server1.contains(vm3));
        
        assertThat(server1, hasLoadPercentage(60.0d));
        assertThat(server2, hasLoadPercentage(40.0d));
		
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

	private Server[] aListOfServersWith(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}
	
}
