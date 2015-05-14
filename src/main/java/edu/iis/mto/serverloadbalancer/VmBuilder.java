package edu.iis.mto.serverloadbalancer;

public class VmBuilder {

	private int size;
	
	public VmBuilder withSize(int size) {
		this.size = size;
		return this;
	}

	public Vm build() {
		return new Vm(size);
	}

	public static VmBuilder vm() {
		return new VmBuilder();
	}
	
}
