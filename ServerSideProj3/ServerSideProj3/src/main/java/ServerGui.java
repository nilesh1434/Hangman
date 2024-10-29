//import javafx.application.Application;
//
//import javafx.scene.Scene;
//
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class JavaFXTemplate extends Application {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		launch(args);
//	}
//
//	//feel free to remove the starter code from this method
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		primaryStage.setTitle("Welcome to JavaFX");
//		
//		
//		
//				
//		Scene scene = new Scene(new VBox(), 700,700);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}
//
//}

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class ServerGui extends Application{

	
	private TextField t1;

    
	HashMap<String, Scene> sceneMap;
	
	@FXML
	private GridPane gridPane = new GridPane(); // creates a grid pane

	
	char[] displayWord;
	String displayString;
	TextField s1,s2,s3,s4;
	Button b, b1, b2, playBtn, howToPlayBtn, backToMenuBtn;
	GridPane grid;
	HBox buttonBox;
	VBox clientBox;
	Scene startScene, clientMenuScene;
	BorderPane startPane;
	Server serverConnection;
	ListView<String> listItems, listItems2;
	private Button[][] arr = new Button[13][2];
	private EventHandler<ActionEvent> myHandler;
	private int portNumber = -1;
	//private Image bg;
	//private Button HowToPlay;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("The Networked Client/Server GUI Example");
		

		listItems = new ListView<String>();
		t1 = new TextField();
		t1.setText("Please Enter A Port Number");
		b1 = new Button("Start Server");
		startScene = createServerLoginGui();
		sceneMap = new HashMap<String, Scene>();
		sceneMap.put("server",  startScene);
		
		
		b1.setOnAction(e->{
			try {
				portNumber = Integer.parseInt(t1.getText());
				startScene = createServerGui();
				sceneMap.put("server",  startScene);
				primaryStage.setScene(startScene);
			} catch (Exception t) {
				t1.setText("Please Enter A Port Number");
			}
			
		});
		
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		
		
		primaryStage.setScene(startScene);
		primaryStage.show();
		
	}
	// create server GUI
	public Scene createServerGui() { 
		serverConnection = new Server(data -> {Platform.runLater(()->{listItems.getItems().add(data.toString());});}, portNumber);
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: black");
		pane.setCenter(listItems);
		return new Scene(pane, 700, 700);
		
	}
	
	public Scene createServerLoginGui() { 
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: black");
		pane.setCenter(t1);
		pane.setBottom(b1);
		return new Scene(pane, 700, 700);
		
	}

}

