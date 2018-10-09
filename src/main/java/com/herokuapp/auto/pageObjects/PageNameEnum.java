package com.herokuapp.auto.pageObjects;

public enum PageNameEnum {
    Login(LoginPage.class),
    Dashboard(DashboardPage.class),
    CreateEmployer(CreateEmployerPage.class),
    EditEmployer(EditEmployerPage.class);

    PageNameEnum(Class clazz) {
        this.clazz = clazz;
    }

    private Class clazz;

    public Class getClazz() {
        return clazz;
    }
}
