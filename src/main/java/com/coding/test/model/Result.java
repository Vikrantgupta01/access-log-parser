package com.coding.test.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Result {

    private int uniqueIpAddress;
    private List<String> mostActiveIpAddress;
    private List<String> mostActiveUrls;
}
