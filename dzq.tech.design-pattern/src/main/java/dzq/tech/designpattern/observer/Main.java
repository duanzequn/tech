package dzq.tech.designpattern.observer;

import java.util.Observable;
import java.util.Observer;


/**
 * 观察者模式：一个对象（被观察者）发生变化，其他对象（观察者）需要对这个变化作出反应
 * java.util实现了这种设计模式：1、被观察者继承Observable类，并且在发生变化时调用setChanged，notifObservers通知观察者作出
 * 响应  2、观察者对象们需要实现Observer接口，重写update方法，实现响应逻辑
 */
class Ring extends Observable {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.setChanged();
        //this.notifyObservers(参数值);可用此方法通知并传递参数给观察者
        this.notifyObservers();
    }
}

class Student implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Ring ring = (Ring) o;
        switch (ring.getType()) {
            case "1":
                System.out.println("同学：快去教师，要上课了！");
                break;
            case "0":
                System.out.println("同学：继续玩！放音乐而已！");
                break;
            case "-1":
                System.out.println("同学：下课了！");
            default:
                break;
        }

    }
}

class Teacher implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Ring ring = (Ring) o;
        switch (ring.getType()) {
            case "1":
                System.out.println("老师：同学们上课了！");
                break;
            case "0":
                System.out.println("老师：放音乐了！");
                break;
            case "-1":
                System.out.println("老师：下课了！");
            default:
                break;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Ring ring = new Ring();
        ring.addObserver(new Student());
        ring.addObserver(new Teacher());
        ring.setType("1");
        ring.setType("-1");
        ring.setType("0");
    }
}
