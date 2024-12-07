package com.github.tarcio2020.controller;

import com.github.tarcio2020.dao.UserDAO;
import com.github.tarcio2020.model.User;
import com.github.tarcio2020.view.FormLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private FormLogin formLogin;
    private UserDAO userDAO;

    public LoginController() {
        userDAO = new UserDAO();
        formLogin = new FormLogin(new LoginAction());
        formLogin.setVisible(true);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = formLogin.getUsername();
            String password = formLogin.getPassword();

            User user = new User(username, password);
            if (userDAO.authenticate(user)) {
                JOptionPane.showMessageDialog(formLogin, "Login bem-sucedido!");
                formLogin.dispose();
            } else {
                JOptionPane.showMessageDialog(formLogin, "Usuário ou senha inválidos.");
            }
        }
    }
}
