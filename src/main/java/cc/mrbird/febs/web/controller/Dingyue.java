package cc.mrbird.febs.web.controller;

public class Dingyue implements Runnable {

    private Integer num = 4;

    @Override
    public void run() {
        synchronized (this){
            if (num > 0) {
                num--;
                System.out.println(Thread.currentThread().getName()+ " 抢到了");
                System.out.println(num);
            } else {
                System.out.println(Thread.currentThread().getName() + " 没票了");
            }
        }
    }
}
