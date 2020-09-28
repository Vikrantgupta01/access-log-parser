package com.coding.test;

import com.coding.test.model.AccessLogData;
import com.coding.test.model.Result;

import java.util.List;

public interface DataWriter {
    Result writeData(List<AccessLogData> accessLogData) ;
}
