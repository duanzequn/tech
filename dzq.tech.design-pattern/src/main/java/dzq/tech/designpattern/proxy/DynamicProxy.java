package dzq.tech.designpattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理：1、jdk动态代理(代理的方法必须是接口方法) 2、cglib的动态代理（通过生成子类对象来实现代理，类似于继承实现静态代理
 * 也就意味着被代理类必须可继承，不能被final修饰）。
 * 动态代理模式一般与简单工厂模式结合使用
 */


/**
 * jdk动态代理：代理接口，通过Proxy.newProxyInstance构造，参数列表为（被代理对象类加载器，需要代理的接口类数组，
 * 代理逻辑实现），在代理逻辑实现里面传入被代理对象实例。返回的是被代理的接口对象。
 * 使用流程总结：传入（被代理实例和指定接口）实现对被代理对象方法执行的增强。
 */
interface say {
    String sayHello();

    String work();
}

class Cat implements say {
    @Override
    public String sayHello() {
        System.out.println("喵喵!");
        return "喵成功了！";
    }

    @Override
    public String work() {
        System.out.println("小喵正在捉老鼠！");
        return "捉成功了！";
    }
}

class JDKProxySimpleFactory {
    static Cat cat = new Cat();

    public static say getJDKProxyCat() {
        say proxyInterface = (say) Proxy.newProxyInstance(Cat.class.getClassLoader(), new Class[]{say.class}, (proxy, method, args) -> {
            System.out.println("要执行say接口里面的" + method.getName() + "方法了！");
            Object invoke = method.invoke(cat, args);
            System.out.println("我拦截到了[" + method.getName() + "]方法的返回值" + "[" + invoke.toString() + "]");
            return invoke;
        });
        return proxyInterface;
    }
}

/**
 * CGLIB动态代理：与简单工厂结合使用，简单工厂类实现MethodInterceptor接口中的intercept方法，
 * 在intercept方法中定义增强逻辑。并返回方法的执行结果。定义好代理逻辑后。创建enhancer实例
 * 设置父类（被代理对象类）.class即可，不需要传入父类（被代理对象类）实例，并把工厂类this传入enhancer的回调方法，
 * 最后调用enhancer.create返回代理类。
 * 使用总结：CGLIB其实就是通过子类继承实现代理增强，所以被代理类必须可继承
 * 创建代理对象无需手动新建被代理实例。
 */
class Dog {
    String sayHello() {
        return "汪汪汪！";
    }
}

class CGLIBProxyASimpleFactory implements MethodInterceptor {
    //static Dog dog = new Dog();

    @Override
    public String intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + "要执行了！");
        String ret = methodProxy.invokeSuper(o, objects).toString();
        System.out.println(method.getName() + "的返回值是" + ret);
        return ret;
    }

    public Dog getCGLIBProxyDog() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback(this);
        return (Dog) enhancer.create();
    }
}

public class DynamicProxy {
    /**
     * client
     *
     * @param args
     */
    public static void main(String[] args) {
        say jdkProxyCatSay = JDKProxySimpleFactory.getJDKProxyCat();
        jdkProxyCatSay.sayHello();
        jdkProxyCatSay.work();
        System.out.println("------------------------------------");
        Dog cglibProxyDog = new CGLIBProxyASimpleFactory().getCGLIBProxyDog();
        cglibProxyDog.sayHello();
    }
}
