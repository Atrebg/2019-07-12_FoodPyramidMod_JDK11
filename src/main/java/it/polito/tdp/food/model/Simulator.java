package it.polito.tdp.food.model;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.model.Event.EventType;

public class Simulator {

	
	private PriorityQueue<Event> queue = new PriorityQueue<>();
	int K;
	public SimpleWeightedGraph<Integer, DefaultWeightedEdge> g;
	int inizio;
	List<Integer> lavorati=new ArrayList<>();
	
	public Simulator(SimpleWeightedGraph<Integer, DefaultWeightedEdge> g2, int inizio, int K) {
		
		this.K=K;
		this.g=g2;
		this.inizio=inizio;
		
	}
	
	public List<Integer> dammiLavorati(){
		return lavorati;
	}
	
	public void run() {
		this.queue.clear();
		LocalTime oraInizio=LocalTime.of(8, 0);
		int post=0;
		
		for(Fcal2 fc:dammiVicini(inizio)) {
			if(post>=K) {
				break;
			}
			post++;
			LocalTime oraFine=oraInizio.plus((int)fc.getD(), ChronoUnit.MINUTES);
			Event e=new Event(fc.getF(), EventType.LAVORA, (int)fc.getD(),oraFine, post);
			this.queue.add(e);
			lavorati.add(fc.getF());
		
		}
		while(!this.queue.isEmpty()) {
			Event e=this.queue.poll();
			processEvent(e);
		}
	}
	
	
	
	private void processEvent(Event e) {
		switch(e.getType()) {
		
		case LAVORA:
			if(dammiVicini(e.getF()).isEmpty()) {
				Event e1=new Event(0, EventType.NOLAVORA,0,e.getTimeFine() ,e.getPostazionek());
				queue.add(e1);
				break;
			}else {
				int flag=0;
				for(Fcal2 fc:dammiVicini(inizio)) {
					if(!lavorati.contains(fc.getF())) {
						Event e1=new Event(fc.getF(), EventType.LAVORA, (int)fc.getD(),e.getTimeFine().plus((int)fc.getD(),ChronoUnit.MINUTES),e.getPostazionek());
						queue.add(e1);
						lavorati.add(fc.getF());
						flag=1;
						break;
					}
				}
				if(flag==0) {
					Event e1=new Event(0, EventType.NOLAVORA,0,e.getTimeFine() ,e.getPostazionek());
					queue.add(e1);
					break;
				}
			}
			
			break;
		case NOLAVORA:
			System.out.println("NOLAVORA");
		}
		
	}

	public List<Fcal2> dammiVicini(int i){
		List<Fcal2> migliori=new ArrayList<>();
		for(Integer n: Graphs.neighborListOf(g, i)) {
			migliori.add(new Fcal2(n, g.getEdgeWeight(g.getEdge(i, n))));
		}
		Collections.sort(migliori);
		return migliori;
	}
	
}
