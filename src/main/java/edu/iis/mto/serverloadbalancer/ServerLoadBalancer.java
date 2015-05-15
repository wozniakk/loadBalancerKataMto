package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

	public void balance(Server[] aListOfServers, Vm[] aListOfVms) {
		for (Vm vm : aListOfVms) {
			List<Server> serversWithEnoughCapacity = new ArrayList<Server>(aListOfServers.length);
			for(Server server : aListOfServers){
				if(server.canFit(vm))
					serversWithEnoughCapacity.add(server);
			}			
			Server lessLoaded = extractLessLoadedServer(serversWithEnoughCapacity);
			if(lessLoaded != null)
				lessLoaded.addVm(vm);
		}
	}

	private Server extractLessLoadedServer(List<Server> servers) {
		Server lessLoaded = null;
		for(Server server : servers){
			if(lessLoaded == null || lessLoaded.currentLoadPecentage > server.currentLoadPecentage){
				lessLoaded = server;
			}
		}
		return lessLoaded;
	}
	
}
