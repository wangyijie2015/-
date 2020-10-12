package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

public class TeamService {
    private static int counter = 1;//用于给memberId赋值
    private final int MAX_MEMBER = 5;//总人数
    Programmer[] team = new Programmer[MAX_MEMBER];//团队数组
    private int total = 0;//用于计算团队人数
    //获取团队所有成员
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];
        }
        return team;
    }
    //添加团队成员，各种添加不成功的情况会
    public void addMember(Employee e) throws TeamException {
        // 成员已满，无法添加
        if(total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
        // 该成员不是开发人员，无法添加
        if(!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        // 该员工已在本开发团队中
        for(int i = 0; i < total; i++) {
            if(e.getId() == team[i].getId()) {
                throw new TeamException("该员工已在本开发团队中");
            }
        }
        // 该员工已是某团队成员
        // 该员正在休假，无法添加
        Programmer p = (Programmer)e;
        if("BUSY".equals(p.getStatus().getName())){
            throw new TeamException("该员工已是某团队成员");
        }
        if("VOCATION".equals(p.getStatus().getName())){
            throw new TeamException("该员正在休假，无法添加");
        }
        // 团队中至多只能有一名架构师
        // 团队中至多只能有两名设计师
        // 团队中至多只能有三名程序员
        int numOfAri = 0, numOfDes = 0, numOfPro = 0;
        //遍历计算三种类型人数
        for(int i = 0; i < total; i++) {
            if(team[i] instanceof Architect) {
                numOfAri++;
            }else if (team[i] instanceof Designer) {
                numOfDes++;
            }else {
                numOfPro++;
            }
        }
        //判断情况并抛出异常
        if(p instanceof Architect) {
            if(numOfAri >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        }else if(p instanceof Designer) {
            if(numOfDes >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        }else if (p instanceof Programmer) {
            if(numOfPro >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }
        //若无异常，可以开始添加
        team[total++] = p;//将传入对象赋给数组，团队人数加一
        p.setStatus(Status.BUSY);//修改状态
        p.setNumberId(counter++);//设置新成员团队id，并将计数器加一
    }
    //删除成员，删除失败会抛出异常
    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        for(; i < total; i++) {
            if(memberId == team[i].getMemberId()) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        //如果遍历成功结束，说明没有找到
        if(i == total) {
            throw new TeamException("找不到指定memberId的员工，删除失败");
        }
        //开始删除，团队数组从找到的下标开始后一个对象进行覆盖
        for(int j = i; j < total - 1; j++) {
            team[j] = team[j+1];
        }
        //将数组最后一个对象指向空，总数减一
        team[--total] = null;
    }
}
