package es.rubenmejias.projectfinal_ruben;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * JavaFX App
 */
public class App extends Application {
    
    
    int PantallaX = 800;
    int PantallaY = 450;
    ImageView fondo1;
    ImageView fondo2;
    int fondo1X = 0;
    int fondo2X = 800;
    ImageView personaje1;
    int personajeX = 100;
    int personajeY = 0;
    
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene (root, PantallaX, PantallaY);
        stage.setTitle("BosqueMaldito");
        stage.setScene(scene);
        stage.show();
        
        //Imagen fondo
        Image fondoImg = new Image (getClass().getResourceAsStream("/images/suelo1.jpg"));
        fondo1 = new ImageView(fondoImg);
        root.getChildren().add(fondo1);
        fondo2 = new ImageView(fondoImg);
        root.getChildren().add(fondo2);
        
        
        fondo1.setLayoutX(fondo1X);
        fondo2.setLayoutX(fondo2X);
        
        
        //Imagen personaje
        Image personajeImg = new Image (getClass().getResourceAsStream("/images/personaje.gif"));
        personaje1 = new ImageView(personajeImg);
        root.getChildren().add(personaje1);
        
        personaje1.setLayoutX(personajeX);
        personaje1.setLayoutX(personajeY);
        //Desplazar la pantala ala izquierda
        Timeline fondoScroll = new Timeline(
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      System.out.println("dentro del timeline");
                      fondo1X = fondo1X -1;
                      System.out.println(fondo1X);
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
          
    }

    public static void main(String[] args) {
        launch();
    }
}
