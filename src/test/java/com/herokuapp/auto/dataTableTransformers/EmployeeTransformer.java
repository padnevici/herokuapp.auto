package com.herokuapp.auto.dataTableTransformers;

import com.herokuapp.auto.model.Employee;
import com.herokuapp.auto.testDataUtils.RandomDataHelper;
import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.Map;

public class EmployeeTransformer implements TypeRegistryConfigurer {

    public Locale locale() {
        return Locale.ENGLISH;
    }

    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(Employee.class,
                        (Map<String, String> row) -> {
                            Employee employee = new Employee();
                            employee.setFirstName(RandomDataHelper.getRandomDataFor(row.get("firstName")));
                            employee.setLastName(RandomDataHelper.getRandomDataFor(row.get("lastName")));
                            employee.setStartDate(RandomDataHelper.getRandomDataFor(row.get("startDate")));
                            employee.setEmail(RandomDataHelper.getRandomDataFor(row.get("email")));
                            return employee;
                        }
                )
        );
    }
}
