package View.Components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;


public class Bullet extends Rectangle {

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static Gyrocopter gyrocopter;
    public static URL bulletURL;
    public static Pane root;

    public Bullet(){
        super(gyrocopter.getX() + Gyrocopter.imageWidth + 50,gyrocopter.getY() + Gyrocopter.imageHeight / 2 + 50,36,10);
        Image image = new Image(String.valueOf(bulletURL));
        ImageView imageView = new ImageView(image);

        root.getChildren().add(this);
        bullets.add(this);
    }

    public void move(double d){
        this.setX(this.getX() + d);
    }
    public boolean hasCollision(Rectangle boss){
        return boss.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public void remove() {
        bullets.remove(this);
        root.getChildren().remove(this);
    }
}
