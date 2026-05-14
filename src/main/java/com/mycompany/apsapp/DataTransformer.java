/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apsapp;

import java.util.List;

/**
 *
 * @author abin
 */
public class DataTransformer {
    
    public List<List<String>> transformAccounts(List<List<String>> accounts) {
        for (List<String> account : accounts) {
            
            if (account.get(0) != null) {
                account.set(0, account.get(0).toUpperCase()); //Uppercase Company Name
            }
            
            //Fix sector spelling & standardize
            String sector = account.get(1);
            if (sector != null) {
                sector = sector.toLowerCase();
                if (sector.equals("technolgy")) sector = "technology";
                account.set(1, sector);
            }

            //Fix country spelling
            String location = account.get(5);
            if (location != null) {
                if (location.equalsIgnoreCase("Philipines")) {
                    location = "Philippines";
                }
                account.set(5, location);
            }

            //Handle missing subsidiary_of by adding null
            if (account.size() < 7) {
                account.add(null);
            }
        }
        return accounts;
    }
    
    public List<List<String>> transformProducts(List<List<String>> products) {

        for (List<String> row : products) {

            // Series cleanup
            if (row.get(1) != null) {
                String series = row.get(1).trim().toUpperCase();
                row.set(1, series);
            }

            // sales_price cleanup
            if (row.get(2) != null) {
                String price = row.get(2).trim();

                if (price.isEmpty()) {
                    row.set(2, null);
                } else {
                    row.set(2, price);
                }
            }
        }

        return products;
    }
    
    public List<List<String>> transformSalesPipeline(List<List<String>> sales) {

        for (List<String> row : sales) {

            if (row.get(0) != null) {
                row.set(0, row.get(0).trim());
            }
            
            if (row.get(1) != null) {
                row.set(1, row.get(1).trim());
            }

            // PRODUCT FIX 
            if (row.get(2) != null) {
                String product = row.get(2).trim();

                // normalizing  issues
                product = product
                        .replace("GTXPro", "GTX Pro")
                        .replace("GTXPlus", "GTX Plus")
                        .replace("MGAdvanced", "MG Advanced");

                row.set(2, product);
            }

            // Account cleanup
            if (row.get(3) != null) {
                row.set(3, row.get(3).trim());
            }

            // Deal stage normalization
            if (row.get(4) != null) {
                row.set(4, row.get(4).trim());
            }

            // Dates cleanup
            if (row.get(5) != null && row.get(5).trim().isEmpty()) {
                row.set(5, null);
            }

            if (row.get(6) != null && row.get(6).trim().isEmpty()) {
                row.set(6, null);
            }

            // close_value cleanup
            if (row.get(7) != null && row.get(7).trim().isEmpty()) {
                row.set(7, null);
            }
        }

        return sales;
    }

}
