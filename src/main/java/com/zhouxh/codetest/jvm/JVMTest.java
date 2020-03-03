package com.zhouxh.codetest.jvm;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.management.*;
import java.lang.management.*;
import java.util.List;
import java.util.Map;

public class JVMTest {
    public static void main(String[] args) throws MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        System.out.println(heapMemoryUsage);
        System.out.println(nonHeapMemoryUsage);

        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean memoryPoolMXBean : memoryPoolMXBeans) {
            System.out.println("############");
            System.out.println(memoryPoolMXBean.getName());
            System.out.println(memoryPoolMXBean.getType());

            System.out.println(memoryPoolMXBean.getCollectionUsage());
//            System.out.println(memoryPoolMXBean.getCollectionUsageThreshold());
//            System.out.println(memoryPoolMXBean.getCollectionUsageThresholdCount());

            System.out.println(memoryPoolMXBean.getUsage());
            System.out.println(memoryPoolMXBean.getPeakUsage());
//            System.out.println(memoryPoolMXBean.getUsageThreshold());
//            System.out.println(memoryPoolMXBean.getUsageThresholdCount());
        }

        ManagementFactory.getMemoryManagerMXBeans();

        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
            System.out.println("===========");
            System.out.println(garbageCollectorMXBean.getName());
            System.out.println(garbageCollectorMXBean.getCollectionCount());
            System.out.println(garbageCollectorMXBean.getCollectionTime());
        }


        List<MemoryManagerMXBean> memoryManagerMXBeans = ManagementFactory.getMemoryManagerMXBeans();
        for (MemoryManagerMXBean memoryManagerMXBean : memoryManagerMXBeans) {

            System.out.println("################");
            System.out.println(memoryManagerMXBean.getName());
            for (String memoryPoolName : memoryManagerMXBean.getMemoryPoolNames()) {
                System.out.println(memoryPoolName);
                System.out.println("-------");
            }
        }

        System.out.println("===========");
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();


        System.out.println(operatingSystemMXBean.getSystemLoadAverage());
        System.out.println(((com.sun.management.OperatingSystemMXBean)operatingSystemMXBean).getSystemCpuLoad());
        System.out.println(((com.sun.management.OperatingSystemMXBean)operatingSystemMXBean).getProcessCpuLoad());
        System.out.println(((com.sun.management.OperatingSystemMXBean)operatingSystemMXBean).getProcessCpuTime());


        System.out.println("===========");

        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName objectName = ObjectName.getInstance("java.lang:type=OperatingSystem");

        System.out.println(platformMBeanServer.getAttribute(objectName, "ProcessCpuLoad"));
        System.out.println(platformMBeanServer.getAttribute(objectName, "SystemCpuLoad"));
        System.out.println(platformMBeanServer.getAttribute(objectName, "ProcessCpuTime"));

        System.out.println("################");

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(ToStringBuilder.reflectionToString(threadInfo, ToStringStyle.MULTI_LINE_STYLE));
//            StackTraceElement[] stackTrace = threadInfo.getStackTrace();

//            for (StackTraceElement traceElement : stackTrace) {
//                System.out.println(ToStringBuilder.reflectionToString(traceElement, ToStringStyle.MULTI_LINE_STYLE));
//            }
            LockInfo lockInfo = threadInfo.getLockInfo();
            if(lockInfo != null)
                System.out.println(ToStringBuilder.reflectionToString(lockInfo));
            System.out.println("################");
        }


        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        System.out.println(runtimeMXBean.getBootClassPath());
        System.out.println(runtimeMXBean.getClassPath());
        System.out.println(runtimeMXBean.getLibraryPath());
        System.out.println(runtimeMXBean.getManagementSpecVersion());
        System.out.println(runtimeMXBean.getSpecName());
        System.out.println(runtimeMXBean.getSpecVendor());
        System.out.println(runtimeMXBean.getStartTime());
        System.out.println(runtimeMXBean.getSpecVersion());
        System.out.println(runtimeMXBean.getVmName());
        System.out.println(runtimeMXBean.getVmVendor());

        List<String> inputArguments = runtimeMXBean.getInputArguments();
        for (String inputArgument : inputArguments) {
            System.out.println(inputArgument);
        }


        Map<String, String> systemProperties = runtimeMXBean.getSystemProperties();
        systemProperties.forEach((k,v) -> {
            System.out.println(k + "=" + v);
        });


        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
    }
}
