package Transitions;

import View.Components.Gyrocopter;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.net.URL;

public class GyrocopterAvatarAnimation extends Transition {
    public int counter = 0;
    public static URL gyrocopterAvatarURL;
    public static Gyrocopter gyrocopter;

    public GyrocopterAvatarAnimation(){
        this.setCycleCount(4);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 7);
        String address = String.valueOf(gyrocopterAvatarURL) + frame + ".png";
        gyrocopter.setFill(new ImagePattern(new Image(String.valueOf(address))));

        if(frame == 7) {
            counter++;
            if(counter >= this.getCycleCount()){
                gyrocopter.setFill(new ImagePattern(new Image(String.valueOf(gyrocopterAvatarURL) + "1.png")));
                Gyrocopter.onShield = false;
            }
        }
    }
}
