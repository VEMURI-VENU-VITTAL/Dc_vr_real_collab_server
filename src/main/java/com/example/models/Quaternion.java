package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quaternion {
    private Float x;
    private Float y;
    private Float z;
    private Float w;
}
