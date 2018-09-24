package com.herokuapp.auto.model;

public class Employee {
    String firstName;
    String lastName;
    String startDate;
    String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        Employee objEmployee;
        if (obj instanceof Employee)
            objEmployee = (Employee) obj;
        else
            return false;
        return objEmployee.firstName.equals(this.firstName)
                && objEmployee.lastName.equals(this.lastName)
                && objEmployee.startDate.equals(this.startDate)
                && objEmployee.email.equals(this.email);
    }
}
