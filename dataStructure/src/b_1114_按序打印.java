import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

/**
 * @Data:2021/5/3
 */
public class b_1114_按序打印 {
    public static void main(String[] args) throws Exception {
        Foo1 foo = new Foo1();

        CompletableFuture.runAsync(() -> {
            try {
                foo.first(()->{
                    System.out.println(1);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture.runAsync(() -> {
            try {
                foo.third(()->{
                    System.out.println(3);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        CompletableFuture.runAsync(() -> {
            try {
                foo.second(()->{
                    System.out.println(2);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while (true) {

        }
    }

    static class Foo1 {
        Semaphore s1to2 = new Semaphore(0);
        Semaphore s2to3 = new Semaphore(0);
        public Foo1() {
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            s1to2.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            s1to2.acquire();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            s2to3.release();
        }
        public void third(Runnable printThird) throws InterruptedException {
            s2to3.acquire();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            s2to3.release();
        }
    }

    static class Foo2 {
        //控制变量
        private int flag = 0;
        //定义Object对象为锁
        private Object lock = new Object();
        public Foo2() {
        }
        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (lock){
                //如果flag不为0则让first线程等待，while循环控制first线程如果不满住条件就一直在while代码块中，
                //防止出现中途跳入，执行下面的代码，其余线程while循环同理
                while( flag != 0){
                    lock.wait();
                }
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                //定义成员变量为 1
                flag = 1;
                //唤醒其余所有的线程
                lock.notifyAll();
            }
        }
        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (lock){
                //如果成员变量不为1则让二号等待
                while (flag != 1){
                    lock.wait();
                }
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                //如果成员变量为 1 ，则代表first线程刚执行完，所以执行second，并且改变成员变量为 2
                flag = 2;
                //唤醒其余所有的线程
                lock.notifyAll();
            }
        }
        public void third(Runnable printThird) throws InterruptedException {
            synchronized (lock){
                //如果flag不等于2 则一直处于等待的状态
                while (flag != 2){
                    lock.wait();
                }
                // printThird.run() outputs "third". Do not change or remove this line.
                //如果成员变量为 2 ，则代表second线程刚执行完，所以执行third，并且改变成员变量为 0
                printThird.run();
                flag = 0;
                lock.notifyAll();
            }
        }
    }
}
