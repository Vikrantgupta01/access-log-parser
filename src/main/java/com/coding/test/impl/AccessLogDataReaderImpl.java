package com.coding.test.impl;

import com.coding.test.DataReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;

public class AccessLogDataReaderImpl implements DataReader {
    @Override
    public List<String> fetchData() {

        URL res = getClass().getClassLoader().getResource("programming-task-example-data.log");

        try {
            File file = Paths.get(res.toURI()).toFile();
            List<String> contents = FileUtils.readLines(file, "UTF-8");
            return contents;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;

    }
}
