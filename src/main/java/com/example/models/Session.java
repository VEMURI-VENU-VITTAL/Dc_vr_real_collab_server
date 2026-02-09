package com.example.models;

import com.example.enums.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Session {
    @Id
    private String id;
    private LocalDateTime start;
    private LocalDateTime end;
    private SessionStatus sessionStatus;
    private String hostId;
}
