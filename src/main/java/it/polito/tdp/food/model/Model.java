package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDAO;



public class Model {
	public SimpleWeightedGraph<Integer, DefaultWeightedEdge> g;
	Map<Integer, Food> vertici=new HashMap<>();
	Map<Integer, Food> tendina=new HashMap<>();
	FoodDAO dao=new FoodDAO();
	
	public Model() {
		dao=new FoodDAO();
	}
	
	public Collection<Food> creaGrefo(int port) {
		g=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		vertici=new HashMap<>();
		for(Food f:dao.listVertici(port)) {
			vertici.put(f.getFood_code(), f);
			g.addVertex(f.getFood_code());
		}
		int archi=0;
		for(Adiacenza a: dao.listAdiacenze(port)) {
			if(vertici.get(a.getF1())!=null && vertici.get(a.getF2())!=null) {
				tendina.put(a.getF1(), vertici.get(a.getF1()));
				tendina.put(a.getF2(), vertici.get(a.getF2()));
				Graphs.addEdgeWithVertices(g, a.getF1(), a.getF2(), a.getMedia());
				archi++;
			}
		}
		System.out.println("V= "+vertici.size());
		System.out.println("A="+archi);
		return tendina.values();
			
	}
	
	public List<Fcal> dammiVicini(int i){
		List<Fcal> migliori=new ArrayList<>();
		int v=Graphs.neighborListOf(g, i).size();
		for(Integer n: Graphs.neighborListOf(g, i)) {
			migliori.add(new Fcal(vertici.get(n), g.getEdgeWeight(g.getEdge(i, n))));
		}
		Collections.sort(migliori);
		return migliori;
	}
	
	public List<Integer> simula(int i, int k) {
		Simulator sim=new Simulator(g,i, k);
		sim.run();
		return sim.dammiLavorati();
	}
}
