package Transitions;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static View.Components.FlyingBoss.bossFlyingURL;
import static View.StartGame.backgroundURL;

public class BackgroundAnimation extends Transition {
    public static Pane root;

    public BackgroundAnimation(){
        this.setCycleDuration(Duration.millis(1));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double frac) {
        int frame = (int) Math.floor(frac * 7);
        String address = String.valueOf(backgroundURL) + frame + ".png";
        root.setStyle("-fx-background-image: url('" + address + "'); " +
                "-fx-background-position: center; " +
                "-fx-background-radius: 1800;" +
                "-fx-background-repeat: stretch;");
    }
}
