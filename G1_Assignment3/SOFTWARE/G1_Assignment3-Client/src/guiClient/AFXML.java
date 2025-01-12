package guiClient;

import client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * all boundaries extend this
 * 
 * @version Final
 * @author Elroy, Lior
 */
public abstract class AFXML {

	@FXML
	protected AnchorPane titleBar;
	@FXML
	protected Button btnMini;
	@FXML
	protected Button btnExit;

	protected AnchorPane visibleNow;
	protected ClientController controller;

	/**
	 * changes the window after server returned an answer to client's request
	 * 
	 * @param lastMsgFromServer
	 */
	public abstract void callAfterMessage(Object lastMsgFromServer);

	/**
	 * opens error popup window, all windows use this
	 * 
	 * @param title
	 * @param msg
	 */
	public void openErrorAlert(String title, String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		ButtonType buttonTypeOne = new ButtonType("OK");
		alert.getButtonTypes().setAll(buttonTypeOne);
		alert.show();
		final Button btn = (Button) alert.getDialogPane().lookupButton(buttonTypeOne);
		btn.setOnAction(event -> {
			alert.hide();
		});
	}

	/**
	 * opens confirmation popup window, all windows use this
	 * 
	 * @param title
	 * @param msg
	 */
	public void openConfirmationAlert(String title, String msg) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		ButtonType buttonTypeOne = new ButtonType("OK");
		alert.getButtonTypes().setAll(buttonTypeOne);
		alert.show();
		final Button btn = (Button) alert.getDialogPane().lookupButton(buttonTypeOne);
		btn.setOnAction(event -> {
			alert.hide();
		});
	}

	/* methods for functionality of the top bar close, minimize, drag window */
	private double x = 0;
	private double y = 0;

	/**
	 * button listener for minimize button
	 * 
	 * @param event
	 */
	@FXML
	public void minimizeTopBar(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	/**
	 * listener for topbar
	 * 
	 * @param event
	 */
	@FXML
	public void clickOnTopBar(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		x = stage.getX() - event.getScreenX();
		y = stage.getY() - event.getScreenY();
	}

	/**
	 * listener for topbar
	 * 
	 * @param event
	 */
	@FXML
	public void dragTopBar(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() + x);
		stage.setY(event.getScreenY() + y);
	}

}
