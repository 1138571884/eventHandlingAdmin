package cc.mrbird.febs.web.controller;


import cc.mrbird.febs.common.domain.FebsConstant;
import cc.mrbird.febs.common.domain.FebsResponse;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
@RequestMapping("test")
public class Test {

    @GetMapping("dingyue")
    public void dingyue(String name, String topic, String clintid) {
//        Dingyue dingyue = new Dingyue(topic, clintid);
//        dingyue.setName(name);
//        dingyue.run();
    }

    @GetMapping("exit")
    public void exit(String name) {
        ThreadGroup currentGroup =
                Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        for (int i = 0; i < noThreads; i++) {
            System.out.println("线程号：" + i + " = " + lstThreads[i].getId());
            System.out.println("线程号：" + i + " = " + lstThreads[i].getName());
            if (lstThreads[i].getName().equals(name)) {
                lstThreads[i].interrupt();
            }
        }
    }
}
