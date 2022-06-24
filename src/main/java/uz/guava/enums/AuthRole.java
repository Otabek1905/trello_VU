package uz.guava.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthRole {

    ADMIN(100),
    TEACHER(50),
    USER(10);

    private final int priority;
}
