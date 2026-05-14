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
    
    public void loadProducts(List<List<String>> products) {
        try (Connection conn = connect()) {

            String query = "INSERT INTO products (product, series, sales_price) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            for (List<String> product : products) {

                // product name
                stmt.setString(1, product.get(0));

                // series
                stmt.setString(2, product.get(1));

                // sales_price (nullable safe)
                if (product.get(2) != null && !product.get(2).isEmpty()) {
                    stmt.setDouble(3, Double.parseDouble(product.get(2)));
                } else {
                    stmt.setNull(3, java.sql.Types.DOUBLE);
                }

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadSalesPipeline(List<List<String>> sales) {
        try (Connection conn = connect()) {

            String query = "INSERT INTO sales_pipeline " +
                    "(opportunity_id, sales_agent, product, account, deal_stage, engage_date, close_date, close_value) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);

            for (List<String> row : sales) {

                stmt.setString(1, row.get(0)); // opportunity_id
                stmt.setString(2, row.get(1)); // sales_agent
                stmt.setString(3, row.get(2)); // product
                stmt.setString(4, row.get(3)); // account
                stmt.setString(5, row.get(4)); // deal_stage

                // engage_date
                if (row.get(5) != null && !row.get(5).isEmpty()) {
                    stmt.setDate(6, java.sql.Date.valueOf(row.get(5)));
                } else {
                    stmt.setNull(6, java.sql.Types.DATE);
                }

                // close_date
                if (row.get(6) != null && !row.get(6).isEmpty()) {
                    stmt.setDate(7, java.sql.Date.valueOf(row.get(6)));
                } else {
                    stmt.setNull(7, java.sql.Types.DATE);
                }

                // close_value
                if (row.get(7) != null && !row.get(7).isEmpty()) {
                    stmt.setDouble(8, Double.parseDouble(row.get(7)));
                } else {
                    stmt.setNull(8, java.sql.Types.DOUBLE);
                }

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
