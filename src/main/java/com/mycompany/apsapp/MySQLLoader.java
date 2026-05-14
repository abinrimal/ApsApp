/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apsapp;

import java.sql.*;
import java.util.List;

/**
 *
 * @author abin
 */
public class MySQLLoader {
    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/apsWarehouse", "root", "");
    }

    public void loadAccounts(List<List<String>> accounts) {
        try (Connection conn = connect()) {
            String query = "INSERT INTO accounts " +
                    "(account, sector, year_established, revenue, employees, office_location, subsidiary_of) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);

            for (List<String> account : accounts) {

                stmt.setString(1, account.get(0));
                stmt.setString(2, account.get(1));
                stmt.setInt(3, Integer.parseInt(account.get(2)));
                stmt.setDouble(4, Double.parseDouble(account.get(3)));
                stmt.setInt(5, Integer.parseInt(account.get(4)));
                stmt.setString(6, account.get(5));

                // handle optional field safely
                if (account.size() > 6) {
                        stmt.setString(7, account.get(6));
                    } else {
                        stmt.setNull(7, java.sql.Types.VARCHAR);
                    }

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void loadSalesTeams(List<List<String>> rows) {
        try (Connection conn = connect()) {

            String query = "INSERT INTO sales_teams (sales_agent, manager, regional_office) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            for (List<String> row : rows) {
                stmt.setString(1, row.get(0));
                stmt.setString(2, row.get(1));
                stmt.setString(3, row.get(2));
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
