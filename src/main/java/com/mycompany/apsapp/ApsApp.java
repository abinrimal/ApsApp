/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.apsapp;

import java.util.List;
/**
 *
 * @author abin
 */
public class ApsApp {

    public static void main(String[] args) {
        
        // Parse XML data
        XMLParser parser = new XMLParser();
        List<List<String>> accounts = parser.parseAccounts("accounts.xml");
        List<List<String>> sales_teams = parser.parseSalesTeams("sales_teams.xml");

        // Transform data
        DataTransformer transformer = new DataTransformer();
        accounts = transformer.transformAccounts(accounts);
//        for (List<String> account : accounts) {
//            System.out.println(account);
//        }
        
        // Load data into MySQL
        MySQLLoader loader = new MySQLLoader();
        loader.loadAccounts(accounts);
        loader.loadSalesTeams(sales_teams);

    }
}
