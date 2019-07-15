package com.zx.demo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Messages implements Serializable {
    public int mid;
    public String company;
    public String phone;
}
