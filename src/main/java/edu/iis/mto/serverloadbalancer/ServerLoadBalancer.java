package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] aListOfServers, Vm[] aListOfVms) {
		for(Vm vm : aListOfVms){
			aListOfServers[0].addVm(vm);
		}
	}

}
