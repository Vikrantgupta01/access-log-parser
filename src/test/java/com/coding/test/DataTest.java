package com.coding.test;

import com.coding.test.impl.AccessLogDataProcessorImpl;
import com.coding.test.impl.AccessLogDataWriterImpl;
import com.coding.test.model.AccessLogData;
import com.coding.test.model.Result;
import org.junit.gen5.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

public class DataTest {

    @Test
    public void testParsing() {
        List<AccessLogData> accessLogData = new AccessLogDataProcessorImpl().parserData(DataFileMock.UNIQUE_2_DATA);
        assertEquals(2, accessLogData.size(), "Data size 2");
        assertEquals("177.71.128.21", accessLogData.get(0).getIpAddress(), "Ip address is not correct");
        assertEquals("/intranet-analytics/", accessLogData.get(0).getUrl(), "Ip address is not correct");
    }

    @Test
    public void testParsingProcessing_FULL_DATA() {
        List<AccessLogData> accessLogData = new AccessLogDataProcessorImpl().parserData(DataFileMock.FULL_DATA);
        Result result = new AccessLogDataWriterImpl().writeData(accessLogData);
        assertEquals(23, accessLogData.size(), "Data size 23");
        assertEquals(11, result.getUniqueIpAddress(), "Unique Ip address is not correct");
        assertEquals("168.41.191.40", result.getMostActiveIpAddress().get(0), "Most active Ip address is not correct");
        assertEquals("/docs/manage-websites/", result.getMostActiveUrls().get(0), "Most active url is not correct");
    }

    @Test
    public void testParsingProcessing_SAME_3_UNIQUE_2_DATA() {
        List<AccessLogData> accessLogData = new AccessLogDataProcessorImpl().parserData(DataFileMock.SAME_3_UNIQUE_2_DATA);
        Result result = new AccessLogDataWriterImpl().writeData(accessLogData);
        assertEquals(4, accessLogData.size(), "Data size 4");
        assertEquals(2, result.getUniqueIpAddress(), "Unique Ip address is not correct");
        assertEquals("177.71.128.21", result.getMostActiveIpAddress().get(0), "Most active Ip address is not correct");
        assertEquals("/intranet-analytics/", result.getMostActiveUrls().get(0), "Most active url is not correct");
    }
}
