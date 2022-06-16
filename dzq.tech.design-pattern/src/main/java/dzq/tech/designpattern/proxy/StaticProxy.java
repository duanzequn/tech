package dzq.tech.designpattern.proxy;

/**
 * 静态代理，代理对象通过聚合或者继承被代理对象实现对被代理对象的功能增强，客户端通过代理对象来使用被代理对象
 */
class A {
    public String say() {
        return "我很帅";
    }
}

/**
 * 继承实现静态代理
 */
class AProxyExtends extends A {
    @Override
    public String say() {
        System.out.println("我是继承代理对象，A想说话了");
        String say = super.say();
        System.out.println("A想跟客户说“" + say + "”,但是我不同意了");
        say = "我很丑";
        return say;
    }
}

/**
 * 聚合实现动态代理
 */
class AProxyEmbed {
    private final A a = new A();

    public String say() {
        System.out.println("我是聚合代理对象，A想说话了");
        String say = a.say();
        System.out.println("A想跟客户说“" + say + "”,但是我不同意了");
        say = "我十分丑";
        return say;
    }
}

public class StaticProxy {
    public static void main(String[] args) {
        A a = new AProxyExtends();
        System.out.println("客户收到A对他说：“" + a.say() + "”");
        AProxyEmbed e = new AProxyEmbed();
        System.out.println("客户收到A对他说：“" + e.say() + "”");
    }
}
