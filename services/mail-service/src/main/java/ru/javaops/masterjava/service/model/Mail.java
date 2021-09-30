package ru.javaops.masterjava.service.model;

import com.bertoncelj.jdbi.entitymapper.Column;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Mail extends BaseEntity {
    private String header;
    private @NonNull
    String body;
    private @NonNull
    @Column("sender_id")
    int senderId;
    private @NonNull
    @Column("receiver_id")
    int receiverId;

    public Mail(Integer id, String header, String body, int senderId, int receiverId) {
        this(header, body, senderId, receiverId);
        this.id = id;
    }
}
