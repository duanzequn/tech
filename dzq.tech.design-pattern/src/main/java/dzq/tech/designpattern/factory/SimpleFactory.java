package dzq.tech.designpattern.factory;

import java.util.Scanner;

abstract class BaseCoffee {
    public abstract void addTee();

    public abstract void addMilk();
}

class SpringCoffee extends BaseCoffee {
    @Override
    public void addTee() {
        System.out.println("加入初春茶叶！");
    }

    @Override
    public void addMilk() {
        System.out.println("加入特仑苏牛奶！");
    }
}

class LateeCoffee extends BaseCoffee {
    @Override
    public void addTee() {
        System.out.println("加入红茶！");
    }

    @Override
    public void addMilk() {
        System.out.println("加入纯牛奶！");
    }
}

class SimpleCoffeeFactory {
    private BaseCoffee coffee;

    private SimpleCoffeeFactory() {
    }

    public SimpleCoffeeFactory(String type) throws Exception {
        BaseCoffee coffee;
        if ("SpringCoffee".equals(type)) {
            this.coffee = new SpringCoffee();
        } else if ("LateeCoffee".equals(type)) {
            this.coffee = new LateeCoffee();
        } else {
            throw new Exception("不存在此咖啡！");
        }
    }

    public BaseCoffee createCoffee() {
        coffee.addMilk();
        coffee.addTee();
        return this.coffee;
    }
}

class CoffeeStore {
    SimpleCoffeeFactory fac;

    public void orderCoffee() throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入需要点的咖啡种类！");
            this.fac = new SimpleCoffeeFactory(sc.next());
            System.out.println(fac.createCoffee() + "已经准备好了！");
        }
    }
}

public class SimpleFactory {
    public static void main(String[] args) throws Exception {
        CoffeeStore store = new CoffeeStore();
        store.orderCoffee();
    }
}
/**
 * 简单工厂模式总结：把具体哪种实例的创建交给工厂类来判断创建，使用者与工厂交互，
 * 工厂根据使用者的要求创建并返回对应的实例，
 * 优点：传统创建实例时，客户与具体产品直接交互，导致添加新产品时，客户也需要改变，不符合开闭原则，
 * 由简单工厂负责实例的创建后，添加新产品，只需要更改工厂类，然后添加新产品即可，客户不需要改变
 * 缺点：这种方式虽然解除了客户（使用方）与产品（被使用对象）之间的耦合，但是又把简单工厂跟产品耦合在一起了，
 * 不符合开闭原则
 */