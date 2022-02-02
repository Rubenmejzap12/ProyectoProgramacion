package es.rubenmejias.projectfinal_ruben;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * JavaFX App
 */
public class App extends Application {
    int score;
    int highScore;
    
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
    ImageView oveja1;
    int ovejaX = 0;
    int ovejaY = 0;
    
    
    final int TEXT_SIZE = 24;
    Text textScore;
    Pane root = new Pane();
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene (root, PantallaX, PantallaY); // Creacion ventana juego con las medidas de ancho y alto
        stage.setTitle("AguilaDepredadora"); //Titulo que aparece en ventana
        stage.setScene(scene);
        stage.show();
       
        
        
        
         //LAYOUTS PARA MOSTRAR PUNTUACIONES

          //Layout principal
          HBox paneScores = new HBox();
          paneScores. setTranslateY(20) ;
          paneScores. setMinWidth(PantallaX) ;
          paneScores. setAlignment (Pos.CENTER) ;
          paneScores. setSpacing(100) ;
          root.getChildren().add(paneScores) ;

          //Layout para puntuación actual
          HBox paneCurrentScore = new HBox();
          paneCurrentScore. setSpacing(10) ;
          paneScores.getChildren().add(paneCurrentScore) ;

          //Layout para puntuación maxima
          HBox paneHighScore = new HBox();
          paneHighScore. setSpacing(10) ;
          paneScores.getChildren().add(paneHighScore) ;

          //Texto de etiqueta para la puntuación
          Text textTitleScore = new Text("Score:");
          textTitleScore.setFont(Font.font(TEXT_SIZE));
          textTitleScore.setFill(Color.BLACK);

          //Texto para la puntuacion
          Text textScore = new Text("0");
          textScore. setFont (Font. font (TEXT_SIZE));
          textScore. setFill(Color.BLACK) ;

          //Texto de etiqueta para la puntuación maxima
          Text textTitleHighScore = new Text("Max.Score:");
          textTitleHighScore. setFont (Font. font (TEXT_SIZE));
          textTitleHighScore.setFill(Color.BLACK) ;

          //Texto para la puntuación maxima
          Text textHighScore = new Text("0");
          textHighScore. setFont (Font. font (TEXT_SIZE)) ;
          textHighScore. setFill(Color.BLACK) ;
          paneCurrentScore.getChildren().add(textTitleScore) ;
          paneCurrentScore.getChildren().add(textScore) ;
          paneHighScore.getChildren().add(textTitleHighScore);
          paneHighScore.getChildren().add(textHighScore);

          
        
        
        // Imagen 1
        ImageView oveja1 = new ImageView();
        Rectangle rectZona1 = new Rectangle(226, 138); // Debe englobar a la imagen
        Group groupZona1 = new Group();
        groupZona1.getChildren().addAll(oveja1, rectZona1);
        // Hacer invisible el rectángulo
        rectZona1.setVisible(false);
 
        
        
        
        //Imagen fondo
        Image fondoImg = new Image (getClass().getResourceAsStream("/images/pradera.jpg"));
        fondo1 = new ImageView(fondoImg);
        root.getChildren().add(fondo1);
        
        //Imagen 2 del fondo que hemos añadido siguiendo a la 1 para el movimiento el fondo
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
        
        
        
        
        

        //Desplazar la pantala a la izquierda para que paresca infinita
        Timeline fondoScroll = new Timeline(
                  new KeyFrame(Duration.seconds(0.006), (ActionEvent ae) -> {
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
        //EJECUTA MOVIMIENTO FONDO
        fondoScroll.setCycleCount(Timeline.INDEFINITE);
        fondoScroll.play(); // EJECUTAR EL TIMELINE
        
        
        //Movimiento personaje con las teclas deseadas
        Timeline aguilaScroll = new Timeline(
                  new KeyFrame(Duration.seconds(0.005), (ActionEvent ae) -> {
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
                        //PULSADA TECLA DERECHA y que no se salga el personaje por el lateral derecho
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
        //EJECUTA MOVIMIENTO AGUILA
        aguilaScroll.setCycleCount(Timeline.INDEFINITE);
        aguilaScroll.play(); // EJECUTAR EL TIMELINE
          
    }

    public static void main(String[] args) {
        launch();
    }
    
  
}