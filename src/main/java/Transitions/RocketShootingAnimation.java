package Transitions;

import View.Components.FlyingBoss;
import View.Components.Rocket;
import View.Game;
import javafx.animation.Transition;
import javafx.util.Duration;

public class RocketShootingAnimation extends Transition {
    public static FlyingBoss flyingBoss;
    public Rocket rocket;

    public RocketShootingAnimation(Rocket rocket){
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.rocket = rocket;
    }
    @Override
    protected void interpolate(double frac) {
        rocket.move(3);

        if(this.rocket.hasCollision(flyingBoss)){
            flyingBoss.health -= Game.damage * 3;
            this.stop();
            rocket.remove();
            //TODO... check for boss health
        }
    }
}
