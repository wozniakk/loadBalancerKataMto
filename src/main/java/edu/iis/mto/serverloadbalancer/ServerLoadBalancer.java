package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] aListOfServers, Vm[] aListOfVms) {
		if (aListOfVms.length > 0) {
			aListOfServers[0].currentLoadPecentage = (double) aListOfVms[0].size
					/ (double) aListOfServers[0].capacity * 100.0d;
		}
	}

}
