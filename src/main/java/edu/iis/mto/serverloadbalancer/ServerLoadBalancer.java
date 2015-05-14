package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] aListOfServersContains, Vm[] aListOfVms) {
		if (aListOfVms.length > 0) {
			aListOfServersContains[0].currentLoadPecentage = (double) aListOfVms[0].size
					/ (double) aListOfServersContains[0].capacity * 100.0d;
		}
	}

}
