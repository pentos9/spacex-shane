package com.buzz.common.id;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

public class UniqueIdGenerator {
    private static Logger log = LoggerFactory.getLogger(UniqueIdGenerator.class);
    public static final long EPOCH;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_BITS = 10L;
    private static final long SEQUENCE_MASK = 4095L;
    private static final long WORKER_ID_LEFT_SHIFT_BITS = 12L;
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = 22L;
    private static final long WORKER_ID_MAX_VALUE = 1024L;
    private static AbstractClock clock = AbstractClock.systemClock();
    private static long workerId;
    private static long sequence;
    private static long lastTime;

    public UniqueIdGenerator() {
    }

    static void initWorkerId() {
        InetAddress address = getLocalAddress();
        byte[] ipAddressByteArray = address.getAddress();
        setWorkerId(Long.valueOf((long)(((ipAddressByteArray[ipAddressByteArray.length - 2] & 3) << 8) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 255))));
    }

    private static InetAddress getLocalAddress() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();

            while(e.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface)e.nextElement();
                if(!networkInterface.isLoopback() && !networkInterface.isVirtual() && networkInterface.isUp()) {
                    Enumeration addresses = networkInterface.getInetAddresses();
                    if(addresses.hasMoreElements()) {
                        return (InetAddress)addresses.nextElement();
                    }
                }
            }

            return null;
        } catch (Exception var3) {
            log.debug("Error when getting host ip address: <{}>.", var3.getMessage());
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
    }

    public static void setWorkerId(Long workerId) {
        Preconditions.checkArgument(workerId.longValue() >= 0L && workerId.longValue() < 1024L);
        workerId = workerId.longValue();
    }

    public static synchronized Long generateId() {
        long time = clock.millis();
        Preconditions.checkState(lastTime <= time, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", new Object[]{Long.valueOf(lastTime), Long.valueOf(time)});
        if(lastTime == time) {
            if(0L == (sequence = ++sequence & 4095L)) {
                time = waitUntilNextTime(time);
            }
        } else {
            sequence = 0L;
        }

        lastTime = time;
        if(log.isDebugEnabled()) {
            log.debug("{}-{}-{}", new Object[]{(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(lastTime)), Long.valueOf(workerId), Long.valueOf(sequence)});
        }

        return Long.valueOf(time - EPOCH << 22 | workerId << 12 | sequence);
    }

    private static long waitUntilNextTime(long lastTime) {
        long time;
        for(time = clock.millis(); time <= lastTime; time = clock.millis()) {
            ;
        }

        return time;
    }

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 3, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        EPOCH = calendar.getTimeInMillis();
        initWorkerId();
    }
}
