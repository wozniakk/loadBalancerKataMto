package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private int capacity;
	public double currentLoadPecentage;
	
	public Server(int capacity) {
		this.capacity = capacity;
	}

	public Server withCapacity(int i) {
		return this;
	}

	public Matcher<? super String> contains(Vm vm) {
		// TODO Auto-generated method stub
		return null;
	}

}
