package io.github.xxyopen.util;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * IdWorker单元测试
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/9
 */
public class IdWorkerTest {

    @Test
    @SneakyThrows
    public void idWorkerTest() {
        List<Long> idList = new ArrayList<>(10);
        IdWorker idworker = IdWorker.INSTANCE;
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                long start = System.currentTimeMillis();
                idList.add(idworker.nextId());
                System.out.println("耗时：" + (System.currentTimeMillis() - start)  + "毫秒");
            }).start();
        }
        TimeUnit.SECONDS.sleep(1);
        for (int i = 1; i < idList.size(); i++) {
            System.out.println(idList.get(i));
            Assert.assertTrue(idList.get(i) > idList.get(i - 1));
        }
    }
}
