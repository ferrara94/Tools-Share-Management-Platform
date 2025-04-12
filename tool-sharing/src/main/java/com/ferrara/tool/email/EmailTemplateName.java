package com.ferrara.tool.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    //LIST OF TEMPLATES
    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}
