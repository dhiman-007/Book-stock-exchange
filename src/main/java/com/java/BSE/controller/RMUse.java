package com.java.BSE.controller;

import com.java.BSE.model.RMData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class RMUse {

    @GetMapping("/getData")
    public List<RMData> getCSVData() throws FileNotFoundException {
        String filePath = "C://Users//HP//OneDrive//Desktop//Java_flavours.csv";
        List<RMData> rmDataList = new ArrayList<>();

        try {
            FileReader filereader = new FileReader(filePath);
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

            List<String[]> allData = csvReader.readAll();

            RMData rmData;
            List<String> skills;

            for (int i = 1; i < allData.size(); i++) {
                rmData = new RMData();
                skills = new ArrayList<>();
                for (String str : allData.get(i)) {
                    if (str.startsWith("ROLE")) rmData.setRoleId(str);
                    else {
                        if(!str.isEmpty())
                            skills.add(str.trim());
                    }
                }
                rmData.setSkills(skills);
                rmDataList.add(rmData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rmDataList;
    }

    @GetMapping("/{skill}")
    public List<RMData> listJobOnSkill(@PathVariable String skill) throws FileNotFoundException {
        List<RMData> jobsData = getCSVData();
        return jobsData.stream()
                .filter(f -> f.getSkills().contains(skill))
                .collect(Collectors.toList());
    }   
}

