package com.zx.demo.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Emp {
    private int eid;
    private String ename;
    private String esex;
    private String epassword;
    private Timestamp ecreaton;
    private String eaddress;


}
