package dzq.tech.designpattern.singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 枚举单例（枚举特性：懒加载、线程安全、虚拟机保证内存只有一个（单实例））
 */
enum Singleton {
    /**
     * 单例用户
     */
    SingletonUser(new User());
    private final Object instance;

    Singleton(Object o) {
        instance = o;
    }

    public Object getInstance() {
        return instance;
    }
}

class User {

}

/**
 * 双重检查（懒加载、线程安全）
 */
class DoubleCheck {
    private static DoubleCheck instance;

    /**
     * 不允许外部创建对象
     */
    private DoubleCheck() {
    }

    public static DoubleCheck getInstance() {
        if (instance == null) {
            synchronized (DoubleCheck.class) {
                if (instance == null) {
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }
}

/**
 * 静态内部类（懒加载、线程安全）实现单例
 */
class StaticInnerClass {
    public static User getInstance() {
        return Single.INSTANCE;
    }

    private static final class Single {
        public static final User INSTANCE = new User();
    }
}

class MyThreadFactory implements ThreadFactory {
    static int index = 0;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("线程" + (++index));
        return thread;
    }
}

public class Main {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new MyThreadFactory();
        final int corePoolSize = 50;
        final int maximumPoolSize = 60;
        final int keepAliveTime = 5;
        ThreadPoolExecutor exec = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), threadFactory);
        for (int i = 0; i < corePoolSize; i++) {
            exec.execute(() ->
                    System.out.println(Singleton.SingletonUser.getInstance())
            );
        }
        for (int i = 0; i < corePoolSize; i++) {
            exec.execute(() ->
                    System.out.println(DoubleCheck.getInstance())
            );
        }
        for (int i = 0; i < corePoolSize; i++) {
            exec.execute(() ->
                    System.out.println(StaticInnerClass.getInstance())
            );
        }
        exec.shutdown();
    }
}
/**
 * 总结
 * 三种方法都能实现构造懒加载、线程安全的单实例，但是枚举跟静态内部类适合依赖反转时使用，双重检查适合作为对象内部函数返回
 */
