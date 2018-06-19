package asteroids;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class MainAsteroid extends Application {
    final int sceneAltura = 600;
    final int sceneAnchura = 800;
    int navePosY = sceneAltura/2;
    int navePosX = sceneAnchura /2;
    double velocidadGiroBala;
    Bala bala;
    Asteroid asteroid;
    private ArrayList<Asteroid> arrayListasteroid;
    private ArrayList<Bala> arrayListBala;
    
    @Override
    public void start(Stage primaryStage) {

//        Asteroid asteroid = new Asteroid();
        Nave naveFuego = new Nave();
        //Color aleatorio 
        Pane root = new Pane(); //Panel       
        Scene scene = new Scene (root, sceneAnchura, sceneAltura); //Crear pantalla
        primaryStage.setTitle("Asteroids");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.getStylesheets().add("AsteroidCSS.css");
        
        //Nave
        naveFuego.returnPaneNaveFuego();   
        naveFuego.crearFuego();
        naveFuego.crearNave();
        root.getChildren().add(naveFuego.returnPaneNaveFuego());
        naveFuego.tamañoPaneYañadirNaveFuego(); 
        naveFuego.posPaneNaveFuego();
        naveFuego.fuegoInvisible(); //poner fuego en invisible      
        scene.getStylesheets().add("asteroids/AsteroidCSS.css");
        arrayListasteroid = new ArrayList<Asteroid>();
        for (int i = 0; i < 5; i++){                
            Asteroid asteroid = new Asteroid();
            asteroid.crearFormaAsteroide();
            arrayListasteroid.add(asteroid);
            asteroid.velocidadAsteroide();
            asteroid.asteroidePosY();
            asteroid.asteroidePosX();
            root.getChildren().add(asteroid.getForma());            
        }
        arrayListBala = new ArrayList<Bala>(); //Crear Lista balas
        
        scene.setOnKeyPressed((KeyEvent event) -> { 
            switch(event.getCode()){
                case W:                    
                    naveFuego.velocidadNave();
                    naveFuego.fuegoVisible();
                    break;               
                case D:
                    naveFuego.giroDerecha();
                    break;
                case A:
                    naveFuego.giroIzquierda();
                    break;
                    case SPACE: 
                        bala = new Bala(); // crear bala
                        root.getChildren().add(bala.getBala()); // añadirla al root
                        arrayListBala.add(bala);
                        bala.pulsarEspacio(naveFuego.getAnguloGrupo(), naveFuego.getNavePosX(), 
                        naveFuego.getNavePosY());
                        bala.balaVisible();
                        break;
                 
                }
          
 
        });

        //Cuando sueltas una tecla pulsada 
        scene.setOnKeyReleased((KeyEvent event) -> {          
                if(event.getCode() == KeyCode.W ||event.getCode() 
                == KeyCode.A ||event.getCode() == KeyCode.D){
                    naveFuego.pararGirorNave(); 
                    naveFuego.fuegoInvisible();
                }         
        });   
        //crear la animacion
        AnimationTimer movimimientoNave; 
        movimimientoNave = new AnimationTimer() {       
            @Override
            public void handle(long now) {
                for(int i=0; i<arrayListasteroid.size(); i++){
                    Asteroid getAsteroid =  arrayListasteroid.get(i);
                    getAsteroid.giroAsteroide();
                    getAsteroid.movimientoAsteroide();
                    getAsteroid.bordes();
                }                      
                naveFuego.calcularAngulo();
                naveFuego.calcularAngulo();
                //movimiento nave y fuego
                naveFuego.movimientoPaneNaveFuego();
                if(arrayListasteroid.isEmpty()){
                    for (int i = 0; i < 5; i++){                
                    Asteroid asteroid = new Asteroid();
                    asteroid.crearFormaAsteroide();
                    arrayListasteroid.add(asteroid);
                    asteroid.velocidadAsteroide();
                    asteroid.asteroidePosY();
                    asteroid.asteroidePosX();
                    root.getChildren().add(asteroid.getForma());            
        }
                }
                //MovimientoBala
                for(int i=0; i<arrayListBala.size(); i++){
                    Bala balaGuardada =arrayListBala.get(i);
                    balaGuardada.movimientoBala();
                    if ((balaGuardada.posBalaX > 800) || (balaGuardada.posBalaX < 0)
                    ||(balaGuardada.posBalaY < 0)|| (balaGuardada.posBalaY > 800)){
                            arrayListBala.remove(balaGuardada);
                            balaGuardada.formaBala.setVisible(false);
                            root.getChildren().remove(balaGuardada.formaBala);
                    }
                
                    for (int j = 0; j < arrayListasteroid.size(); j++){
                        Asteroid asteroidGuardado = arrayListasteroid.get(j);
                        Shape colisionBalaAsteroide = Shape.intersect(asteroidGuardado.forma, balaGuardada.formaBala);
                        boolean colisionVaciaBalaAsteroide = colisionBalaAsteroide.getBoundsInLocal().isEmpty();
                            if(colisionVaciaBalaAsteroide == false){
                                    arrayListasteroid.remove(asteroidGuardado);
                                    asteroidGuardado.forma.setVisible(false);
                                    root.getChildren().remove(asteroidGuardado.forma);
                                    
                        }
                    }
                }
            //Giros
            naveFuego.calcularGiroNave();                             
            naveFuego.naveBordes();                  
            }           
       };
        movimimientoNave.start();     
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
