/**
 * Copyright © autonav-vts - MyCompanyf0012325 2024-2024
 */

package com.bilyoner.casestudy.livebet.websocket;

import lombok.Getter;

import java.util.Date;

@Getter
public class OutputMessage {
    private String from;
    private String text;
    private Date time;

    public OutputMessage(String from, String text, Date time) {
        this.from = from;
        this.text = text;
        this.time = time;
    }

    // getters and setters
}
