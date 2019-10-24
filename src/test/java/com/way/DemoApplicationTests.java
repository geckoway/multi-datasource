package com.way;

import com.way.dao.FirstDao;
import com.way.dao_second.SecondDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {
    @Autowired
    private FirstDao firstDao;
    @Autowired
    private SecondDao secondDao;

    @Test
    public void firstTest() {
        log.debug("Get first data:{}", firstDao.getData());
    }

    @Test
    public void secondTest() {
        log.debug("Get second data:{}", secondDao.getData());
    }

}
