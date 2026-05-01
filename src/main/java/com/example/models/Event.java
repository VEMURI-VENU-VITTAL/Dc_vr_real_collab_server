package com.example.models;

import com.example.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@FieldNameConstants
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private String id;
    private String userId;
    private String userName;
    private String sessionId;
    private EventType eventType;
    private Position position;
    private Quaternion quaternion;

    @CreatedDate
    private LocalDateTime createdAt;
}
