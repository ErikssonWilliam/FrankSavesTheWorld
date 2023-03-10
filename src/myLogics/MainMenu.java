package myLogics;

import java.io.FileNotFoundException;
import javafx.scene.layout.HBox;
import myGraphics.Logo;

/*'
 * box that builds the main menu
 */
public class MainMenu extends HBox {

	public MainMenu(Model model) {
		try {
			Logo logo = new Logo(model);
			this.getChildren().add(logo);

			Options options = new Options(model);
			this.getChildren().add(options);
		} catch (Exception FileNotFoundException) {
		}
	}
}