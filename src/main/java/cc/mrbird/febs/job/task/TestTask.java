package cc.mrbird.febs.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestTask {

    public void test(String params) {
        System.out.println("test");
        log.info("我是带参数的test方法，正在被执行，参数为：{}" , params);
    }

    public void test1() {
        System.out.println("test1");
        log.info("我是不带参数的test1方法，正在被执行");
    }

    public void test2() {
        System.err.println("test2");
    }

}
