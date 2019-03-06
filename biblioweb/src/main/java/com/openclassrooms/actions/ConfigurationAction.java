package com.openclassrooms.actions;

import openclassrooms.config.PropSource;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Properties;

public class ConfigurationAction extends ActionSupport {

    String extensionDuration;
    String borrowingDuration;


    PropSource propSource = new PropSource();
    Properties props = propSource.getProps();

    public String execute() {
        setBorrowingDuration(props.getProperty("borrowing-duration"));
        setExtensionDuration(props.getProperty("extension-duration"));
        return SUCCESS;
    }

    public String changeConfig() {
        propSource.setProp(extensionDuration,borrowingDuration);
        return SUCCESS;
    }

    public String getExtensionDuration() {
        return extensionDuration;
    }

    public void setExtensionDuration(String extensionDuration) {
        this.extensionDuration = extensionDuration;
    }

    public String getBorrowingDuration() {
        return borrowingDuration;
    }

    public void setBorrowingDuration(String borrowingDuration) {
        this.borrowingDuration = borrowingDuration;
    }
}
