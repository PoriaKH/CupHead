package View.Components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Rocket extends Circle {
    public static ArrayList<Rocket> rockets = new ArrayList<>();
    public static Gyrocopter gyrocopter;
    public static Pane root;

    private double Vx;
    private double Vy;
    private double x0;

    public Rocket(){
        super(gyrocopter.getX() + Gyrocopter.imageWidth + 65,gyrocopter.getY() + Gyrocopter.imageHeight / 2 + 53,13);

        this.Vy = 0;
        this.Vx = 15;
        this.x0 = this.getCenterX();

        this.setFill(Color.BROWN);
        root.getChildren().add(this);
        rockets.add(this);
    }

    public boolean hasCollision(Rectangle boss){
        return boss.getBoundsInParent().intersects(this.getLayoutBounds());
    }
    public void remove() {
        rockets.remove(this);
        root.getChildren().remove(this);
    }
    public void move(double gravity){
        this.setCenterX(this.getCenterX() + this.Vx);
        this.setCenterY(this.getCenterY() + gravity * (this.getCenterX() - this.x0) / this.Vx);
    }
}
