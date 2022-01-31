package es.rubenmejias.projectfinal_ruben;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * JavaFX App
 */
public class App extends Application {
    
    
    int PantallaX = 800;
    int PantallaY = 400;
    ImageView fondo1;
    ImageView fondo2;
    int fondo1X = 0;
    int fondo2X = 800;
    ImageView aguila1;
    int aguilaX = 0;
    int aguilaY = 0;
    int stickCurrentSpeed = 0;
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene (root, PantallaX, PantallaY);
        stage.setTitle("AguilaDepredadora");
        stage.setScene(scene);
        stage.show();
        
        //Imagen fondo
        Image fondoImg = new Image (getClass().getResourceAsStream("/images/pradera.jpg"));
        fondo1 = new ImageView(fondoImg);
        root.getChildren().add(fondo1);
        Image fondoImg1 = new Image (getClass().getResourceAsStream("/images/pradera2.jpg"));
        fondo2 = new ImageView(fondoImg1);
        root.getChildren().add(fondo2);
        
        fondo1.setLayoutX(fondo1X);
        fondo2.setLayoutX(fondo2X);
        
        //Imagen personaje
        Image personajeImg = new Image (getClass().getResourceAsStream("/images/aguila.gif"));
        aguila1 = new ImageView(personajeImg);
        root.getChildren().add(aguila1);
        
        aguila1.setLayoutX(aguilaX);
        aguila1.setLayoutY(aguilaY);

        //Desplazar la pantala ala izquierda
        Timeline fondoScroll = new Timeline(
                  new KeyFrame(Duration.seconds(0.004), (ActionEvent ae) -> {
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
        //Movimiento personaje con teclas
        Timeline aguilaScroll = new Timeline(
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      scene.setOnKeyPressed((KeyEvent event) -> {
                      aguila1.setLayoutX(aguilaX);
                      aguila1.setLayoutY(aguilaY);
                      switch(event.getCode()) {
                      case UP:
                        //PULSADA TECLA ARRIBA
                        if (aguilaY >= 0){
                            aguilaY = aguilaY -3;
                        }
                        break;
                      case DOWN:
                        //PULSADA TECLA ABAJO
                        if (aguilaY <= 220){
                            aguilaY = aguilaY +3;
                          }
                        break;
                      case RIGHT:
                        //PULSADA TECLA DERECHA
                        if (aguilaX <= 540){
                        aguilaX = aguilaX +3;
                          }
                        break;
                      case LEFT:
                        //PULSADA TECLA IZQUIERDA
                        if (aguilaX >= 0){
                        aguilaX = aguilaX -3;
                          }
                        break;
                       }
          });  
                  })
        
          );
          //EJECUTA MOVIMIENTO FONDO
          fondoScroll.setCycleCount(Timeline.INDEFINITE);
          fondoScroll.play(); // EJECUTAR EL TIMELINE
          //EJECUTA MOVIMIENTO AGUILA
          aguilaScroll.setCycleCount(Timeline.INDEFINITE);
          aguilaScroll.play(); // EJECUTAR EL TIMELINE
          
    }

    public static void main(String[] args) {
        launch();
    }
}