package asteroids;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Nave {
    private final int sceneAltura = 600;
    private final int sceneAnchura = 800;
    
    private double velocidadNaveY = 0;
    private double velocidadNaveX = 0;
    
    private int velocidadFuegoY = 0;
    private int velocidadFuegoX = 0;
    
    private int navePosY = sceneAltura/2;
    private int navePosX = sceneAnchura /2;
    
    private double velocidadGiroNave;
    
    private double xpaneFuegoNaveX = 0;
    private double xpaneFuegoNaveY = 0;
    
    private double direccionXNave;
    private double direccionYNave;
    
    private double anchuraPane = 60;
    private double alturaPane = 20;
    
    private double anguloGrupo;
    
    Polygon formaNave = new Polygon(); //crear nave
    Polygon formaFuego = new Polygon();  //crear fuego
    Pane paneNaveFuego = new Pane(); //Pane para que el fuego se mueva con la nave
    public void calcularAngulo(){
        anguloGrupo = paneNaveFuego.getRotate();        
    }  
    public void tamañoPaneYañadirNaveFuego(){
        paneNaveFuego.setMinWidth(anchuraPane);//establecer tamaño minimo al pane naveFuego
        paneNaveFuego.setMinHeight(5);
        paneNaveFuego.getChildren().add(formaFuego); //añadir al pane
        paneNaveFuego.getChildren().add(formaNave); //añadir al pane
        
    }
    
    public void crearNave(){
        formaNave.setFill(Color.BLACK);
        formaNave.getPoints().addAll(new Double[]{ //tamaño nave
            59.0,alturaPane/2,
            30.0, 0.0,
            30.0, 20.0 });
    }
    public void crearFuego(){
        formaFuego.getPoints().addAll(new Double[]{ // tamaño fuego
            15.0, 10.0,
            30.0, 0.0,
            30.0, 20.0 });
        formaFuego.setFill(Color.BLUE);
    }
    public Pane returnPaneNaveFuego(){
        return paneNaveFuego;
    }

    public void fuegoInvisible(){
         formaFuego.setVisible(false);
    }
    public void fuegoVisible(){
         formaFuego.setVisible(true);
    }
    public void velocidadNave(){
        direccionXNave = Math.cos(Math.toRadians(anguloGrupo));
        direccionYNave = Math.sin(Math.toRadians(anguloGrupo));
        if ((velocidadNaveX == 0) ||(velocidadNaveY == 0)){
            velocidadNaveY += 1 * direccionYNave;
            velocidadNaveX += 1 * direccionXNave;
        } else {
            velocidadNaveY += 0.5 * direccionYNave;
            velocidadNaveX += 0.5 * direccionXNave; 
        }
    }
    public void movimientoPaneNaveFuego(){       
            anguloGrupo += velocidadGiroNave;
            paneNaveFuego.setRotate(anguloGrupo);
            navePosY +=  velocidadNaveY;
            paneNaveFuego.setLayoutY(navePosY);               
            navePosX += velocidadNaveX;
            paneNaveFuego.setLayoutX(navePosX);
            anguloGrupo %= 360;
    }
    public void naveBordes(){
        if (paneNaveFuego.getLayoutY() > 600){ 
            navePosY = 0;                                     
        }                 
        if (paneNaveFuego.getLayoutY() < 0){ 
            navePosY = 600;
        }                 
        if (paneNaveFuego.getLayoutX() > 800){ 
            navePosX = 0;
        }                 
        if (paneNaveFuego.getLayoutX() < 0){ 
            navePosX = 800;
        }                    

    }
    public void calcularGiroNave(){
        anguloGrupo += velocidadGiroNave;
        paneNaveFuego.setRotate(anguloGrupo);
        anguloGrupo %= 360;
    }
    public void posPaneNaveFuego(){
        paneNaveFuego.setLayoutX(navePosX); //Poner el pane naveFuego en el centro
        paneNaveFuego.setLayoutY(navePosY); // poner el panel naveFuego en el centro
    }
    public void giroDerecha(){
        velocidadGiroNave = 2;
    }
    public void giroIzquierda(){
        velocidadGiroNave = -2;
    }
    public void pararGirorNave(){
        velocidadGiroNave = 0;
    }
    public int getNavePosY(){
        return navePosY;
    }
    public int getNavePosX(){
        return navePosX;
    }
    public double getDireccionNaveX(){
        return direccionXNave;
    }
    public double getDireccionNaveY(){
        return direccionYNave;
    }
    public double getAnguloGrupo(){
        return anguloGrupo;
    }   
}

