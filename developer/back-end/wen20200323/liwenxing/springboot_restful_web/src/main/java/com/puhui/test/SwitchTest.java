package com.puhui.test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @ClassName SwitchTest
 * @Description 使用Java提供的MXBean来监控jvm创建了这些线程
 */

public class SwitchTest {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] allThreadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(allThreadIds);

        for (ThreadInfo info : threadInfo) {
            System.out.println(info.getThreadId() + " : " + info.getThreadName());
        }

    }

}
