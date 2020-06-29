package it.polito.tdp.food.model;

import java.time.LocalTime;


public class Event implements Comparable<Event> {

	public enum EventType{
		LAVORA, NOLAVORA
	}
	
	int f;
	EventType type;
	int minuti;
	LocalTime timeFine;
	int postazionek;
	
	
	public Event(int f, EventType type, int minuti, LocalTime timeFine, int postazionek) {
		super();
		this.f = f;
		this.type = type;
		this.minuti = minuti;
		this.timeFine= timeFine;
		this.postazionek = postazionek;
	}


	public int getF() {
		return f;
	}


	public void setF(int f) {
		this.f = f;
	}


	public EventType getType() {
		return type;
	}


	public void setType(EventType type) {
		this.type = type;
	}


	public int getMinuti() {
		return minuti;
	}


	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}


	public LocalTime getTimeFine() {
		return timeFine;
	}


	public void setTimeInizio(LocalTime timeFine) {
		this.timeFine = timeFine;
	}


	public int getPostazionek() {
		return postazionek;
	}


	public void setPostazionek(int postazionek) {
		this.postazionek = postazionek;
	}
	
	public int compareTo(Event other) {
		return this.timeFine.compareTo(other.timeFine);
	}
	
}
