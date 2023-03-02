package myLogics;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import myGraphics.Frame;

public class statePlay extends stateOfGame {

	private PlayRoom pr;
	private String informationText;

	public statePlay(Model model) {
		super(model);
		this.informationText = "Press Escape To Return To The Menu";
		this.pr = new PlayRoom(model);
		//pr.getFrankLocation().add(0.0,0.0);

	}

	public void keyIntake() {
		model.getMain().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent keyEvent) {
				keyPressed(keyEvent);
			}
		});

	}

	public PlayRoom getPr() {
		return pr;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getCode() + " i PlayState");

		if (key.getCode() == KeyCode.ESCAPE) {
			model.changeState(new stateMainMenu(model));
			Frame frame = new Frame(model);
			model.getMain().setScene(new Scene(frame));
		} else if (key.getCode() == KeyCode.LEFT  || key.getCode() == KeyCode.UP ||
				   key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.DOWN){
			pr.moveTo(key.getCode());
		
	
		}
	}

	@Override
	public void update() {
		pr.update();
		// TODO Auto-generated method stub

	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

}
