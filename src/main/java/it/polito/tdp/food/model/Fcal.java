package it.polito.tdp.food.model;

public class Fcal implements Comparable<Fcal>{

	Food f;
	double cal;
	public Fcal(Food f, double d) {
		super();
		this.f = f;
		this.cal = d;
	}
	public Food getF() {
		return f;
	}
	public void setF(Food f) {
		this.f = f;
	}
	public double getCal() {
		return cal;
	}
	public void setCal(double cal) {
		this.cal = cal;
	}
	@Override
	public String toString() {
		return f.getDisplay_name()+", cal=" + cal;
	}
	@Override
	public int compareTo(Fcal o) {
		
		return -(int)(this.cal-o.getCal());
	}
	
	
	
}
