package org.playwright.utils;

import org.playwright.models.SandBoxFormDataType;

import java.util.HashMap;
import java.util.Map;

public class SandboxFormDataProvisionService {
    private static final Map<String, SandBoxFormDataType> formDataTypeMap;

    static {
        formDataTypeMap = new HashMap<>();
        SandBoxFormDataType validFormData = new SandBoxFormDataType("playwright", "Water", "Blue", Boolean.TRUE, "playwright@email.com");
        SandBoxFormDataType requiredValidFormData = new SandBoxFormDataType("playwright", null, null, null, null);
        SandBoxFormDataType invalidFormData = new SandBoxFormDataType("", null, null, null, null);
        formDataTypeMap.putIfAbsent("validFormData", validFormData);
        formDataTypeMap.putIfAbsent("requiredValidFormData", requiredValidFormData);
        formDataTypeMap.putIfAbsent("invalidFormData", invalidFormData);
    }

    public SandBoxFormDataType getValidFormData() {
        return formDataTypeMap.get("validFormData");
    }

    public SandBoxFormDataType getRequiredFormData() {
        return formDataTypeMap.get("requiredValidFormData");
    }

    public SandBoxFormDataType getInvalidFormData() {
        return formDataTypeMap.get("invalidFormData");
    }

}
