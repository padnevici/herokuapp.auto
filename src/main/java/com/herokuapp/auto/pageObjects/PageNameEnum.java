package com.herokuapp.auto.pageObjects;

public enum PageNameEnum {
    LoginPage(com.herokuapp.auto.pageObjects.LoginPage.class),
    DashboardPage(DashboardPage.class);

    PageNameEnum(Class clazz) {
        this.clazz = clazz;
    }

    private Class clazz;

    public Class getClazz() {
        return clazz;
    }
}
