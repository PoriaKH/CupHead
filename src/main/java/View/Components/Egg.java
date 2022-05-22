package View.Components;

import Transitions.BossFlyingAnimation;
import Transitions.FlyingBossPhaseTwoAnimation;
import View.Game;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import java.util.Date;

import static Transitions.EggShootingAnimation.eggURL;

public class Egg extends Circle {
    public static ArrayList<Egg> allEggs = new ArrayList<>();
    public FlyingBoss flyingBoss;

    public int number;
    public double theta1 = 90;
    public double theta2 = 180;
    public double rotationRadius = 150;
    public boolean increasingRadius = true;

    public static Pane root;

    public Egg(FlyingBoss flyingBoss,int number){
        super(flyingBoss.getX() - 50 / 2,flyingBoss.getY() + flyingBoss.getHeight() / 2 + 20, 80);
        this.flyingBoss = flyingBoss;
        this.number = number;
        this.setFill(new ImagePattern(new Image(String.valueOf(eggURL))));

        if(Game.phaseTwo){
            if(number == 1){
                this.setRadius(42);
                this.setCenterX(flyingBoss.getX() + 70);
                this.setCenterY(flyingBoss.getY() - 10);
            }
            else if(number == 2){
                this.setRadius(42);
                this.setCenterX(flyingBoss.getX() + 70);
                this.setCenterY(flyingBoss.getY() + 230);
            }
            allEggs.add(this);
        }
        if(Game.phaseThree){
            if(number == 100){
                this.setRadius(120);
                this.setCenterX(flyingBoss.getX() + flyingBoss.getWidth() - 170);
                this.setCenterY(flyingBoss.getY() - flyingBoss.getHeight() + 100);
            }
        }

        root.getChildren().add(this);
    }
    public void move(){
        if(Game.phaseOne) {
            this.setCenterX(this.getCenterX() - 11);
        }
        else if(Game.phaseTwo){
            boolean phaseTwoEggFlag = false;
            for(Egg egg : FlyingBossPhaseTwoAnimation.eggs){
                if(this == egg)
                    phaseTwoEggFlag = true;
            }
            if(phaseTwoEggFlag) {
                if(this.number == 1) {
                    this.theta1 += 0.05;
                    this.setCenterX(flyingBoss.getX() + flyingBoss.getWidth() / 2 + rotationRadius * Math.cos(theta1));
                    this.setCenterY(flyingBoss.getY() + flyingBoss.getHeight() / 2 - rotationRadius * Math.sin(theta1));
                }
                if(this.number == 2){
                    this.theta2 += 0.05;
                    this.setCenterX(flyingBoss.getX() + flyingBoss.getWidth() / 2 + rotationRadius * Math.cos(theta2));
                    this.setCenterY(flyingBoss.getY() + flyingBoss.getHeight() / 2 - rotationRadius * Math.sin(theta2));
                }
                if(rotationRadius <= 150){
                    increasingRadius = true;
                }
                else if(rotationRadius >= 300){
                    increasingRadius = false;
                }

                if(increasingRadius){
                    rotationRadius += 0.5;
                }
                else{
                    rotationRadius -= 0.5;
                }
            }
            else {
                this.setCenterX(this.getCenterX() - 11);
            }
        }
        else if(Game.phaseThree){
            this.setCenterY(this.getCenterY() - 11);
        }
    }

    public boolean hasCollision(Gyrocopter gyrocopter){
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
