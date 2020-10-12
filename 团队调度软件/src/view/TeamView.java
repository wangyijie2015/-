package view;

import domain.Programmer;
import domain.Employee;
import service.TeamException;
import service.NameListService;
import service.TeamService;

public class TeamView {

    NameListService listService = new NameListService(); //创建名单服务类对象
    TeamService teamService = new TeamService();   //创建团队功能类
    //进入菜单
    public void enterMainMenu(){

        boolean loopFlag = true; //循环结束标志
        char menu = 0;
        while (loopFlag) {
            if(menu != '1')
            listAllEmployees();
        }
        System.out.println("1-团队列表 2-添加团队列表 3-删除团队成员 4-退出 请选择（1-4）：");
        menu = TSUtility.readConfirmSelection();
        switch(menu){
            case '1':
                getTeam();
                break;
            case '2':
                addMember();
                break;
            case '3':
                deleteMember();
                break;
            case '4':
                System.out.print("确认是否退出(Y/N):");
                char isExit = TSUtility.readConfirmSelection();
                if(isExit == 'Y'){
                    loopFlag = false;
                }
                break;
        }
    }
    //显式所有的员工信息
    private void listAllEmployees(){
          System.out.println("--------------------------开发团队调度软件----------------------------");

          Employee[] employees = listService.getAllEmployees();
          if(employees == null || employees.length == 0){
              System.out.println("公司中没有任何员工信息！");
          }else{
              System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t股票\t领用设备");

              for(int i = 0;i < employees.length;i++){
                  System.out.println(employees[i]);
              }
          }
    }
    private void getTeam(){
//        System.out.println("查看开发团队情况");
        System.out.println("--------------------------团队成员列表----------------------------\n");

        Programmer[] team = teamService.getTeam();
        if(team == null || team.length == 0){
            System.out.println("开发团队目前没有成员！");
        }else{
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t状态\t股票\n");
            for(int i = 0; i < team.length; i++){
                System.out.println(team[i]);
            }
        }

        System.out.println("----------------------------------------------------------------\n");
    }
    private void addMember(){
//        System.out.println("添加团队成员");
        System.out.println("---------------------------添加成员-----------------------------------");
        System.out.println("请输入要添加的成员ID：");
        int id = TSUtility.readInt();

        try {
            Employee employee = listService.getEmployee(id);
            teamService.addMember(employee);
            System.out.println("添加成功");
            //按回车键继续....
            TSUtility.readConfirmSelection();
        } catch (TeamException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    private void deleteMember(){
//        System.out.println("删除团队成员");
    }

    public static void main(String[] args){
             TeamView view = new TeamView();
             view.enterMainMenu();
    }
}
