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
            account.set(0, account.get(0).toUpperCase()); //Company name uppercase

            //Fix sector spelling & standardize
            String sector = account.get(1).toLowerCase();
            if (sector.equals("technolgy")) sector = "technology";
            account.set(1, sector);

            //Fix country spelling
            String location = account.get(5);
            if (location.equalsIgnoreCase("Philipines")) {
                location = "Philippines";
            }
            account.set(5, location);

            //Handle missing subsidiary_of by adding null
            if (account.size() < 7) {
                account.add(null);
            }
        }
        return accounts;
    }

}
