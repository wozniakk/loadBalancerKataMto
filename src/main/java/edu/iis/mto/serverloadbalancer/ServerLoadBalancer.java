package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] aListOfServersContains, Vm[] aListOfVms) {
		if (aListOfVms.length > 0) {
			aListOfServersContains[0].currentLoadPecentage = 100.0d;
		}
	}

}
