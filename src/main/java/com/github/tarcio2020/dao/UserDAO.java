package com.github.tarcio2020.dao;

import com.github.tarcio2020.config.DatabaseConfig;
import com.github.tarcio2020.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean authenticate(User user) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retorna true se encontrar o usuário
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
