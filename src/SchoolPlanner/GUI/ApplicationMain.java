package SchoolPlanner.GUI;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ApplicationMain extends javafx.application.Application {

    private BorderPane mainPane;
    private GridPane mainGrid;


    public static void main(String[] args) {
        launch(ApplicationMain.class);

    }
    public void start(Stage stage){
        initialize(stage);
        gridEditor();
    }

    public void initialize(Stage stage){
        this.mainPane = new BorderPane();
        this.mainGrid = new GridPane();
        mainPane.setCenter(mainGrid);
        Scene testScene = new Scene(mainPane);
        stage.setScene(testScene);
        stage.show();
    }

    public void gridEditor(){
        mainGrid.add(new Label("skrrr"),0,0);
    }
}
