/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apsapp;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abin
 */
public class CSVParser {
    public List<List<String>> parseProducts(String filePath) {
        List<List<String>> products = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            boolean isHeader = true;
            while ((line = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                List<String> product = new ArrayList<>();

                product.add(safe(line, 0)); // product
                product.add(safe(line, 1)); // series
                product.add(safe(line, 2)); // sales_price
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    
    public List<List<String>> parseSalesPipeline(String filePath) {
        List<List<String>> sales = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            boolean isHeader = true;
            while ((line = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                List<String> sale = new ArrayList<>();

                sale.add(safe(line, 0)); // opportunity_id
                sale.add(safe(line, 1)); // sales_agent
                sale.add(safe(line, 2)); // product
                sale.add(safe(line, 3)); // account
                sale.add(safe(line, 4)); // deal_stage
                sale.add(safe(line, 5)); // engage_date
                sale.add(safe(line, 6)); // close_date
                sale.add(safe(line, 7)); // close_value
                sales.add(sale);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sales;
    }

    
    private String safe(String[] arr, int index) {
        if (arr == null || index >= arr.length) return null;
        String value = arr[index];
        if (value == null) return null;
        value = value.trim();
        return value.isEmpty() ? null : value;
    }
    
}
