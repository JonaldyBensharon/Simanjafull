package com.simanja.controller;

import com.simanja.model.User;
import com.simanja.service.ApiAuthService;
import com.simanja.util.SceneManager;
import com.simanja.util.SessionManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller untuk Login Page
 * Demonstrasi: Validasi input, Security (autentikasi)
 */
public class LoginController {

    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Label lblError;
    @FXML private Hyperlink linkDaftar;

    private final ApiAuthService authService =
            new ApiAuthService();

    @FXML
    public void initialize() {
        lblError.setVisible(false);
    }

    @FXML
    private void handleLogin() {

        lblError.setVisible(false);

        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();

        try {

            User user =
                    authService.login(email, password);

            SessionManager.getInstance().login(user);

            SceneManager.switchTo("dashboard");

        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                showError(e.getMessage());
            });
        }
    }

    @FXML
    private void handleDaftar() {
        SceneManager.switchTo("register");
    }

    @FXML
    private void handleKembali() {
        SceneManager.switchTo("landing");
    }

    private void showError(String message) {
        lblError.setText(message);
        lblError.setVisible(true);
    }
}
