package app.example;

import com.angryelectron.thingspeak.Channel;
import com.angryelectron.thingspeak.Entry;
import com.angryelectron.thingspeak.ThingSpeakException;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class DataSender {

    public static void main(String[] args) {
        String apiWriteKey = "VZL27Q051LEG8TNM";
        int channelId = 262248;
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
            System.out.println(osBean.getSystemCpuLoad());
            entry.setField(1, String.valueOf(osBean.getSystemCpuLoad()));
            try {
                channel.update(entry);
            } catch (UnirestException | ThingSpeakException e) {
//            e.printStackTrace();
            }

            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }

    }

}
