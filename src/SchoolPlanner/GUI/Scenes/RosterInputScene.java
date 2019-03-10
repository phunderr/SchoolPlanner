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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


/**
 * @Author Luke Taylor & Jelmer Surewaard & Pascal Holthuijsen & Stijn van Berkel
 * creates the scene for "Roster Input" tab in the main application.
 */

public class RosterInputScene {

    private FileReader fr = new FileReader();
    private ObservableList<Teacher> teacherObservableList = FXCollections.observableArrayList();
    private ObservableList<ClassName> classNameObservableList = FXCollections.observableArrayList();
    private ObservableList<Subject> subjectObservableList = FXCollections.observableArrayList();
    private ObservableList<Classroom> classroomObservableList = FXCollections.observableArrayList();
    private TableView teacherView;
    private TableView classNameView;
    private TableView classroomView;
    private TableView subjectView;
    private Button changeButton;
    private Button deleteButton;

    /**
     * @return the RosterInput borderpane.
     */

    public BorderPane rosterInputScene(){

        BorderPane borderPane = new BorderPane();

        this.teacherView = new TableView();
        this.classNameView = new TableView();
        this.classroomView = new TableView();
        this.subjectView = new TableView();

        File teacherFile = new File("src/TextFile/TeacherPathNames.txt");
        try (Scanner scanner = new Scanner(teacherFile)) {
//            scanner.nextLine();
                while (scanner.hasNext()){
                    String path = scanner.nextLine();
                    teacherObservableList.add((Teacher) fr.readObject(path));
            }
        } catch (Exception e) { e.printStackTrace(); }

        File classNameFile = new File("src/TextFile/ClassnamePathNames.txt");
        try (Scanner scanner = new Scanner(classNameFile)) {
            scanner.nextLine();
            while (scanner.hasNext()){
                String path = scanner.nextLine();
                classNameObservableList.add((ClassName) fr.readObject(path));
            }
        } catch (Exception e) { e.printStackTrace(); }

        File classRoomFile = new File("src/TextFile/ClassroomPathNames.txt");
        try (Scanner scanner = new Scanner(classRoomFile)) {
            scanner.nextLine();
            while (scanner.hasNext()){
                String path = scanner.nextLine();
                classroomObservableList.add((Classroom) fr.readObject(path));
            }
        } catch (Exception e) { e.printStackTrace(); }

        File subjectFile = new File("src/TextFile/SubjectPathNames.txt");
        try (Scanner scanner = new Scanner(subjectFile)) {
            scanner.nextLine();
            while (scanner.hasNext()){
                String path = scanner.nextLine();
                subjectObservableList.add((Subject) fr.readObject(path));
            }
        } catch (Exception e) { e.printStackTrace(); }

        TableColumn teacherNamecollumn = new TableColumn("Name");
        teacherNamecollumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("name"));
        TableColumn teacherPopularityCollumn = new TableColumn("Popularity");
        teacherPopularityCollumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("popularity"));
        teacherView.getColumns().addAll(teacherNamecollumn, teacherPopularityCollumn);
        teacherNamecollumn.setPrefWidth(125);
        teacherPopularityCollumn.setPrefWidth(125);
        TableColumn subjectNameCollumn = new TableColumn("Name");
        subjectNameCollumn.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
        TableColumn subjectPopularityCollumn = new TableColumn("Popularity");
        subjectPopularityCollumn.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("popularity"));
        subjectView.getColumns().addAll(subjectNameCollumn, subjectPopularityCollumn);
        subjectNameCollumn.setPrefWidth(125);
        subjectPopularityCollumn.setPrefWidth(125);
        TableColumn classroomNameCollumn = new TableColumn("Name");
        classroomNameCollumn.setCellValueFactory(new PropertyValueFactory<Classroom, String>("classID"));
        classroomView.getColumns().addAll(classroomNameCollumn);
        classroomNameCollumn.setPrefWidth(250);
        TableColumn classNameCollumn = new TableColumn("Name");
        classNameCollumn.setCellValueFactory(new PropertyValueFactory<ClassName, String>("name"));
        TableColumn classNumberOfStudents = new TableColumn("Number of students");
        classNumberOfStudents.setCellValueFactory(new PropertyValueFactory<ClassName, Integer>("numberOfStudents"));
        classNameView.getColumns().addAll(classNameCollumn, classNumberOfStudents);
        classNameCollumn.setPrefWidth(125);
        classNumberOfStudents.setPrefWidth(125);

        teacherView.setItems(teacherObservableList);
        subjectView.setItems(subjectObservableList);
        classNameView.setItems(classNameObservableList);
        classroomView.setItems(classroomObservableList);

        Button addButton = new Button("ADD");
        addButton.setFont(new Font(60));
        addButton.setMaxWidth(400);

        this.changeButton = new Button("CHANGE");
        changeButton.setFont(new Font(60));
        changeButton.setMaxWidth(400);

        this.deleteButton = new Button("DELETE");
        deleteButton.setFont(new Font(60));
        deleteButton.setMaxWidth(400);

        VBox buttonVbox = new VBox();
        buttonVbox.getChildren().addAll(addButton,changeButton,deleteButton);

        addButton.setOnAction(event -> {
            addButton();
        });

        changeButton.setOnAction(event -> {
            changeButton();
        });

        deleteButton.setOnAction(event -> {
            deleteButton();
        });

        VBox teacherVBox = new VBox();
        Label teacherLabel = new Label("Teachers");
        teacherLabel.setFont(new Font("Ariel", 20));
        teacherVBox.setSpacing(5);
        teacherVBox.setPadding(new Insets(10, 0, 0, 10));
        teacherVBox.getChildren().addAll(teacherLabel, teacherView);

        VBox subjectVBox = new VBox();
        Label subjectLabel = new Label("Subject");
        subjectLabel.setFont(new Font("Ariel", 20));
        subjectVBox.setSpacing(5);
        subjectVBox.setPadding(new Insets(10, 0, 0, 10));
        subjectVBox.getChildren().addAll(subjectLabel, subjectView);

        VBox classnameVBox = new VBox();
        Label classnameLabel = new Label("Class");
        classnameLabel.setFont(new Font("Ariel", 20));
        classnameVBox.setSpacing(5);
        classnameVBox.setPadding(new Insets(10, 0, 0, 10));
        classnameVBox.getChildren().addAll(classnameLabel, classNameView);

        VBox classroomVBox = new VBox();
        Label classroomLabel = new Label("Classroom");
        classroomLabel.setFont(new Font("Ariel", 20));
        classroomVBox.setSpacing(5);
        classroomVBox.setPadding(new Insets(10, 0, 0, 10));
        classroomVBox.getChildren().addAll(classroomLabel, classroomView);

        HBox hBox = new HBox();

        hBox.getChildren().addAll(teacherVBox, subjectVBox, classnameVBox, classroomVBox);

        borderPane.setCenter(hBox);
        borderPane.setLeft(buttonVbox);

        return borderPane;
    }

    public void addButton(){

        Stage stage = new Stage();
        stage.setTitle("Add");

        BorderPane borderPane = new BorderPane();
        borderPane.setMinHeight(100);

        Button teacherButton = new Button("Teacher");
        Button subjectButton = new Button("Subject");
        Button classButton = new Button("Class");
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
                    fr.addToFile("src/TextFile/TeacherPathNames.txt", "src/objectFile/teacher/" + nameTextField.getText() + ".dat");
                    teacherObservableList.add((teacher));
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
                    fr.addToFile("src/TextFile/SubjectPathNames.txt", "src/objectFile/subject/" + nameTextField.getText() + ".dat");
                    subjectObservableList.add(subject);
                    stage.close();
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
                        fr.addToFile("src/TextFile/ClassnamePathNames.txt", "src/objectFile/className/" + nameTextField.getText() + ".dat");
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
                    fr.addToFile("src/TextFile/ClassroomPathNames.txt", "src/objectFile/classroom/" + nameTextField.getText() + ".dat");
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

    public void changeButton(){

        Stage stage = new Stage();
        stage.setTitle("Change");

        BorderPane borderPane = new BorderPane();
        borderPane.setMinHeight(150);

        Button teacherButton = new Button("Teacher");
        Button subjectButton = new Button("Subject");
        Button classButton = new Button("Class");
        Button classroomButton = new Button("Classroom");

        HBox buttonHbox = new HBox();
        buttonHbox.getChildren().addAll(teacherButton, subjectButton, classButton, classroomButton);
        buttonHbox.setSpacing(20);
        borderPane.setTop(buttonHbox);

        teacherButton.setOnAction(event -> {
            ComboBox teacherComboBox = new ComboBox();
            teacherComboBox.setItems(teacherObservableList);

            VBox vBox = new VBox();
            vBox.getChildren().add(teacherComboBox);

            borderPane.setCenter(vBox);

            teacherComboBox.setOnAction(event1 -> {
                Label nameLabel = new Label("Name:");
                TextField nameTextField = new TextField();
                Teacher selectedTeacher = (Teacher) teacherComboBox.getSelectionModel().getSelectedItem();
                nameTextField.setText(selectedTeacher.getName());

                Label popularityLabel = new Label("Popularity:");
                TextField popularityTextField = new TextField();
                String popularity = String.valueOf(selectedTeacher.getPopularity());
                popularityTextField.setText(popularity);

                HBox nameHbox = new HBox();
                nameHbox.getChildren().addAll(nameLabel, nameTextField);

                HBox popularityHbox = new HBox();
                popularityHbox.getChildren().addAll(popularityLabel, popularityTextField);

                vBox.getChildren().addAll(nameHbox, popularityHbox);

                Button changeButton = new Button("Change");
                borderPane.setBottom(changeButton);

                changeButton.setOnAction(event2 -> {
                    teacherObservableList.remove(selectedTeacher);
                    int popularityTeacher = Integer.parseInt(popularityTextField.getText());
                    Teacher teacher = new Teacher(nameTextField.getText(), popularityTeacher);
                    teacherObservableList.add(teacher);
                    File deleteFile = new File("src/objectFile/teacher/" + selectedTeacher.getName() + ".dat");
                    deleteFile.delete();
                    try {
                        fr.writeObject(teacher, "src/objectFile/teacher/" + nameTextField.getText() + ".dat");
                        fr.removeFromFile("src/TextFile/TeacherPathNames.txt", "src/objectFile/teacher/" + selectedTeacher.getName() + ".dat");
                        fr.addToFile("src/TextFile/TeacherPathNames.txt", "src/objectFile/teacher/" + nameTextField.getText() + ".dat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    stage.close();
                });
            });



        });

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }

    public void deleteButton(){
        deleteButton.setOnAction(e -> {
            Teacher selectedTeacher = (Teacher) teacherView.getSelectionModel().getSelectedItem();
            if (selectedTeacher != null) {
                try {
                    fr.removeFromFile("src/TextFile/TeacherPathNames.txt", "src/objectFile/teacher/" + selectedTeacher.getName() + ".dat");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                teacherObservableList.remove(selectedTeacher);
                File deleteFile = new File("src/objectFile/teacher/" + selectedTeacher.getName() + ".dat");
                deleteFile.delete();
            }
            Subject selectedSubject = (Subject) subjectView.getSelectionModel().getSelectedItem();
            if ( selectedSubject != null) {
                try {
                    fr.removeFromFile("src/TextFile/SubjectPathNames.txt", "src/objectFile/subject/" + selectedSubject.getName() + ".dat");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                subjectObservableList.remove(selectedSubject);
                File deleteFile = new File("src/objectFile/Subject/" + selectedSubject.getName() + ".dat");
                deleteFile.delete();
            }
            ClassName selectedClassName = (ClassName) classNameView.getSelectionModel().getSelectedItem();
            if(selectedClassName != null){
                try {
                    fr.removeFromFile("src/TextFile/ClassnamePathNames.txt", "src/objectFile/className/" + selectedClassName.getName() + ".dat");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                classNameObservableList.remove(selectedClassName);
                File deleteFile = new File("src/objectFile/className/" + selectedClassName.getName() + ".dat");
                deleteFile.delete();
            }
            Classroom selectedClassroom = (Classroom) classroomView.getSelectionModel().getSelectedItem();
            if( selectedClassroom != null){
                try {
                    fr.removeFromFile("src/TextFile/ClassroomPathNames.txt", "src/objectFile/classroom/" + selectedClassroom.getClassID() + ".dat");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                classroomObservableList.remove(selectedClassroom);
                File deleteFile = new File("src/objectFile/classroom/" + selectedClassroom.getClassID() + ".dat");
                deleteFile.delete();
            }
        });

    }
}

