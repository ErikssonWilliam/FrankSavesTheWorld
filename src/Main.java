import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
//import myGraphics.Frame;
import myLogics.*;

/**
 * Main starts a new game of "Frank...Saves the world!" it also updates the
 * playing-state of the model
 * 
 * @author wiler441
 *
 */
public class Main extends Application {

	private Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Frank...Saves The World");
		arg0.setHeight(1000);
		arg0.setWidth(1520);

		Model model = new Model(arg0);
		HBox frame = new HBox();
		MainMenu mm = new MainMenu(model);
		
		frame.getChildren().add(mm);
		scene = new Scene(frame);

		final double targetFps = 64.0;
		final double nanoPerUpdate = 100000000.0 / targetFps;

		arg0.setScene(scene);
	
		/***
		 * Updates PlayState when the playstate is active
		 */
		new AnimationTimer() {
			long lastUpdate = 0;

			@Override
			public void handle(long currentTime) {
				if (model.getPlayState() != null) {
					if ((currentTime - lastUpdate) > nanoPerUpdate) {
						model.getPlayState().update();
						lastUpdate = currentTime;
					}
				}
			}
		}.start();

		arg0.show();
	}
}
