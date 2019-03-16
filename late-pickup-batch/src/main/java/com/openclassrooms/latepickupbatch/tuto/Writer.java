package com.openclassrooms.latepickupbatch.tuto;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class Writer implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> msgArray) throws Exception {
        for(String msg : msgArray){
            System.out.println("In writer: " + msg);
        }
    }
}
