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
        
        CSVParser parse = new CSVParser();
        List<List<String>> products = parse.parseProducts("products.csv");
        List<List<String>> sales = parse.parseSalesPipeline("sales_pipeline.csv");
//           for (List<String> sale : sales) {
//            System.out.println(sale);
//        }

        // Transform data
        DataTransformer transformer = new DataTransformer();
        accounts = transformer.transformAccounts(accounts);
        products = transformer.transformProducts(products);
        sales = transformer.transformSalesPipeline(sales);
//        for (List<String> account : accounts) {
//            System.out.println(account);
//        }
        
        // Load data into MySQL
        MySQLLoader loader = new MySQLLoader();
        loader.loadAccounts(accounts);
        loader.loadSalesTeams(sales_teams);
        loader.loadProducts(products);
        loader.loadSalesPipeline(sales);

    }
}
