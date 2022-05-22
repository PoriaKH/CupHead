package Transitions;

import View.Components.Bullet;
import View.Components.Gyrocopter;
import View.Components.MiniBoss;
import View.Components.Rocket;
import View.Game;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import static View.Components.MiniBoss.miniBossURL;
import static View.Components.MiniBoss.root;

public class MiniBossFly extends Transition {
    public static Gyrocopter gyrocopter;

    public MiniBoss miniBoss;

    public MiniBossFly(MiniBoss miniBoss,int frameNumber){
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.miniBoss = miniBoss;
        String address = String.valueOf(miniBossURL) + 1 + ".png";
        this.miniBoss.setFill(new ImagePattern(new Image(address)));

    }
    @Override
    protected void interpolate(double frac) {
        miniBoss.move();

        if(!Gyrocopter.onShield) {
            if (this.miniBoss.hasCollisionWithGyro(gyrocopter)) {
                Gyrocopter.onShield = true;
                Game.health -= 1;
                Game.hp.setText(Integer.toString(Game.health));
                this.stop();
                miniBoss.remove();
                GyrocopterAvatarAnimation gyrocopterAvatarAnimation = new GyrocopterAvatarAnimation();
                gyrocopterAvatarAnimation.play();
            }
        }
        for(Bullet bullet : Bullet.bullets){
            if(this.miniBoss.hasCollisionWithBullet(bullet)){
                bullet.remove();
                this.miniBoss.health--;
                if(this.miniBoss.health <= 0){
                    this.stop();
                    miniBoss.remove();
                }
                break;
            }
        }
        for(Rocket rocket : Rocket.rockets){
            if(this.miniBoss.hasCollisionWithRocket(rocket)){
                rocket.remove();
                this.stop();
                miniBoss.remove();
                break;
            }
        }
    }
}
