package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private String x;
    private String y;
    private String z;
}
