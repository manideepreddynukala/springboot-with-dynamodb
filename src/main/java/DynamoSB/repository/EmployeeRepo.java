package DynamoSB.repository;

import DynamoSB.entity.Employee;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.KeyStore;

@Repository
public class EmployeeRepo {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Employee save(Employee employee){
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String employeeId){
        return dynamoDBMapper.load(Employee.class, employeeId);
    }

    public String delete(String employeeId){
        Employee employee = dynamoDBMapper.load(Employee.class, employeeId);
        dynamoDBMapper.delete(employee);
        return "Deleted employee "+employeeId;
    }

    public String update(Employee employee, String employeeId){
        dynamoDBMapper.save(employee,  new DynamoDBSaveExpression()
                .withExpectedEntry("employeeId", new ExpectedAttributeValue(
                        new AttributeValue().withS(employeeId)
                )));
        return "Updated employee "+employeeId;
    }
}
