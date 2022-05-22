package Transitions;

import View.Components.Bullet;
import View.Components.FlyingBoss;
import View.Game;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import static View.Components.Bullet.bulletURL;

public class ShootAnimation extends Transition {
    public static FlyingBoss flyingBoss;
    public Bullet bullet;

    public ShootAnimation(Bullet bullet){
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.bullet = bullet;
        this.bullet.setFill(new ImagePattern(new Image(String.valueOf(bulletURL))));
    }

    @Override
    protected void interpolate(double frac) {
        bullet.move(14);

        if(this.bullet.hasCollision(flyingBoss)){
            flyingBoss.health -= Game.damage;
            this.stop();
            bullet.remove();
            //TODO... check for boss health
        }
    }
}
