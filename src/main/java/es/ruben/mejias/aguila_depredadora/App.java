package es.ruben.mejias.aguila_depredadora;

import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;



/**
 * JavaFX App
 */
public class App extends Application {
    Pane root = new Pane();
    ImageView fondo1;
    ImageView fondo2;
    ImageView aguila;
    ImageView oveja;
    ImageView meteorito1;
    ImageView meteorito2;
    Rectangle rectAguila = new Rectangle(150, 120);
    Rectangle rectOveja = new Rectangle(110, 65);
    Rectangle rectMeteorito1 = new Rectangle(60, 60);
    Rectangle rectMeteorito2 = new Rectangle(60, 60);
    
    //GRUPOS DE LOS DISTINTOS ELEMENTOS QUE FORMAN EL JUEGO
    Group groupAguila = new Group();
    Group groupOveja = new Group();
    Group groupMeteorito1 = new Group();
    Group groupMeteorito2 = new Group();
    
    Label labelPuntos;
    
    // DECLARACION DE VARIABLES QUE VAMOS A USAR
    final int SCENE_TAM_X = 1000;
    final int SCENE_TAM_Y = 500;
    final int TEXT_SIZE = 24;
    double fondo1PositionX = 0;
    double fondo2PositionX = 1000;
    int aguilaCurrentSpeed = 0;
    int aguilaPositionX = 100;
    int aguilaPositionY = 100;
    int ovejaPositionX = 1100;
    int ovejaPositionY = 100;
    int meteorito1PositionX = 1200;
    int meteorito1PositionY = 150;
    int meteorito2PositionX = 1300;
    int meteorito2PositionY = 250;
    int numRandom1 = 0;
    int numRandom2 = 0;
    int numRandom3 = 0;
    int numRandom4 = 0;
    int score = 0;
    int velocidad = 5;
    
    
    private void crearPuntos(){ // ESTE METODO CREA EL TEXTO QUE INDICA LA PUNTUACION
       labelPuntos = new Label("Puntos: "+score); // ESTO ES LO QUE SE VA A MOSTRAR EN LA PANTALLA
       Font font = Font.font("Arial Black", FontWeight.BOLD, FontPosture.REGULAR, 25); // PROPIEDADES DEL TEXTO
       labelPuntos.setFont(font);
       labelPuntos.setTextFill(Color.WHITE); //  COLOR DEL TEXTO
       labelPuntos.setTranslateX(100); // CORDENADAS X DE TEXTO
       labelPuntos.setTranslateY(25); // CORDENADAS Y DEL TEXTO
       root.getChildren().add(labelPuntos); // LO AÑADIMOS A ROOT
   }
    
    private void cambiarPuntos(){ //  ACTUALIZA EL MARCADOR DE PUNTOS CADA VEZ QUE COJEMOS UNA OVEJA
        labelPuntos.setText(""); // PRIMERO VACIAMOS EL TEXTO QUE YA TENIAMOS
        labelPuntos.setText("Puntos: "+score); // PINTAMOS EN PANTALLA EL NUEVO TEXTO
    }
    
    private void resetGame(){ // REINICIAMOS LA PARTIDA, ESTABLECEMOS EL VALOR POR DEFECTO DE LAS VARIABLES QUE VAMOS A USAR
        fondo1PositionX = 0;
        fondo2PositionX = 1000;
        aguilaCurrentSpeed = 0;
        aguilaPositionX = 100;
        aguilaPositionY = 100;
        ovejaPositionX = 1100;
        ovejaPositionY = 100;
        meteorito1PositionX = 1200;
        meteorito1PositionY = 150;
        meteorito2PositionX = 1300;
        meteorito2PositionY = 250;
        numRandom1 = 0;
        numRandom2 = 0;
        numRandom3 = 0;
        numRandom4 = 0;
        score = 0;
        velocidad = 5;
    }
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(root, SCENE_TAM_X, SCENE_TAM_Y); // ESTABLECEMOS EL TAMAÑO DE LA PANTALLA
        stage.setTitle("Aguila Depredadora"); // TITULO DE LA PANTALLA DEL JUEGO
        stage.setScene(scene);
        stage.show();
        
        Image fondoImg = new Image(getClass().getResourceAsStream("/images/fondo.jpg")); // RUTA DEL RECURSO QUE VAMOS A USAR
        fondo1 = new ImageView(fondoImg); // ASIGNAMOS LA IMAGEN AL OBJETO
        fondo2 = new ImageView(fondoImg); 
        root.getChildren().add(fondo1); //AÑADE EL OBJETO O IMAGEN A LA PANTALLA DEL JUEGO 
        root.getChildren().add(fondo2);
        Image aguilaImg = new Image(getClass().getResourceAsStream("/images/aguila2.gif")); // RUTA DEL RECURSO QUE VAMOS A USAR
        aguila = new ImageView(aguilaImg); 
        root.getChildren().add(groupAguila); 
        groupAguila.getChildren().addAll(aguila,rectAguila); // CREAMOS UN GRUPO CON LA IMAGEN Y EL RECTANGULO PARA PODER REALIZAR LAS COLISIONES
        Image ovejaImg = new Image(getClass().getResourceAsStream("/images/oveja.gif")); // RUTA DEL RECURSO QUE VAMOS A USAR
        oveja = new ImageView(ovejaImg); 
        root.getChildren().add(groupOveja); 
        groupOveja.getChildren().addAll(oveja,rectOveja);// CREAMOS UN GRUPO CON LA IMAGEN Y EL RECTANGULO PARA PODER REALIZAR LAS COLISIONES
        Image meteoritoImg = new Image(getClass().getResourceAsStream("/images/meteorito.gif")); // RUTA DEL RECURSO QUE VAMOS A USAR
        meteorito1 = new ImageView(meteoritoImg); 
        meteorito2 = new ImageView(meteoritoImg); 
        root.getChildren().add(groupMeteorito1); 
        groupMeteorito1.getChildren().addAll(meteorito1,rectMeteorito1);// CREAMOS UN GRUPO CON LA IMAGEN Y EL RECTANGULO PARA PODER REALIZAR LAS COLISIONES
        root.getChildren().add(groupMeteorito2); 
        groupMeteorito2.getChildren().addAll(meteorito2,rectMeteorito2);// CREAMOS UN GRUPO CON LA IMAGEN Y EL RECTANGULO PARA PODER REALIZAR LAS COLISIONES
        
        
        
        
        // ACTUALIZAMOS LA POSICION DE LOS OBJETOS A LAS QUE LES CORRESPONDES
        fondo1.setLayoutX(fondo1PositionX);
        fondo2.setLayoutX(fondo2PositionX);
        groupAguila.setLayoutX(aguilaPositionX);
        groupAguila.setLayoutY(aguilaPositionY);
        groupOveja.setLayoutX(ovejaPositionX);
        groupOveja.setLayoutY(ovejaPositionY);
        groupMeteorito1.setLayoutX(meteorito1PositionX);
        groupMeteorito1.setLayoutY(meteorito1PositionY);
        groupMeteorito2.setLayoutX(meteorito2PositionX);
        groupMeteorito2.setLayoutY(meteorito2PositionY);
        rectAguila.setVisible(false); // HACEMOS INVISIBLES LOS RECTANGULOS DE LOS OBJETOS NECESARIOS PARA LA COLISION
        rectOveja.setVisible(false);
        rectMeteorito1.setVisible(false);
        rectMeteorito2.setVisible(false);
        
        crearPuntos(); // CREAMOS EL TEXTO DE PUNTUACION CON EL METODO CREADO ANTERIORMENTE
        
        Timeline movimientoFondo = new Timeline( // CON ESTE TIMELINE MOVEMOS EL FONDO PARA QUE PAREZCA INFINITO
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      fondo1PositionX = fondo1PositionX -1;
                      fondo1.setLayoutX(fondo1PositionX);
                      fondo2PositionX = fondo2PositionX -1;
                      fondo2.setLayoutX(fondo2PositionX);
                      if (fondo1PositionX == -1000) {
                          fondo1PositionX = 1000;
                      } else if (fondo2PositionX == -1000) {
                          fondo2PositionX = 1000;
                      }
                  })
        );
        movimientoFondo.setCycleCount(Timeline.INDEFINITE); 
        movimientoFondo.play(); 
        
        Timeline movimientoObjetos = new Timeline( // LE AÑADIMOS EL MOVIMIENTO A LOS OBJETOS QUE TENEMOS QUE COGER Y QUE ESQUIVAS
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      ovejaPositionX = ovejaPositionX - velocidad;
                      groupOveja.setLayoutX(ovejaPositionX);
                      meteorito1PositionX = meteorito1PositionX - velocidad;
                      groupMeteorito1.setLayoutX(meteorito1PositionX);
                      meteorito2PositionX = meteorito2PositionX - velocidad;
                      groupMeteorito2.setLayoutX(meteorito2PositionX);
                      if (ovejaPositionX <= -60) {
                          ovejaPositionX = 1100;
                          numRandom1= (int) (Math.random() * 4) + 1;
                          if(numRandom1 == 1){
                              groupOveja.setLayoutY(50);
                          }else if(numRandom1 == 2){
                              groupOveja.setLayoutY(150);
                          }else if(numRandom1 == 3){
                              groupOveja.setLayoutY(250);
                          }else if(numRandom1 == 4){
                              groupOveja.setLayoutY(350);
                          }
                          
                      }
                      if (meteorito1PositionX <= -60) {
                          meteorito1PositionX = 1200;
                          numRandom1= (int) (Math.random() * 4) + 1;
                          if(numRandom1 == 1){
                              groupMeteorito1.setLayoutY(50);
                          }else if(numRandom1 == 2){
                              groupMeteorito1.setLayoutY(150);
                          }else if(numRandom1 == 3){
                              groupMeteorito1.setLayoutY(250);
                          }else if(numRandom1 == 4){
                              groupMeteorito1.setLayoutY(350);
                          }
                          
                      }
                      if (meteorito2PositionX <= -60) {
                          meteorito2PositionX = 1300;
                          numRandom1= (int) (Math.random() * 4) + 1;
                          if(numRandom1 == 1){
                              groupMeteorito2.setLayoutY(50);
                          }else if(numRandom1 == 2){
                              groupMeteorito2.setLayoutY(150);
                          }else if(numRandom1 == 3){
                              groupMeteorito2.setLayoutY(250);
                          }else if(numRandom1 == 4){
                              groupMeteorito2.setLayoutY(350);
                          }
                          
                      }
                  })
        );
        movimientoObjetos.setCycleCount(Timeline.INDEFINITE); 
        movimientoObjetos.play(); 
        
        scene.setOnKeyPressed((KeyEvent event) -> { // ESTO ES PARA EL MOVIMIENTO DEL ALGUILA, UNA DETECCION DE TECLAS
              switch(event.getCode()) {
                  case UP:
                      //PULSADA TECLA ARRIBA
                      aguilaCurrentSpeed = -5;
                          
                      break;
                  case DOWN:
                      //PULSADA TECLA ABAJO
                          aguilaCurrentSpeed = 5;
                      break;
                  
              }
        });
          scene.setOnKeyReleased((KeyEvent event) -> {
              aguilaCurrentSpeed = 0;
        });
        
        Timeline movimientoAguila= new Timeline(
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      aguilaPositionY += aguilaCurrentSpeed;
                      if(aguilaPositionY < 0){
                          aguilaPositionY = 0;
                      } else{
                          if(aguilaPositionY > 350){
                              aguilaPositionY = 350;
                          }
                      }
                      groupAguila.setLayoutY(aguilaPositionY);
                  })
        );
        movimientoAguila.setCycleCount(Timeline.INDEFINITE); 
        movimientoAguila.play(); 
        
        Timeline detectarColision = new Timeline( // CON TIMELINE VAMOS A DETECTAR SI HAY ALGUNA COLISION ENTRE LOS RECTANGULOS QUE HABIAMOS CREADO
                  new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                      Shape colisionMeteorito1 = Shape.intersect(rectAguila, rectMeteorito1);
                      boolean colisionVaciaMeteorito1 = colisionMeteorito1.getBoundsInLocal().isEmpty();
                      if(colisionVaciaMeteorito1 == false){
                          System.out.println("HAS COLISIONADO CON UN METEORITO1");
                          groupOveja.setLayoutX(3000);
                          groupMeteorito1.setLayoutX(3000);
                          groupMeteorito2.setLayoutX(3000);
                          resetGame();
                          cambiarPuntos();
                      }
                      Shape colisionMeteorito2 = Shape.intersect(rectAguila, rectMeteorito2);
                      boolean colisionVaciaMeteorito2 = colisionMeteorito2.getBoundsInLocal().isEmpty();
                      if(colisionVaciaMeteorito2 == false){
                          System.out.println("HAS COLISIONADO CON UN METEORITO2");
                          groupOveja.setLayoutX(3000);
                          groupMeteorito1.setLayoutX(3000);
                          groupMeteorito2.setLayoutX(3000);
                          resetGame();
                          cambiarPuntos();
                      }
                      Shape colisionOveja = Shape.intersect(rectAguila, rectOveja);
                      boolean colisionVaciaOveja = colisionOveja.getBoundsInLocal().isEmpty();
                      if(colisionVaciaOveja == false){
                          System.out.println("HAS COLISIONADO CON UNA OVEJA");
                          ovejaPositionX = - 40;
                          velocidad++; // Aumento de velocidad cuando colisiona el aguila con una oveja
                          score += 1 ; // Aumento puntuacion
                          cambiarPuntos();
                          System.out.println("Tienes "+score+" puntos");
                      }
                  })
          );
        detectarColision.setCycleCount(Timeline.INDEFINITE); 
        detectarColision.play(); 
    }    

 public static void main(String[] args) {
        launch();
    }

}

