package View.Components;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javax.print.DocFlavor;
import java.net.URL;

public class MiniBoss extends Rectangle {
    public static URL miniBossURL;

    public int health = 3;

    public static Pane root;
    public boolean isUp;

    public MiniBoss(boolean isUp, int frameNumber){
        super(1280,20,80,80);
        this.isUp = isUp;
        if(!isUp){
            this.setY(600);
        }
        if(frameNumber == 1){
            this.setX(this.getX() + this.getWidth());
        }
        else if(frameNumber == 2){
            this.setX(this.getX() + this.getWidth() + 100);
        }
        else if(frameNumber == 3){
            this.setX(this.getX() + this.getWidth() + 200);
        }
        else if(frameNumber == 4){
            this.setX(this.getX() + this.getWidth() + 300);
        }
        root.getChildren().add(this);

    }
    public void move(){
        this.setX(this.getX() - 5);
    }
    public boolean hasCollisionWithGyro(Rectangle gyrocopter){
        return gyrocopter.getBoundsInParent().intersects(this.getLayoutBounds());
    }
    public boolean hasCollisionWithBullet(Bullet bullet){
        return bullet.getBoundsInParent().intersects(this.getLayoutBounds());
    }
    public boolean hasCollisionWithRocket(Rocket rocket){
        return rocket.getBoundsInParent().intersects(this.getLayoutBounds());
    }
    public void remove(){
        root.getChildren().remove(this);
    }
}
