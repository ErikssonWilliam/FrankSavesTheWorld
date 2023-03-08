package myGraphics;

import javafx.scene.layout.HBox;
import myLogics.Model;
import myLogics.stateMainMenu;
import myLogics.statePlay;
import myLogics.stateResult;
import myLogics.stateTextRoom;

public class Frame extends HBox {

	public Frame(Model model) {

		if (model.getCurrentState() instanceof stateTextRoom) {
			this.getChildren().add(model.getCurrentState().getTr());
		} else if (model.getCurrentState() instanceof stateMainMenu) {
			this.getChildren().add(model.getCurrentState().getMm());
		} else if (model.getCurrentState() instanceof statePlay) {
			this.getChildren().add(model.getCurrentState().getPr());
		} else if (model.getCurrentState() instanceof stateResult) {
			this.getChildren().add(model.getCurrentState().getSr());
		}
	}

}
