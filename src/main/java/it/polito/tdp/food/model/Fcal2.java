package it.polito.tdp.food.model;

public class Fcal2 implements Comparable<Fcal2>{

	int f;
	double d;
	
	
	public Fcal2(int f, double d) {
		
		this.f=f;
		this.d=d;
	}

	
	
	public int getF() {
		return f;
	}



	public void setF(int f) {
		this.f = f;
	}



	public double getD() {
		return d;
	}



	public void setD(double d) {
		this.d = d;
	}



	public int compareTo(Fcal2 o) {
		
		return -(int)(this.d-o.getD());
	}
	
	
}
