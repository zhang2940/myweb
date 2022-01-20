package com.example.myweb.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    private Integer id;
    private Integer userId;
    private Integer houseId;

}
