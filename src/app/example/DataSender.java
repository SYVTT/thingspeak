package app.example;

import com.angryelectron.thingspeak.Channel;
import com.angryelectron.thingspeak.Entry;
import com.angryelectron.thingspeak.ThingSpeakException;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class DataSender {

    public static void main(String[] args) {
        String apiWriteKey = "VG4X2XZ8AE47NU9S";
        int channelId = 262256;
        Channel channel = new Channel(channelId, apiWriteKey);

        Entry entry = new Entry();


        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//        System.out.println(osBean.getSystemCpuLoad());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(osBean.getSystemCpuLoad());
        while(true) {
            Double cpuLoad = osBean.getSystemCpuLoad();
            Double freeMemory = (double)osBean.getFreePhysicalMemorySize() / osBean.getTotalPhysicalMemorySize();
            entry.setField(1, String.valueOf(cpuLoad));
            entry.setField(2, String.valueOf(freeMemory));
            try {
                channel.update(entry);
            } catch (UnirestException | ThingSpeakException e) {
                System.out.println("Channel update exception");
            }

            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                System.out.println("Thread exception");
            }
        }

    }

}
