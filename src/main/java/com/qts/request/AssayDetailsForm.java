package com.qts.request;



import com.qts.pojo.AssayDetails;

import java.util.List;

public class AssayDetailsForm extends AssayDetails {

    private List<String> itemValues;
    private List<String> itemNames;
    private List<String> itemStandards;

    public List<String> getItemValues() {
        return itemValues;
    }

    public void setItemValues(List<String> itemValues) {
        this.itemValues = itemValues;
    }

    public List<String> getItemNames() {
        return itemNames;
    }

    public void setItemNames(List<String> itemNames) {
        this.itemNames = itemNames;
    }

    public List<String> getItemStandards() {
        return itemStandards;
    }

    public void setItemStandards(List<String> itemStandards) {
        this.itemStandards = itemStandards;
    }
}
