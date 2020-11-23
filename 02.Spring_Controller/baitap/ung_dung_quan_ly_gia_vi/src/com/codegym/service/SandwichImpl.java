package com.codegym.service;

import org.springframework.stereotype.Service;

@Service
public class SandwichImpl implements Sandwich {
    @Override
    public String save(String condiment) {
        if(condiment==null){
            return "Do not leave it blank";
        }
        return "Sandwich Condiments have " + condiment;
    }
}
