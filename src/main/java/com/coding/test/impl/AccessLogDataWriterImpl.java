package com.coding.test.impl;

import com.coding.test.DataWriter;
import com.coding.test.model.AccessLogData;
import com.coding.test.model.Result;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AccessLogDataWriterImpl implements DataWriter {

    @Override
    public Result writeData(List<AccessLogData> accessLogData) {
        Result result = new Result();
        List<String> ipAddressEntries = getSortedIpAddress(accessLogData);
        List<String> urlEntries = getSortedUrl(accessLogData);
        result.setUniqueIpAddress(ipAddressEntries.size());
        result.setMostActiveIpAddress(ipAddressEntries);
        result.setMostActiveUrls(urlEntries);
        return result;
    }

    private  List<String> getSortedIpAddress(List<AccessLogData> accessLogData){
        List<String> result = new ArrayList<>();
        Map<String, Integer> ipCountMap = new HashMap<>();
        accessLogData.stream().forEach(data -> ipCountMap.put(data.getIpAddress(), ipCountMap.getOrDefault(data.getIpAddress(), 0) + 1));
        Set<Map.Entry<String, Integer>> entry = ipCountMap.entrySet();
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(entry);
        Collections.sort(entries, (o1, o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        });
        entries.stream().forEach(stringIntegerEntry -> result.add(stringIntegerEntry.getKey()));

        return  result;
    }

    private   List<String> getSortedUrl(List<AccessLogData> accessLogData){
        List<String> result = new ArrayList<>();
        Map<String, Integer> urlCountMap = new HashMap<>();
        accessLogData.stream().forEach(data -> urlCountMap.put(data.getUrl(), urlCountMap.getOrDefault(data.getUrl(), 0) + 1));
        Set<Map.Entry<String, Integer>> entry = urlCountMap.entrySet();
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(entry);
        Collections.sort(entries, (o1, o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        });

        entries.stream().forEach(stringIntegerEntry -> result.add(stringIntegerEntry.getKey()));

        return  result;
    }
}
