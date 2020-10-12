package service;

/**
 * @Description 表示员工的状态
 */
public class Status {
    private final String NAME;
    private String Name;

    private Status(String name){
        this.NAME = name;

    }
    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    public String getNAME(){
        return NAME;
    }

    public String getName() {
         return Name;
    }
    @Override
    public String toString() {
        return NAME;
    }

    
}
