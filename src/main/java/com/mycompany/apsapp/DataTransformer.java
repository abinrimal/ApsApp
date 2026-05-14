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

}
