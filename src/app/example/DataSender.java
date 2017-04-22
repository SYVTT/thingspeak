package app.example;

import com.angryelectron.thingspeak.Channel;
import com.angryelectron.thingspeak.Entry;
import com.angryelectron.thingspeak.ThingSpeakException;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class DataSender {

    public static void main(String[] args) {
        String apiWriteKey = "FLX4M627WED2TEAQ";
        int channelId = 262151;
        Channel channel = new Channel(channelId, apiWriteKey);

        Entry entry = new Entry();


        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        System.out.println(osBean.getSystemLoadAverage());


        entry.setField(1, String.valueOf(osBean.getSystemLoadAverage()));
        try {
            channel.update(entry);
        } catch (UnirestException | ThingSpeakException e) {
//            e.printStackTrace();
        }

    }

}
