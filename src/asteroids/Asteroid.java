package asteroids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Asteroid {
    final int sceneAltura = 600;
    final int sceneAnchura = 800;
    
    private double asteroidVelX;
    private double asteroidVelY;
    
    private double velocidadGiroAsteroid;
    
    private double asteroidPosY;
    private double asteroidPosX;
    
    Polygon forma = new Polygon();
    
    
    public Polygon getForma(){
        return forma;
    }
    
    public void asteroidePosY(){
             asteroidPosY = Math.random()* (sceneAltura);          
    }
    
    public void asteroidePosX( ){
        asteroidPosX = Math.random()* (sceneAnchura);
    }
    
    public void velocidadAsteroide(){
        asteroidVelX = Math.random()* (3 - (-1)) + (-1);
        asteroidVelY = Math.random() * (3 - (-1)) + (-1);
    }
    
    public void movimientoAsteroide(){       
        asteroidPosY += asteroidVelY;
        forma.setLayoutY(asteroidPosY);
        asteroidPosX += asteroidVelX;
        forma.setLayoutX(asteroidPosX);
    }
    
    public void crearFormaAsteroide(){        
        forma.getPoints().addAll(new Double[]{
             0.0, 0.0,
            -70.0, 60.0,
            -20.0, 120.0,
            0.0, 70.0,
            50.0, 100.0,
            60.0, 70.0,
            }); 
        forma.setFill(Color.RED);
        forma.setLayoutX(asteroidPosX);
        forma.setLayoutY(asteroidPosY);
    }
    
    public void bordes(){
        if (forma.getLayoutY()>600){
            asteroidPosY = 0;                
        }
        if (forma.getLayoutY()<-25){ 
            asteroidPosY = 600;  
        } 
        if (forma.getLayoutX()>800){ 
            asteroidPosX = 0;  
        }
        if (forma.getLayoutX()<0){
            asteroidPosX = 800;  
               }
    }
    
    public void giroAsteroide(){
         velocidadGiroAsteroid = 3;
         double anguloAsteroid = forma.getRotate();
         anguloAsteroid += velocidadGiroAsteroid;
         forma.setRotate(anguloAsteroid);
    }    
}
