package Transitions;

import View.Components.FlyingBoss;
import View.Components.Gyrocopter;
import View.Game;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;

import static Transitions.EggShootingAnimation.eggURL;

public class FlyingBossPhaseTwoAttack extends Transition {
    public static Gyrocopter gyrocopter;
    public static URL shootURL;
    public Circle circle;
    public static FlyingBoss flyingBoss;
    public static Pane root;

    public FlyingBossPhaseTwoAttack(){
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        circle = new Circle(flyingBoss.getX() + flyingBoss.getWidth() / 2,flyingBoss.getY() + flyingBoss.getHeight() / 2,30);
        circle.setFill(new ImagePattern(new Image(String.valueOf(shootURL))));

        root.getChildren().add(circle);
    }
    @Override
    protected void interpolate(double frac) {
        circle.setCenterX(circle.getCenterX() - 8);
        if(!Gyrocopter.onShield) {
            if (hasCollision()) {
                Gyrocopter.onShield = true;
                Game.health -= 1;
                Game.hp.setText(Integer.toString(Game.health));
                this.stop();
                root.getChildren().remove(circle);
                GyrocopterAvatarAnimation gyrocopterAvatarAnimation = new GyrocopterAvatarAnimation();
                gyrocopterAvatarAnimation.play();
            }
        }

    }
    public boolean hasCollision(){
        return gyrocopter.getBoundsInParent().intersects(circle.getLayoutBounds());
    }
}
