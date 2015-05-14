package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] aListOfServersWith, Vm[] anEmptyListOfVms) {
		if (anEmptyListOfVms.length > 0)
			aListOfServersWith[0].currentLoadPecentage = 100.0d;
	}

}
