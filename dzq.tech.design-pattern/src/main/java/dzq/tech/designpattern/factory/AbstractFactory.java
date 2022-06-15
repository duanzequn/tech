package dzq.tech.designpattern.factory;

interface CreatureFactory {
    BaseAnimal createAnimal();

    BasePlant createPlant();
}

abstract class BaseAnimal {
    abstract void shout();
}

class ChineseCat extends BaseAnimal {
    @Override
    void shout() {
        System.out.println("喵喵喵！");
    }
}

class ChineseDog extends BaseAnimal {
    @Override
    void shout() {
        System.out.println("汪汪汪！");
    }
}

class AmericanCat extends BaseAnimal {
    @Override
    void shout() {
        System.out.println("喵喵喵喵！");
    }
}

class AmericanDog extends BaseAnimal {
    @Override
    void shout() {
        System.out.println("汪汪汪旺！");
    }
}

abstract class BasePlant {
    abstract void bear();
}

class AmericanOrange extends BasePlant {
    @Override
    void bear() {
        System.out.println("结出了一个橙子！");
    }
}

class AmericanApple extends BasePlant {
    @Override
    void bear() {
        System.out.println("结出了一个苹果！");
    }
}

class ChineseOrange extends BasePlant {
    @Override
    void bear() {
        System.out.println("结出了三个橙子！");
    }
}

class ChineseApple extends BasePlant {
    @Override
    void bear() {
        System.out.println("结出了三个苹果！");
    }
}

class ChineseCreatureFactory implements CreatureFactory {
    @Override
    public BaseAnimal createAnimal() {
        return new ChineseCat();
    }

    @Override
    public BasePlant createPlant() {
        return new ChineseApple();
    }
}

class AmericanCreatureFactory implements CreatureFactory {
    @Override
    public BaseAnimal createAnimal() {
        return new AmericanDog();
    }

    @Override
    public BasePlant createPlant() {
        return new AmericanOrange();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        CreatureFactory fac = new ChineseCreatureFactory();
        BaseAnimal animal = fac.createAnimal();
        animal.shout();
        fac = new AmericanCreatureFactory();
        BasePlant plant = fac.createPlant();
        plant.bear();
    }
}
/**
 * 抽象工厂模式总结：
 * 优点：新增一个产品族时，只需添加一个新工厂即可，如新增一个日本生物工厂即可获取日本生物工厂，符合开闭原则
 * 缺点：新增一个产品时，如新增一种中国猴子的产品时，涉及到的中国生物工厂需要修改，不符个开闭原则
 */