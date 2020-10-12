package service;

import domain.*;

import java.util.Date;

import static java.util.Date.*;
import static service.Data.*;

/**
 *
 * @Description 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法
 * @author wangyijie
 * @version v1.0
 * @data
 *
 */
public class NameListService {

    private Employee[] employees;

    /**
     * 给employee及数组元素进行初始化
     */
    public NameListService(){
       employees = new Employee[EMPLOYEES.length];

       for(int i = 0; i < employees.length;i++){
           int type = Integer.parseInt(EMPLOYEES[i][0]); //获取Employee的4个基本信息

           int id = Integer.parseInt(EMPLOYEES[i][1]);
           String name = EMPLOYEES[i][2];
           int age = Integer.parseInt(EMPLOYEES[i][3]);
           double salary = Double.parseDouble(EMPLOYEES[i][4]);

           Equipment equipment;
           double bonus;
           int stock;

           switch(type){
               case EMPLOVEE:
                   employees[i] = new Employee(id,name,age,salary);
                   break;
               case PROGRAMMER:
                   equipment = createEquipment(i);
                   employees[i] = new Programmer(id,name,age,salary,equipment);
                   break;
               case DESIGNER:
                   equipment = createEquipment(i);
                   bonus = Double.parseDouble(EMPLOYEES[i][5]);
                   employees[i] = new Designer(id,name,age,salary,equipment,bonus);
                   break;
               case ARCHITECT:
                   equipment = createEquipment(i);
                   bonus = Double.parseDouble(EMPLOYEES[i][5]);
                   stock = Integer.parseInt(EMPLOYEES[i][6]);
                   employees[i] = new Employee(id,name,age,salary,equipment,bonus,stock);
                   break;
           }
       }
    }

    private Equipment createEquipment(int index) {

        int key = Integer.parseInt(EQUIPMENTS[index][0]);

        String modelOrName = EQUIPMENTS[index][1];

        switch(key){
            case PC:
                String display = EQUIPMENTS[index][2];
                return new PC(modelOrName,display);
            case NOTEBOOK:
                double price = Double.parseDouble(EQUIPMENTS[index][2]);
                return new NoteBook(modelOrName,price);
            case PRINTER:
                String type = EQUIPMENTS[index][2];
                return new Printer(modelOrName,type);
        }
        //TODO Auto-generated method stub
        return null; //获取指定index上的员工设备
    }

    /**
     * 获取当前所有员工
     * @return
     */

    public Employee[] getAllEmployees(){
        return employees;
    }

    public Employee getEmployee(int id) throws TeamException{
        for(int i = 0; i < employees.length;i++){
            if(employees[i].getId() == id){
                return employees[i];
            }
        }

        throw new TeamException("找不到指定的员工");
    }
}
