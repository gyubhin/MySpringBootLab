package com.rookies3.MySpringbootLab.property;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MyEnvironment {
    String mode;

    public MyEnvironment(String mode){
        this.mode = mode;
    }
}
