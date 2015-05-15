package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] aListOfServers, Vm[] aListOfVms) {
		for (Vm vm : aListOfVms) {
			Server lessLoaded = null;
			for(Server server : aListOfServers){
				if(lessLoaded == null || lessLoaded.currentLoadPecentage > server.currentLoadPecentage){
					lessLoaded = server;
				}
			}
			lessLoaded.addVm(vm);
		}
	}

}
