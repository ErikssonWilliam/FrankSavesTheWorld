import javafx.application.Application;
import javafx.stage.Stage;
import myLogics.*;
public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setHeight(1000);
		arg0.setWidth(1500);
		MainMenu m = new MainMenu(arg0);
		m.run();

//		Scene mainFrame = new Scene(m);
//		arg0.setScene(mainFrame);
//		arg0.show();
	}
}
