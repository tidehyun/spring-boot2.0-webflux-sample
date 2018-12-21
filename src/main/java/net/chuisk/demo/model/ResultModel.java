package net.chuisk.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class ResultModel {
    private int code;
    private String description;
    private Object data;
}
