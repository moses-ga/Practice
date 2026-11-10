package io.kenzo.learning.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralResponse {
    private Integer action;
    private String message;
}
