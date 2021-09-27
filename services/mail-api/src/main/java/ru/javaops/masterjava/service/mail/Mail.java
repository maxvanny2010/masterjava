package ru.javaops.masterjava.service.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * max Vanny
 * 27.09.2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Mail {
    private String header;
    private @NonNull
    String body;
    private @NonNull
    String sender;
    private @NonNull
    String receiver;
}
