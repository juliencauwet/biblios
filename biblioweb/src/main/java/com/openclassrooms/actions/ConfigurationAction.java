package com.openclassrooms.actions;

import com.openclassrooms.biblioback.ws.test.*;
import com.openclassrooms.config.PropSource;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Properties;

public class ConfigurationAction extends ActionSupport {

    private String extensionDuration;
    private String borrowingDuration;

    private TestPortService service = new TestPortService();
    private TestPort port = service.getTestPortSoap11();

    public String execute() {
        GetBorrowingLengthRequest borrowingLengthRequest = new GetBorrowingLengthRequest();
        GetExtensionLengthRequest extensionLengthRequest = new GetExtensionLengthRequest();
        borrowingLengthRequest.setLibraryId(1);
        extensionLengthRequest.setLibraryId(1);

        extensionDuration = Integer.toString(port.getExtensionLength(extensionLengthRequest).getExtensionLength());
        borrowingDuration = Integer.toString(port.getBorrowingLength(borrowingLengthRequest).getBorrowingLength());

        return SUCCESS;
    }

    public String changeConfig() {

        UpdatePropertiesRequest request = new UpdatePropertiesRequest();
        request.setLibraryId(1);
        request.setBorrowingLength(Integer.parseInt(borrowingDuration));
        request.setExtensionLength(Integer.parseInt(extensionDuration));

        port.updateProperties(request);

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
