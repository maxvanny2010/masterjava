package ru.javaops.masterjava.persist.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class City extends RefEntity {
    @NonNull
    private String name;

    public City(String ref, String name) {
        super(ref);
        this.name = name;
    }
}