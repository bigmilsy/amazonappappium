/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amazonpurchase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author dmiller
 */
public class TestData {
    public HashMap<String, String> data;
    public TestData() {
        this.data = new HashMap<>();
        readTestData();
    }
    public void readTestData() {
        String csvFile = "D:\\testing\\2020_Telstra\\project\\AmazonPurchase\\testData.csv";
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] strData = line.split(cvsSplitBy);
                data.put(strData[0], strData[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}
