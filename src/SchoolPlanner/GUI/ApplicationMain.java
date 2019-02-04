package SchoolPlanner.GUI;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ApplicationMain extends javafx.application.Application {


    public static void main(String[] args) {
        launch(ApplicationMain.class);

    }
    public void start(Stage stage){
        BorderPane testPane = new BorderPane();
        Scene testScene = new Scene(testPane);


        stage.setScene(testScene);
        stage.show();
    }
}
