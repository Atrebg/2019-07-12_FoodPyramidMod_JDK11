package it.polito.tdp.food;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Fcal;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//controller turno A --> switchare al branch master_turnoB per turno B

public class FXMLController {
	
	private Model model;
	List<Food> cibi=new ArrayList<>();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPorzioni;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnAnalisi;

    @FXML
    private Button btnCalorie;

    @FXML
    private Button btnSimula;

    @FXML
    private ComboBox<Food> boxFood;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalorie(ActionEvent event) {
    	int i=0;
    	txtResult.clear();
    	List<Fcal> migliori=new ArrayList<>(model.dammiVicini(boxFood.getValue().getFood_code()));
    	
    	while(i<5) {
    		txtResult.appendText(migliori.get(i).toString()+"\n");
    		i++;
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	cibi=new ArrayList<>(model.creaGrefo(Integer.parseInt(txtPorzioni.getText())));
    	boxFood.getItems().clear();
    	boxFood.getItems().addAll(cibi);
    	txtResult.clear();
    	txtResult.appendText("Grafo creato");
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	for(Integer i: model.simula(boxFood.getValue().getFood_code(), Integer.parseInt(txtK.getText()))) {
    	txtResult.appendText(""+i+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
