package com.github.tarcio2020.config;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("main/dbconfig.properties")) {
            if (input == null) {
                throw new IOException("Arquivo dbconfig.properties não encontrado no classpath.");
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Falha ao carregar o arquivo de configuração do banco de dados.");
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar a configuração do banco de dados.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("jdbc:postgresql://localhost:5432/postgres");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");

        try {
            Class.forName(driver); // Garante que o driver seja carregado
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do banco de dados não encontrado.", e);
        }

        return DriverManager.getConnection(url, username, password);
    }
}
