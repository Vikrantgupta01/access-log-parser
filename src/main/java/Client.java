import com.coding.test.DataProcessor;
import com.coding.test.DataReader;
import com.coding.test.DataWriter;
import com.coding.test.impl.AccessLogDataProcessorImpl;
import com.coding.test.impl.AccessLogDataReaderImpl;
import com.coding.test.impl.AccessLogDataWriterImpl;
import com.coding.test.model.AccessLogData;
import com.coding.test.model.Result;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

class Client {


    public static void main(String[] args) {
        List<String> logData = getDataReader().fetchData();
        List<AccessLogData> accessLogData = getDataProcessor().parserData(logData);
        Result result = getDataWriter().writeData(accessLogData);
        printResult(result);
    }

    private static void printResult(Result result){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unique Ip address count is : ");
        stringBuilder.append(result.getUniqueIpAddress());
        stringBuilder.append("\n");
        stringBuilder.append("Most Active Ip address  : \n");
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(result.getMostActiveIpAddress().get(i));
            stringBuilder.append("\n");
        }
        stringBuilder.append("Most Active urls  : \n");
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(result.getMostActiveUrls().get(i));
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static DataReader getDataReader() {
        return new AccessLogDataReaderImpl();
    }

    public static DataProcessor getDataProcessor() {
        return new AccessLogDataProcessorImpl();
    }

    public static DataWriter getDataWriter() {
        return new AccessLogDataWriterImpl();
    }
}
