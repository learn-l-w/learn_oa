package com.learn.service;

import com.learn.BaseTest;
import com.learn.model.Demo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/6
 * Time: 上午10:46
 */
public class DemoServiceTest extends BaseTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void testSelectById() {
        int id = 1;
        Demo demo = demoService.selectById(id);
        assert demo != null;
    }

    @Test
    public void testInsert() {
        Demo demo = new Demo();
        demo.setDesc("dssdf");
        demo.setTitle("xcvc");
        demoService.insert(demo);
    }

    @Test
    public void testUpdate() {
        Demo demo = new Demo();
        demo.setId(1);
        demo.setDesc("fhdfgj");
        demo.setTitle("gfbvn");
        demoService.update(demo);
    }

    @Test
    public void testDelete() {
        demoService.delete(1);
    }

}
