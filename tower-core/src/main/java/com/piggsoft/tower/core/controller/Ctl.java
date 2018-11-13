package com.piggsoft.tower.core.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
public @interface Ctl {
    int value();

}
