package com.openclassrooms.latepickupbatch.tuto;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ReaderImpl implements ItemReader {

    private String[] msgArray= {"Hi","Welcome","To","Spring","batch","!!"};
    private int count=0;

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(count < msgArray.length){
            return msgArray[count++];
        }else{
            count=0;
        }
        return null;
    }
}
