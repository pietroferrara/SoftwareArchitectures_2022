package com.example.spring.adder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Additions")
public class LoggedSum {
    private String ip;
    private Date time;
    private String leftValue;
    private String rightValue;
    private String result;
    private Long id;

    protected LoggedSum() {
    }

    public LoggedSum(String ip, Date time, String leftValue, String rightValue, String result) {
        this.ip = ip;
        this.time = time;
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.result = result;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue;
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}