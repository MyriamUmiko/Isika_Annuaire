package application;

import java.io.IOException;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InterfaceLogin {

	@FXML
	private Button btnConnection;

	@FXML
	private TextField txtLogin;

	@FXML
	private TextField txtPassword;

	@FXML
	public Label loginMessageLabel;

	
	public void userLogin(ActionEvent event) throws IOException {
		btnConnection(null);
	}
	
	
	@FXML
	private void btnConnection(Event e) throws IOException {
		
		Main m = new Main();

		String login = txtLogin.getText();
		String password = txtPassword.getText();

		if (login.toString().equals("admin") && password.toString().equals("password")) {
			Main.user.setAdmin(true);
			m.changeScene("InterfaceListeStagiaireAdmin.fxml");


			System.out.println(login + password);

		} else if (login.toString().equals("personnel") && password.toString().equals("password")) {
			Main.user.setAdmin(false);
			m.changeScene("InterfaceListeStagiairePersonnel.fxml");


			System.out.println(login + password);
		}

		else {
			loginMessageLabel.setText("Le login ou le mode de passe est incorrect.\n Veuillez réessayez.");

		}

		reinitialisationForm();

	}

	private void reinitialisationForm() {
		txtLogin.clear();
		txtPassword.clear();

	}

//	https://projet-isika.com/

}
