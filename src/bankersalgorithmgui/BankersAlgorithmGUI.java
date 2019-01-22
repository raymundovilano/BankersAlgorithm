/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankersalgorithmgui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BankersAlgorithmGUI extends Application {
    
    // Global Variables
    
    //Panes
    private BorderPane root = new BorderPane();
    private ScrollPane scrollex = new ScrollPane();
    private GridPane allocationGPane = new GridPane();
    private GridPane requestGPane = new GridPane();
    private GridPane availableGPane = new GridPane();  
    
    //Vertical Boxes
    private VBox leftBox = new VBox();
    private VBox mainBox = new VBox();
    private VBox centerBox = new VBox();
    private VBox frameBox = new VBox();
    
    //Horizontal Boxes
    private HBox headingBox = new HBox();
    private HBox dataTable = new HBox();  
    
    // Buttons
    private Button applySettings = new Button("Apply Settings");
    private Button runAlgorithm = new Button("Run Algorithm");
    private Button collectData = new Button("Collect Data");
    //Combo Boxes
    private ComboBox<Integer> processes = new ComboBox<>();
    private ComboBox<Integer> resources = new ComboBox<>();
    
    // Global Arrays
    private int[][] allocationMatrix;
    private int[][] maxMatrix;
    private int[] availableMatrix;
    
    private TextField[][] allocationFields;
    private TextField[][] maxFields;
    private TextField[] availableFields;
    
    // Start FX
    @Override
    public void start(Stage primaryStage) {
        
        // Customization of boxes
        leftBox.setStyle("-fx-background-color: #800000");
        leftBox.setPadding(new Insets(25));
        leftBox.setSpacing(10);
        
        headingBox.setSpacing(80);
        headingBox.setStyle("-fx-background-color: #ffffff");
        headingBox.setPadding(new Insets(25));
        
                
        frameBox.setSpacing(15);
        frameBox.setStyle("-fx-background-color: #ffffff");
        frameBox.setPadding(new Insets(25));
        
        allocationGPane.setHgap(5);
        allocationGPane.setVgap(15);

        requestGPane.setHgap(5);
        requestGPane.setVgap(15);        
        
        availableGPane.setHgap(5);
        availableGPane.setVgap(15);        
        
        mainBox.setSpacing(20);
        mainBox.setStyle("-fx-background-color: #ffffff");
        mainBox.setPadding(new Insets(25));
        
        scrollex.setStyle("-fx-background-color: #ffffff");
        scrollex.setPadding(new Insets(25));
        
       
        Label leftHeading = new Label("Settings");
        leftHeading.setFont(new Font("Cambria", 24));
        leftHeading.setTextFill(Color.web("#ffffff"));
        
        Label processesLabel = new Label("Number of Processes:");
        processesLabel.setTextFill(Color.web("#ffffff"));
        
        //Populate processes combo box to 5 items max
        this.processes.getItems().addAll(1, 2, 3, 4, 5);
        this.processes.getSelectionModel().selectFirst();

        
        Label resourcesLabel = new Label("Number of Resources:");
        resourcesLabel.setTextFill(Color.web("#ffffff"));
        
         //Populate resources combo box to 5 items max
        this.resources.getItems().addAll(1, 2, 3, 4, 5);
        this.resources.getSelectionModel().selectFirst();
        
        //Create button and action
        Apply_Settings applySettingsA = new Apply_Settings();
        applySettings.setOnAction(applySettingsA);
        
        
      
       // Center Box Info
        Label heading = new Label("Banker's Algorithm Simulator");
        
        heading.setFont(new Font("Cambria", 28));
        
        Label heading2 = new Label("By Raymundo Vilano");
        
        heading2.setFont(new Font("Cambria", 10));
       
        headingBox.getChildren().addAll(heading,heading2);
        
        Text text1 = new Text("Welcome to this simulator, please follow the instructions:");
        Text text2 = new Text("Select # of Processes and # of Resources");
        Text text3 = new Text("Click 'Apply Settins' button");
        text1.setY(50);
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14)); 
        text2.setY(50);
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14)); 
        text3.setY(50);
        text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14)); 
        centerBox.setSpacing(40);
        centerBox.alignmentProperty();
        centerBox.getChildren().addAll(text1, text2, text3);
        
        // Add everything to the hbox leftBox
        leftBox.getChildren().addAll(leftHeading, processesLabel , processes, resourcesLabel, resources, applySettings);
        
        mainBox.getChildren().addAll(headingBox, centerBox);
        
        scrollex.setContent(mainBox);
        
        root.setLeft(leftBox);
        root.setCenter(scrollex);
        
        Scene scene = new Scene(root, 800, 750);
        
        primaryStage.setTitle("CMPE 4334 - Banker's Algorithm Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

 
    public static void main(String[] args) {
        launch(args);
    }
    
    // Apply Settings Button Handler
    class Apply_Settings implements EventHandler<ActionEvent> {
        
        @Override
        public void handle(ActionEvent event) {
            
            if(mainBox.getChildren().contains(centerBox)){
                mainBox.getChildren().remove(centerBox);
                mainBox.getChildren().add(frameBox);
            }
            
            
            frameBox.getChildren().clear();
            
            allocationGPane.getChildren().clear();
            requestGPane.getChildren().clear();
            availableGPane.getChildren().clear();
            
            
            int processesNo = processes.getValue();
            int resourcesNo = resources.getValue();
            
            
            allocationMatrix = new int[processesNo][resourcesNo];
            maxMatrix = new int[processesNo][resourcesNo];
            availableMatrix = new int[resourcesNo];
            
            
            allocationFields = new TextField[processesNo][resourcesNo];
            maxFields = new TextField[processesNo][resourcesNo];
            availableFields = new TextField[resourcesNo];
            
            
            Label label_1 = new Label("Allocation");
            Label label_2 = new Label("Max");
            Label label_3 = new Label("Available");
            
            label_1.setFont(new Font("Arial", 20));
            label_2.setFont(new Font("Arial", 20));
            label_3.setFont(new Font("Arial", 20));
            
            VBox allocationVBox = new VBox();
            VBox requestVBox = new VBox();
            VBox availableVBox = new VBox();
            
            
            for(int i = 0; i < processesNo; i++){
                
                allocationGPane.add(new Label("P"+i), 0, i);
                
                for(int j = 0; j < resourcesNo; j++){
                   
                    allocationFields[i][j] = new TextField();                 
                    allocationFields[i][j].setPrefHeight(20);
                    allocationFields[i][j].setPrefWidth(35);
                    allocationFields[i][j].setAlignment(Pos.CENTER);
                    allocationFields[i][j].setEditable(true);
                    
                    GridPane.setRowIndex(allocationFields[i][j], resourcesNo);
                    GridPane.setColumnIndex(allocationFields[i][j], processesNo); 
                    
                    allocationGPane.add(allocationFields[i][j], j+1, i);
                }
            }
            
            
            allocationVBox.setSpacing(5);
            allocationVBox.setPadding(new Insets(10, 0, 0, 10));
            allocationVBox.getChildren().addAll(label_1, allocationGPane);

           
            for(int i = 0; i < processesNo; i++){
                
                requestGPane.add(new Label("P"+i), 0, i);
                
                for(int j = 0; j < resourcesNo; j++){
                    maxFields[i][j] = new TextField();
                    maxFields[i][j].setPrefHeight(20);
                    maxFields[i][j].setPrefWidth(35);
                    maxFields[i][j].setAlignment(Pos.CENTER);
                    maxFields[i][j].setEditable(true);
                    GridPane.setRowIndex(maxFields[i][j], resourcesNo);
                    GridPane.setColumnIndex(maxFields[i][j], processesNo); 
                    requestGPane.add(maxFields[i][j], j+1, i);
                }
            }            
            
            requestVBox.setSpacing(5);
            requestVBox.setPadding(new Insets(10, 0, 0, 10));
            requestVBox.getChildren().addAll(label_2, requestGPane);

            
            for(int i = 0; i < resourcesNo; i++){
                
                availableGPane.add(new Label("P0"), 0, 0);
                availableFields[i] = new TextField();
                availableFields[i].setPrefHeight(20);
                availableFields[i].setPrefWidth(35);
                availableFields[i].setAlignment(Pos.CENTER);
                availableFields[i].setEditable(true);
                GridPane.setColumnIndex(availableFields[i], resourcesNo); 
                availableGPane.add(availableFields[i], i+1, 0);
              
                
            }
            
            availableVBox.setSpacing(5);
            availableVBox.setPadding(new Insets(10, 0, 0, 10));
            availableVBox.getChildren().addAll(label_3, availableGPane);

            
            frameBox.getChildren().addAll(allocationVBox, requestVBox, availableVBox);
            
            
            if(!leftBox.getChildren().contains(collectData)){
                leftBox.getChildren().add(collectData);
            }
            
            
                Collect_Data collectDataA = new Collect_Data(processesNo, resourcesNo); 
                collectData.setOnAction(collectDataA);  
          
        }
    }
    
    class Collect_Data implements EventHandler<ActionEvent> {
        
        private int processNo = 0;
        private int resourceNo = 0;
        
        public Collect_Data(int processNo, int resourceNo){
            this.processNo = processNo;
            this.resourceNo = resourceNo;
        }
        
        @Override
        public void handle(ActionEvent event) {
           
            if(!frameBox.getChildren().contains(dataTable))
                frameBox.getChildren().add(dataTable);
            
           
            dataTable.getChildren().clear();
            
            
            Label dataTitle = new Label("Data Matrix");
            dataTitle.setAlignment(Pos.CENTER);
            
           
            for (int i = 0; i < this.processNo; i++) {
                for (int j = 0; j < this.resourceNo; j++) {
                    allocationMatrix[i][j] = Integer.valueOf(allocationFields[i][j].getText()); 
                }
            }
            
            for (int i = 0; i < this.processNo; i++) {
                for (int j = 0; j < this.resourceNo; j++) {
                    maxMatrix[i][j] = Integer.valueOf(maxFields[i][j].getText());
                }
            }
            
            for (int i = 0; i < this.resourceNo; i++) {
              
               availableMatrix[i] = Integer.valueOf(availableFields[i].getText());
                
            }
            
            
            Label t1 = new Label("Allocation");
            GridPane allocatePane = new GridPane();
            allocatePane.setHgap(5); 
            allocatePane.setVgap(10); 
            allocatePane.setPadding(new Insets(2, 2, 2, 2));
            
            for (int i = 0; i < this.processNo; i++) {
                allocatePane.add(new Label("P"+i), 0, i);
                
                for (int j = 0; j < this.resourceNo; j++) {
                    allocatePane.add(new Label(" " + allocationMatrix[i][j]), j+1, i);
                }
            }
            
            
            Label t2 = new Label("Max");
            GridPane requestPane = new GridPane();
            requestPane.setHgap(5); 
            requestPane.setVgap(10); 
            requestPane.setPadding(new Insets(2, 2, 2, 2));
            
            for (int i = 0; i < this.processNo; i++) {
                requestPane.add(new Label("P"+i + " "), 0, i);
                
                for (int j = 0; j < this.resourceNo; j++) {
                    requestPane.add(new Label(" " + maxMatrix[i][j]), j+1, i);
                }
            }
            
            
            Label t3 = new Label("Available");
            GridPane availablePane = new GridPane();
            availablePane.setHgap(5); 
            availablePane.setVgap(10); 
            availablePane.setPadding(new Insets(2, 2, 2, 2));
            
            for (int i = 0; i < this.resourceNo; i++) {
                availablePane.add(new Label("P0"), 0, 0);  
                availablePane.add(new Label(" " + availableMatrix[i]), i+1, 0);
                
            }
            
           
            dataTable.getChildren().clear();
            VBox central = new VBox();
            HBox horizontal = new HBox();
            HBox horizontal2 = new HBox();
            
            dataTable.setStyle("-fx-background-color: #DCDCDC");
            dataTable.setPadding(new Insets(25));
            dataTable.setSpacing(50);
            
            horizontal.setStyle("-fx-background-color: #C0C0C0");
            horizontal.setSpacing(100);
            horizontal.setPadding(new Insets(5));
     
            int resourcesNo = resources.getValue();
            
           
            if(resourcesNo == 1){
               horizontal2.setSpacing(100);
            }
            if(resourcesNo == 2){
               horizontal2.setSpacing(100);
            }
            if(resourcesNo == 3){
               horizontal2.setSpacing(80);
            }
            if(resourcesNo == 4){
               horizontal2.setSpacing(60);
            }
            if(resourcesNo == 5){
               horizontal2.setSpacing(30);
            }
            
            
            
            horizontal2.setPadding(new Insets(5));
            
            horizontal.getChildren().add(t1);
            horizontal2.getChildren().add(allocatePane);
           
            
            horizontal.getChildren().add(t2);
            horizontal2.getChildren().add(requestPane);
            
            horizontal.getChildren().add(t3);
            horizontal2.getChildren().add(availablePane);
            
            central.getChildren().addAll(dataTitle, horizontal, horizontal2);
            
            dataTable.getChildren().add(central);
            
              
            if(!leftBox.getChildren().contains(runAlgorithm))
                leftBox.getChildren().add(runAlgorithm);
            
           
            Run_Algorithm runAlgorithM = new Run_Algorithm();
            runAlgorithm.setOnAction(runAlgorithM);
        }
    }
    
    class Run_Algorithm implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            
            
            if(Algorithm.isSafe(processes.getValue(), resources.getValue(), availableMatrix, maxMatrix, allocationMatrix)){
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText("Safe State Found");          
              alert.showAndWait();  
            }
            else if(!Algorithm.isSafe(processes.getValue(), resources.getValue(), availableMatrix, maxMatrix, allocationMatrix)){
              Alert alert = new Alert(AlertType.WARNING);
              alert.setTitle("Information Dialog");
              alert.setHeaderText("System State is Unsafe");          
              alert.showAndWait();   
            }
            else{
              Alert alert = new Alert(AlertType.ERROR);
              alert.setTitle("Information Dialog");
              alert.setHeaderText("Wrong Input Values");          
              alert.showAndWait(); 
            }
               
       
         
            
            
        }
        
        
    }
    
    
}
