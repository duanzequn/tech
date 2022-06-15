package dzq.tech.designpattern.factory;

interface BaseCakeFactory {
    BaseCake createCake();
}

abstract class BaseCake {
    String cream;
    String fruit;

    abstract void addFruit();

    abstract void addCream();

    @Override
    public String toString() {
        return "BaseCake{" +
                "cream='" + cream + '\'' +
                ", fruit='" + fruit + '\'' +
                '}';
    }
}

class OrangeCake extends BaseCake {
    @Override
    void addFruit() {
        this.fruit = "Orange";
    }

    @Override
    void addCream() {
        this.cream = "动物淡奶油";
    }
}

class AppleCake extends BaseCake {
    @Override
    void addFruit() {
        this.fruit = "Apple";
    }

    @Override
    void addCream() {
        this.cream = "植物淡奶油";
    }
}

class OrangeCakeFactory implements BaseCakeFactory {
    @Override
    public BaseCake createCake() {
        BaseCake cake = new OrangeCake();
        cake.addCream();
        cake.addFruit();
        return cake;
    }
}

class AppleCakeFactory implements BaseCakeFactory {
    @Override
    public BaseCake createCake() {
        BaseCake cake = new AppleCake();
        cake.addCream();
        cake.addFruit();
        return cake;
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        BaseCakeFactory fac = new OrangeCakeFactory();
        System.out.println(fac.createCake());
        fac = new AppleCakeFactory();
        System.out.println(fac.createCake());
    }
}
/**
 * 工厂方法总结：
 * 工厂方法解决了简单工厂模式中简单工厂与产品之间的耦合，新增产品时，只需要新增对应的产品实现类和工厂实现类。
 * 不涉及修改，符合开闭原则
 * 缺点：每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度。
 */