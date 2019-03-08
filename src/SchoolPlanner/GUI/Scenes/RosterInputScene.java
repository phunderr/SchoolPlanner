package SchoolPlanner.GUI.Scenes;

import SchoolPlanner.Data.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;


/**
 * @Author Luke Taylor & Jelmer Surewaard & Pascal Holthuijsen & Stijn van Berkel
 * creates the scene for "Roster Input" tab in the main application.
 */

public class RosterInputScene {

    private FileReader fr = new FileReader();
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();
    private ObservableList<ClassName> classNameObservableList = FXCollections.observableArrayList();
    private ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
    private ObservableList<Classroom> classroomObservableList = FXCollections.observableArrayList();
    private FileReader fileReader;

    /**
     * @return the RosterInput borderpane.
     */

    public BorderPane rosterInputScene(){
        fileReader = new FileReader();

        BorderPane borderPane = new BorderPane();

        TableView tableView = new TableView();

        TableColumn teacherCollumn = new TableColumn("Teacher");
        TableColumn teacherNamecollumn = new TableColumn("Name");
        teacherNamecollumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
        TableColumn teacherPopularityCollumn = new TableColumn("Popularity");
        teacherPopularityCollumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("popularity"));
        teacherCollumn.getColumns().addAll(teacherNamecollumn, teacherPopularityCollumn);
        teacherNamecollumn.setPrefWidth(125);
        teacherPopularityCollumn.setPrefWidth(125);
        TableColumn subjectCollumn = new TableColumn("Subject");
        TableColumn subjectNameCollumn = new TableColumn("Name");
        subjectNameCollumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
        TableColumn subjectPopularityCollumn = new TableColumn("Popularity");
        subjectPopularityCollumn.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("popularity"));
        subjectCollumn.getColumns().addAll(subjectNameCollumn, subjectPopularityCollumn);
        subjectNameCollumn.setPrefWidth(125);
        subjectPopularityCollumn.setPrefWidth(125);
        TableColumn classroomCollumn = new TableColumn("Classroom");
        TableColumn classroomNameCollumn = new TableColumn("Name");
        classroomNameCollumn.setCellValueFactory(new PropertyValueFactory<ClassName, String>("name"));
        classroomCollumn.getColumns().addAll(classroomNameCollumn);
        classroomNameCollumn.setPrefWidth(250);
        TableColumn classCollumn = new TableColumn("ClassName");
        TableColumn classNameCollumn = new TableColumn("Name");
        classNameCollumn.setCellValueFactory(new PropertyValueFactory<ClassName, String>("name"));
        TableColumn classNumberOfStudents = new TableColumn("Number of students");
        classNumberOfStudents.setCellValueFactory(new PropertyValueFactory<ClassName, Integer>("numberOfStudents"));
        classCollumn.getColumns().addAll(classNameCollumn, classNumberOfStudents);
        classNameCollumn.setPrefWidth(125);
        classNumberOfStudents.setPrefWidth(125);

        //todo Textfile with pathnames

        teacherNamecollumn.setCellValueFactory(new PropertyValueFactory<Teacher,String>("Pascal"));
        tableView.setItems(teacherObservableList);
        tableView.setItems(subjectObservableList);
        tableView.setItems(classNameObservableList);
        tableView.setItems(classroomObservableList);

        tableView.getColumns().addAll(teacherCollumn,subjectCollumn,classroomCollumn,classCollumn);
        tableView.setMaxWidth(1000);

        Button addButton = new Button("ADD");
        addButton.setFont(new Font(60));
        addButton.setPadding(new Insets(20,50,20,50));

        addButton.setOnAction(event -> {
            addButton();
        });

        Button changeButton = new Button("CHANGE");
        changeButton.setFont(new Font(60));
        changeButton.setPadding(new Insets(20,20,20,20));

        borderPane.setCenter(tableView);
        borderPane.setLeft(addButton);
        borderPane.setRight(changeButton);

        return borderPane;
    }

    public void addButton(){

        Stage stage = new Stage();
        stage.setTitle("Add");

        BorderPane borderPane = new BorderPane();
        borderPane.setMinHeight(100);

        Button teacherButton = new Button("Teacher");
        Button subjectButton = new Button("Subject");
        Button classButton = new Button("ClassName");
        Button classroomButton = new Button("Classroom");

        HBox buttonHbox = new HBox();
        buttonHbox.getChildren().addAll(teacherButton, subjectButton, classButton, classroomButton);
        buttonHbox.setSpacing(20);
        borderPane.setTop(buttonHbox);

        teacherButton.setOnAction(event -> {

            Label nameLabel = new Label("Name:");
            TextField nameTextField = new TextField();

            Label popularityLabel = new Label("Popularity:");
            TextField popularityTextField = new TextField();

            HBox nameHbox = new HBox();
            nameHbox.getChildren().addAll(nameLabel, nameTextField);

            HBox popularityHbox = new HBox();
            popularityHbox.getChildren().addAll(popularityLabel, popularityTextField);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(nameHbox, popularityHbox);

            Button addButton = new Button("Add");

            borderPane.setCenter(vBox);
            borderPane.setBottom(addButton);

            addButton.setOnAction(event1 -> {
                try {
                    int text = Integer.parseInt(popularityTextField.getText());
                    Teacher teacher = new Teacher(nameTextField.getText(), text);
                    fr.writeObject(teacher, "src/objectFile/teacher/" + nameTextField.getText() + ".dat");
                    fileReader.addToFile("src/TextFile/TeacherPathNames", "src/objectFile/teacher/" + nameTextField.getText() + ".dat");
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });



        });

        subjectButton.setOnAction(event -> {
            Label nameLabel = new Label("Name:");
            TextField nameTextField = new TextField();

            Label popularityLabel = new Label("Popularity:");
            TextField popularityTextField = new TextField();

            HBox nameHbox = new HBox();
            nameHbox.getChildren().addAll(nameLabel, nameTextField);

            HBox popularityHbox = new HBox();
            popularityHbox.getChildren().addAll(popularityLabel, popularityTextField);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(nameHbox, popularityHbox);

            Button addButton = new Button("Add");

            borderPane.setCenter(vBox);
            borderPane.setBottom(addButton);

            addButton.setOnAction(event1 -> {
                try {
                    int text = Integer.parseInt(popularityTextField.getText());
                    Subject subject = new Subject(nameTextField.getText(), text);
                    fr.writeObject(subject, "src/objectFile/subject/" + nameTextField.getText() + ".dat");
                    subjectObservableList.add(subject);
                    stage.close();
                    subjectData.add(subject);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        });

        classButton.setOnAction(event -> {
            Label nameLabel = new Label("Name:");
            TextField nameTextField = new TextField();

            Label numberOfStudentsLabel = new Label("Number of Students:");
            TextField numberOfStudentsTextField = new TextField();

            HBox nameHbox = new HBox();
            nameHbox.getChildren().addAll(nameLabel, nameTextField);

            HBox numberOfStudentsHbox = new HBox();
            numberOfStudentsHbox.getChildren().addAll(numberOfStudentsLabel, numberOfStudentsTextField);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(nameHbox, numberOfStudentsHbox);

            Button addButton = new Button("Add");

            borderPane.setCenter(vBox);
            borderPane.setBottom(addButton);

            addButton.setOnAction(event1 -> {
                try {
                    int numberOfStudents = 0;
                    if(!numberOfStudentsTextField.getText().equals("")) {
                        numberOfStudents = Integer.parseInt(numberOfStudentsTextField.getText());
                        ClassName className = new ClassName(nameTextField.getText(), numberOfStudents);
                        fr.writeObject(className, "src/objectFile/className/" + nameTextField.getText() + ".dat");
                        classNameObservableList.add(className);
                        stage.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            });




        classroomButton.setOnAction(event -> {
            Label nameLabel = new Label("Name:");
            TextField nameTextField = new TextField();

            HBox nameHbox = new HBox();
            nameHbox.getChildren().addAll(nameLabel, nameTextField);

            VBox vBox = new VBox();
            vBox.getChildren().addAll(nameHbox);

            Button addButton = new Button("Add");

            borderPane.setCenter(vBox);
            borderPane.setBottom(addButton);

            addButton.setOnAction(event1 -> {
                try {
                    Classroom classroom = new Classroom(nameTextField.getText());
                    fr.writeObject(classroom, "src/objectFile/classroom/" + nameTextField.getText() + ".dat");
                    classroomObservableList.add(classroom);
                    stage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        });

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}

