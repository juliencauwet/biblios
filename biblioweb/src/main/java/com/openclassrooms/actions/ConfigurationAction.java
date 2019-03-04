package com.openclassrooms.actions;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.config.PropSource;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Properties;

public class ConfigurationAction extends ActionSupport {

    String extensionDuration;
    String borrowingDuration;

    TestPortService service = new TestPortService();
    TestPort testPort = service.getTestPortSoap11();


    PropSource propSource = new PropSource();
    Properties props = propSource.getProps();

    public String execute() {
        GetBorrowingLengthRequest borrowingLengthRequest = new GetBorrowingLengthRequest();
        GetExtensionLengthRequest extensionLengthRequest = new GetExtensionLengthRequest();
        borrowingLengthRequest.setLibraryId(1);
        extensionLengthRequest.setLibraryId(1);

        setBorrowingDuration(Integer.toString(testPort.getBorrowingLength(borrowingLengthRequest).getBorrowingLength()));
        setExtensionDuration(Integer.toString(testPort.getExtensionLength(extensionLengthRequest).getExtensionLength()));

        return SUCCESS;
    }

    public String changeConfig() {
        //propSource.setProp(extensionDuration,borrowingDuration);
        UpdatePropertiesRequest request = new UpdatePropertiesRequest();
        request.setLibraryId(1);
        request.setBorrowingLength(Integer.parseInt(borrowingDuration));
        request.setExtensionLength(Integer.parseInt(extensionDuration));
        testPort.updateProperties(request);
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
