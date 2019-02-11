package SchoolPlanner.GUI;


import javafx.application.Application;
import javafx.geometry.Insets;
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
        mainPane.setBottom(mainGrid);
        Scene testScene = new Scene(mainPane);
        stage.setScene(testScene);
        stage.show();
    }

    public void gridEditor(){
        mainGrid.setVgap(30);
        mainGrid.setHgap(30);
        mainGrid.setGridLinesVisible(true);
        mainGrid.setPadding(new Insets(500,500,500,500));
        mainGrid.setMinWidth(400);
        mainGrid.setMinHeight(400);
        for (int y = 0; y < 4 ; y++) {
            for (int i = 0; i < 4; i++) {
                mainGrid.add(new Label("skrrr" + i), i, y);
            }
        }
    }
}
