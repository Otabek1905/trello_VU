package uz.jl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestLevel {
    EASY(10),
    MEDIUM(50),
    HARD(90);

    private final int priority;
}
