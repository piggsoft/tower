package com.piggsoft.tower.core;

import com.piggsoft.tower.core.config.TowProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private TowProperties tp;

    @Test
    public void contextLoads() {
        System.out.println(tp.isUseEpoll());
    }

}
