package com.bhaktiBhraman.app.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource; // Core Java SQL framework package
import java.sql.Connection;

@RestController
public class DatabaseCheckController {

    private final DataSource dataSource;

    // 🧠 Why we did it: Constructor injection to request Spring's managed connection pool.
    // ⚙️ What it does: Tells Spring to pass the database driver instance here when the app starts.
    public DatabaseCheckController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 🧠 Why we did it: Creates a basic, public HTTP GET testing gateway.
    // ⚙️ What it does: Safely opens a quick connection channel to MySQL and reads its version name.
    @GetMapping("/api/v1/public/db-check")
    public String checkDbConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Connected successfully to: " + connection.getMetaData().getDatabaseProductVersion();
        } catch (Exception e) {
            return "Database Connection Failed: " + e.getMessage();
        }
    }
}