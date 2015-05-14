package edu.iis.mto.serverloadbalancer;

public class Server {

	int capacity;
	public double currentLoadPecentage;
	
	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm vm) {
		return true;
	}

}
