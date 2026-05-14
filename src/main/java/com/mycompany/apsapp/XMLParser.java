/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apsapp;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abin
 */
public class XMLParser {
    public List<List<String>> parseAccounts(String filePath) {
        List<List<String>> accounts = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));

            NodeList accountList = doc.getElementsByTagName("account");
            for (int i = 0; i < accountList.getLength(); i++) {
                Element accountElement = (Element) accountList.item(i);
                List<String> accountData = new ArrayList<>();
                accountData.add(accountElement.getElementsByTagName("account").item(0).getTextContent());
                accountData.add(accountElement.getElementsByTagName("sector").item(0).getTextContent());
                accountData.add(accountElement.getElementsByTagName("year_established").item(0).getTextContent());                accountData.add(accountElement.getElementsByTagName("year_established").item(0).getTextContent());
                accountData.add(accountElement.getElementsByTagName("revenue").item(0).getTextContent());                accountData.add(accountElement.getElementsByTagName("revenue").item(0).getTextContent());
                accountData.add(accountElement.getElementsByTagName("employees").item(0).getTextContent());
                accountData.add(accountElement.getElementsByTagName("office_location").item(0).getTextContent());                accountData.add(accountElement.getElementsByTagName("office_location").item(0).getTextContent());
                accountData.add(accountElement.getElementsByTagName("subsidiary_of").item(0).getTextContent());
                accounts.add(accountData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public List<List<String>> parseSalesTeams(String filePath) {
        List<List<String>> sales = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));

            NodeList saleTeamsList = doc.getElementsByTagName("row");
            for (int i = 0; i < saleTeamsList.getLength(); i++) {
                Element saleTeamElement = (Element) saleTeamsList.item(i);
                List<String> saleTeamData = new ArrayList<>();
                saleTeamData.add(saleTeamElement.getElementsByTagName("sales_agent").item(0).getTextContent());
                saleTeamData.add(saleTeamElement.getElementsByTagName("manager").item(0).getTextContent());
                saleTeamData.add(saleTeamElement.getElementsByTagName("regional_office").item(0).getTextContent());
                sales.add(saleTeamData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sales;
    }
}
