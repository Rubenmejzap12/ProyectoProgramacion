package es.rubenmejias.projectfinal_ruben;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * JavaFX App
 */
public class App extends Application {
    
    int fondo1X = 0;
    int fondo2X = 800;
    ImageView fondo1;
    ImageView fondo2;
    
    
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene (root, 800, 450);
        stage.setTitle("BosqueMaldito");
        stage.setScene(scene);
        stage.show();
        fondo1 = new ImageView();
        fondo2 = new ImageView();
        root.getChildren().add(fondo1);
        root.getChildren().add(fondo2);
        fondo1.setLayoutX(fondo1X);
        fondo2.setLayoutX(fondo2X);
        // Cargar la imagen crear objeto ImageView
        Image img = new Image(getClass().getResourceAsStream(""));
        fondo1 = new ImageView(img);
        root.getChildren().add(fondo1);
        fondo2 = new ImageView(img);
        root.getChildren().add(fondo2);
       
        //Desplazar la pantala ala izquierda
        Timeline fondoScroll = new Timeline(
                  new KeyFrame(Duration.seconds(0.007), (ActionEvent ae) -> {
                      fondo1X = fondo1X -1;
                      fondo1.setLayoutX(fondo1X);
                      fondo2X = fondo2X -1;
                      fondo2.setLayoutX(fondo2X);
                      if (fondo1X == -800) {
                          fondo1X = 800;
                      } else if (fondo2X == -800) {
                          fondo2X = 800;
                      }
                  })
          );
          fondoScroll.setCycleCount(Timeline.INDEFINITE);
          fondoScroll.play(); // EJECUTAR EL TIMELINE
          
                  // Cargar la imagen crear objeto ImageView
        Image img = new Image(getClass().getResourceAsStream("/images/suelo1.jpg"));
        ImageView imgView = new ImageView(img);
        // Añadir el ImageView al panel principal de la pantalla 
        paneRoot.getChildren().add(imgView);
    }

    public static void main(String[] args) {
        launch();
    }
}
