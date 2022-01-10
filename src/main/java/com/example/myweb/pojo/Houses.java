package com.example.myweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Houses implements Serializable {
    private String pictureUrl;
    private Double price;
    private String comments;
    private String address;
    private String houseType;
    private Double singlePrice;
}
