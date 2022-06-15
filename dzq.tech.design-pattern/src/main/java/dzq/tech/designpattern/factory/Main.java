package dzq.tech.designpattern.factory;

class Coffee {
  private String name;

  public void addSugar() {
    System.out.println("加糖");
  }

  public void addTee() {
    System.out.println("加茶水");
  }

  public String getName() {
    return this.name;
  }
}

class AmericanCoffee extends Coffee {

}

class LatteCoffee extends Coffee {
  @Override
  public String getName() {
    return "";
  }
}

class CoffeeStore {
  public void orderCoffee() {

  }
}
/**
 * 简单工厂
 * <p>
 * 工厂方法
 * <p>
 * 工厂方法
 * <p>
 * 工厂方法
 * <p>
 * 工厂方法
 * <p>
 * 工厂方法
 * <p>
 * 工厂方法
 * <p>
 * 工厂方法
 */

/**
 * 工厂方法
 */

/**
 * 抽象工厂
 */
public class Main {
}
