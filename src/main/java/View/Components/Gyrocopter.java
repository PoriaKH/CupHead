package View.Components;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static View.Game.imageURL;

public class Gyrocopter extends Rectangle {
    public static boolean onShield = false;
    public static boolean onRocket = false;

    public static boolean isRocketReady = true;

    public static double imageWidth = 80;
    public static double imageHeight = 80;

    public static Pane root;


    public Gyrocopter(Pane root){
        super(0,0,80,80);
        Gyrocopter.root = root;
    }

    public void moveRight() {
        if(!hitRight())
            this.setX(this.getX() + 20);
    }
    public void moveLeft() {
        if(!hitLeft())
            this.setX(this.getX() - 20);
    }
    public void moveUp(){
        if(!hitUp())
            this.setY(this.getY() - 20);
    }
    public void moveDown(){
        if(!hitDown())
            this.setY(this.getY() + 20);
    }

    public boolean hitUp(){
        if(this.getY() <= -25)
            return true;
        return false;
    }
    public boolean hitDown(){
        if(this.getY() >= 600)
            return true;
        return false;
    }
    public boolean hitRight(){
        if(this.getX() >= 1220 - this.imageWidth)
            return true;
        return false;
    }
    public boolean hitLeft(){
        if(this.getX() <= -40)
            return true;
        return false;
    }
}
