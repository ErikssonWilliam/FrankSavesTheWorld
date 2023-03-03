import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import myGraphics.Frame;
import myLogics.*;
public class Main extends Application{

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
		Frame frame = new Frame(model);
	
		scene = new Scene(frame);


		final double targetFps = 64.0;
		final double nanoPerUpdate = 100000000.0/targetFps;
		
		arg0.setScene(scene);		
		
		new AnimationTimer() {
			long lastUpdate = 0;
			@Override
			public void handle(long currentTime) {
				
				if((currentTime-lastUpdate) > nanoPerUpdate) {
					model.getCurrentState().update();
					
					lastUpdate = currentTime;
				}
				
			}
		}.start();
		
		
		arg0.show();

	}
}
