package View.Components;

import View.Game;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Random;

public class FlyingBoss extends Rectangle {
    public static URL bossFlyingURL;
    public static boolean isDying = false;

    public int health;


    public double speed = 3;
    public double Vx = speed;
    public double Vy = speed;

    public int isMovingDown;// 1 -> yes 0 - > speed = 0 -1 - > moving up

    public boolean isMovingLeft = true;

    public FlyingBoss(){
//        super(1080,150,180);
        super(980,0,300,300);
        isMovingDown = 1;
        health = 100000;
    }

    public void move(){
        if(Game.phaseOne) {
            if (this.getY() <= -50)
                isMovingDown = 1;
            if (this.getY() + this.getHeight() >= 730)
                isMovingDown = -1;

            if (isMovingDown == 1)
                this.setY(this.getY() + 2);
            if (isMovingDown == -1)
                this.setY(this.getY() - 2);
        }
        else if(Game.phaseTwo) {
            if(this.getY() <= -50){
                if(Vy < 0)
                    Vy *= -1;

                Random rand = new Random();
                int upperbound = 3;
                int int_random = rand.nextInt(upperbound);
                if(int_random == 0)
                    Vx = 0;
                if(int_random == 1)
                    Vx = speed;
                if(int_random == 2)
                    Vx = -speed;
            }
            if(this.getY() + this.getHeight() >= 730){
                if(Vy > 0)
                    Vy *= -1;

                Random rand = new Random();
                int upperbound = 3;
                int int_random = rand.nextInt(upperbound);
                if(int_random == 0)
                    Vx = 0;
                if(int_random == 1)
                    Vx = speed;
                if(int_random == 2)
                    Vx = -speed;
            }
            if(this.getX() >= 980){
                if(Vx > 0)
                    Vx *= -1;

                Random rand = new Random();
                int upperbound = 3;
                int int_random = rand.nextInt(upperbound);
                if(int_random == 0)
                    Vy = 0;
                if(int_random == 1)
                    Vy = speed;
                if(int_random == 2)
                    Vy = -speed;
            }
            if(this.getX() <= 300){
                if(Vx < 0)
                    Vx *= -1;

                Random rand = new Random();
                int upperbound = 3;
                int int_random = rand.nextInt(upperbound);
                if(int_random == 0)
                    Vy = 0;
                if(int_random == 1)
                    Vy = speed;
                if(int_random == 2)
                    Vy = -speed;
            }
            this.setY(this.getY() + Vy);
            this.setX(this.getX() + Vx);
        }
        else if(Game.phaseThree){
            if(this.getX() <= 0)
                isMovingLeft = false;
            if(this.getX() >= 1280 - this.getWidth())
                isMovingLeft = true;

            if(isMovingLeft){
                this.setX(this.getX() - 4);
            }
            else{
                this.setX(this.getX() + 4);
            }
        }
    }

    public boolean hasCollision(Gyrocopter gyrocopter){
        return gyrocopter.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
