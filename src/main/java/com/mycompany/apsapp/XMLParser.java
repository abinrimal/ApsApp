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

            NodeList nodeList = doc.getDocumentElement().getElementsByTagName("account");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                // skip nested <account> nodes
                if (node.getParentNode() != doc.getDocumentElement()) {
                    continue;
                }

                Element accountElement = (Element) node;

                List<String> accountData = new ArrayList<>();

                accountData.add(safe(accountElement, "account"));
                accountData.add(safe(accountElement, "sector"));
                accountData.add(safe(accountElement, "year_established"));
                accountData.add(safe(accountElement, "revenue"));
                accountData.add(safe(accountElement, "employees"));
                accountData.add(safe(accountElement, "office_location"));
                accountData.add(safe(accountElement, "subsidiary_of"));

                accounts.add(accountData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return accounts;
    }
    
    //safely extract a text value from an XML tag inside an <account> element.
    private String safe(Element e, String tag) {
        NodeList nl = e.getElementsByTagName(tag); //if tag = "sector", it finds <sector>technolgy</sector>

        if (nl == null || nl.getLength() == 0 || nl.item(0) == null) {
            return null;
        }

        String value = nl.item(0).getTextContent();
        return (value != null && !value.trim().isEmpty()) ? value.trim() : null;
    }

    public List<List<String>> parseSalesTeams(String filePath) {
        List<List<String>> sales_teams = new ArrayList<>();
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
                sales_teams.add(saleTeamData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sales_teams;
    }
}
