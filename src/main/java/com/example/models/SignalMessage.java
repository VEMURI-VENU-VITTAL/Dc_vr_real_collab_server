package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignalMessage {
    public String roomId;
    public String from;   // userId of sender
    public String to;     // target userId (nullable)
    public String type;   // "join" | "offer" | "answer" | "ice"
    public Object sdp;    // offer/answer SDP (as JSON)
    public Object candidate; // ICE candidate (as JSON)
}