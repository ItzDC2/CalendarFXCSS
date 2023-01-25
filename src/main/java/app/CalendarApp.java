package app;

import controller.CalendarController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CalendarApp extends Application {

	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		CalendarApp.primaryStage = primaryStage;
		primaryStage.setScene(new Scene(new CalendarController().getBp()));
		primaryStage.setTitle("CalendarApp");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/calendar-64x64.png")));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
