package customComponents;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MonthCalendar extends GridPane implements Initializable {

	// model
	private IntegerProperty monthProperty = new SimpleIntegerProperty(LocalDate.now().getMonthValue());
	private IntegerProperty yearProperty = new SimpleIntegerProperty(LocalDate.now().getYear());

	// view

	@FXML
	private Label mesLabel;

	@FXML
	private GridPane view;

	public MonthCalendar() {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonthCalendarView.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// listeners
		yearProperty.addListener(this::onChangeYear);
		monthProperty.addListener(this::onChangeMonth);

		// actions
		cargarDiasDelMes();

	}

	private void onChangeMonth(ObservableValue<? extends Number> o, Number ov, Number nv) {
		switch (nv.intValue()) {
		case 1:
			mesLabel.setText("Enero");
			break;
		case 2:
			mesLabel.setText("Febrero");
			break;
		case 3:
			mesLabel.setText("Marzo");
			break;
		case 4:
			mesLabel.setText("Abril");
			break;
		case 5:
			mesLabel.setText("Mayo");
			break;
		case 6:
			mesLabel.setText("Junio");
			break;
		case 7:
			mesLabel.setText("Julio");
			break;
		case 8:
			mesLabel.setText("Agosto");
			break;
		case 9:
			mesLabel.setText("Septiembre");
			break;
		case 10:
			mesLabel.setText("Octubre");
			break;
		case 11:
			mesLabel.setText("Noviembre");
			break;
		case 12:
			mesLabel.setText("Diciembre");
			break;
		}
		cargarDiasDelMes();
	}

	private void onChangeYear(ObservableValue<? extends Number> o, Number ov, Number nv) {
		cargarDiasDelMes();
	}

	private void cargarDiasDelMes() {
		limpiarLabels();
		int diasTotales = YearMonth.of(getYearProperty(), getMonthProperty()).lengthOfMonth();

		int row = 2;
		for (int i = 1; i <= diasTotales; i++) {

			LocalDate ahora = LocalDate.of(getYearProperty(), getMonthProperty(), i);
			
			switch (ahora.getDayOfWeek()) {
			case MONDAY:
				getLabelPorCoordenada(row, 0).setText(ahora.getDayOfMonth() + "");
				setToday(getLabelPorCoordenada(row, 0), ahora);
				break;
			case TUESDAY:
				getLabelPorCoordenada(row, 1).setText(ahora.getDayOfMonth() + "");
				setToday(getLabelPorCoordenada(row, 1), ahora);
				break;
			case WEDNESDAY:
				getLabelPorCoordenada(row, 2).setText(ahora.getDayOfMonth() + "");
				setToday(getLabelPorCoordenada(row, 2), ahora);
				break;
			case THURSDAY:
				getLabelPorCoordenada(row, 3).setText(ahora.getDayOfMonth() + "");
				setToday(getLabelPorCoordenada(row, 3), ahora);
				break;
			case FRIDAY:
				getLabelPorCoordenada(row, 4).setText(ahora.getDayOfMonth() + "");
				setToday(getLabelPorCoordenada(row, 4), ahora);
				break;
			case SATURDAY:
				getLabelPorCoordenada(row, 5).setText(ahora.getDayOfMonth() + "");
				setToday(getLabelPorCoordenada(row, 5), ahora);
				break;
			case SUNDAY:
				getLabelPorCoordenada(row, 6).setText(ahora.getDayOfMonth() + "");
				setToday(getLabelPorCoordenada(row, 6), ahora);
				row++;
				break;
			}
		}

	}
	
	private void setToday(Label l, LocalDate ahora) {
		if(ahora.getDayOfWeek() == LocalDate.now().getDayOfWeek() && ahora.getDayOfMonth() == LocalDate.now().getDayOfMonth() && ahora.getMonth() == LocalDate.now().getMonth()
				&& LocalDate.now().getYear() == ahora.getYear())
			l.getStyleClass().add("today");
	}
	
	private void limpiarLabels() {
		view.getChildren().stream()
			.filter(nodo -> "diaLabel".equals(nodo.getId()))
			.map(i -> (Label) i)
			.forEach(label -> {
				label.setText("");
				label.getStyleClass().remove("today");
			});
	}

	private Label getLabelPorCoordenada(int row, int col) {
		
		return view.getChildren().stream()
				.filter(nodo -> nodo instanceof Label)
				.filter(nodo -> "diaLabel".equals(nodo.getId()))
				.map(nodo -> (Label) nodo)
				.filter(label -> GridPane.getRowIndex(label) == row && GridPane.getColumnIndex(label) == col)
				.findFirst().get();
	}

	public GridPane getView() {
		return view;
	}

	public final IntegerProperty monthPropertyProperty() {
		return this.monthProperty;
	}

	public final int getMonthProperty() {
		return this.monthPropertyProperty().get();
	}

	public final void setMonthProperty(final int monthProperty) {
		this.monthPropertyProperty().set(monthProperty);
	}

	public final IntegerProperty yearPropertyProperty() {
		return this.yearProperty;
	}

	public final int getYearProperty() {
		return this.yearPropertyProperty().get();
	}

	public final void setYearProperty(final int yearProperty) {
		this.yearPropertyProperty().set(yearProperty);
	}

}
