package com.coding.test.impl;

import com.coding.test.DataProcessor;
import com.coding.test.model.AccessLogData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessLogDataProcessorImpl implements DataProcessor {

    final String REGEX = "^(\\S+) (\\S+) (\\S+) " +
            "\\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+)" +
            " (\\S+)\\s*(\\S+)?\\s*\" (\\d{3}) (\\S+)";

    @Override
    public List<AccessLogData> parserData(List<String> logData) {
        List<AccessLogData> accessLogData = new ArrayList<>();
        logData.stream().forEach(s -> accessLogData.add(parseRecord(s)));
        return accessLogData;
    }

    public AccessLogData parseRecord(String record) {
        final Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(record);
        AccessLogData accessLogData = new AccessLogData();
        HashMap<String, Integer> countIP = new HashMap<String, Integer>();
        while (matcher.find()) {
            accessLogData.setIpAddress(matcher.group(1));
            accessLogData.setUrl(matcher.group(6));
        }
        return accessLogData;
    }
}
