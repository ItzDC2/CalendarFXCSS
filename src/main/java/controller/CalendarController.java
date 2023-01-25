package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import customComponents.MonthCalendar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CalendarController implements Initializable {

	private IntegerProperty yearProperty = new SimpleIntegerProperty(LocalDate.now().getYear());
	
    @FXML
    private Button forwardButton;

    @FXML
    private Button backButton;
	
    @FXML
    private GridPane view;

    @FXML
    private BorderPane bp;
    
    @FXML
    private Label yearLabel;

	public CalendarController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalendarioView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch(IOException ex) {
			throw new RuntimeException(ex);
		}
 	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		//bindings
		backButton.disableProperty().bind(yearProperty.lessThanOrEqualTo(1970));
		forwardButton.disableProperty().bind(yearProperty.isEqualTo(LocalDate.now().getYear()));
		
		yearLabel.textProperty().bind(yearProperty.asString());
		
		//actions
		init();
		
	}
	
	private void init() {
		
		List<MonthCalendar> calendars = view.getChildren().stream()
				.filter(nodo -> nodo instanceof MonthCalendar)
				.map(nodo -> (MonthCalendar) nodo)
				.collect(Collectors.toList());

		calendars.stream().forEach(i -> i.yearPropertyProperty().bind(yearProperty));

	}
	
    @FXML
    private void onBackAction(ActionEvent event) {
    	yearProperty.set(yearProperty.get() - 1);
    }

    @FXML
    private void onForwardAction(ActionEvent event) {
    	yearProperty.set(yearProperty.get() + 1);
    }
    
    public BorderPane getBp() {
		return bp;
	}
	
}