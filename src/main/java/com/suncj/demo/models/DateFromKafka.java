package com.suncj.demo.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DateFromKafka {
    private List<String> messagesFromKafka;
    private Long time_get;
    private Long time_send;
    private Long time_work;

    public DateFromKafka(List<String> messagesFromKafka, Long time_get, Long time_send, Long time_work) {
        this.messagesFromKafka = messagesFromKafka;
        this.time_get = time_get;
        this.time_send = time_send;
        this.time_work = time_work;
    }

    public DateFromKafka() {
    }

    public List<String> getMessagesFromKafka() {
        return messagesFromKafka;
    }

    public void setMessagesFromKafka(List<String> messagesFromKafka) {
        this.messagesFromKafka = messagesFromKafka;
    }

    public Long getTime_get() {
        return time_get;
    }

    public void setTime_get(Long time_get) {
        this.time_get = time_get;
    }

    public Long getTime_send() {
        return time_send;
    }

    public void setTime_send(Long time_send) {
        this.time_send = time_send;
    }

    public Long getTime_work() {
        return time_work;
    }

    public void setTime_work(Long time_work) {
        this.time_work = time_work;
    }
}
