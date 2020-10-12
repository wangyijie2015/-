package team.junit;

import domain.Employee;
import org.junit.Test;
import service.NameListService;
import service.TeamException;

public class NameListServiceTest {

    @Test
    public void testGetAllEmloyees(){
        NameListService service = new NameListService();
        Employee[] employees = service.getAllEmployees();
        for(int i = 0; i < employees.length;i++){
            System.out.println(employees[i]);
        }
    }

    @Test
    public void testGetEmployee(){
        NameListService service = new NameListService();
        int id = 1;
        id = 101;
        try {
            Employee employee = service.getEmployee(1);
            System.out.println(employee);
        } catch (TeamException e){
            System.out.println(e.getMessage());
        }
    }
}
