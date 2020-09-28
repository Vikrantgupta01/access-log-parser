package com.coding.test;

import com.coding.test.model.AccessLogData;

import java.util.List;

public interface DataProcessor {
    List<AccessLogData> parserData(List<String> logData);
}
