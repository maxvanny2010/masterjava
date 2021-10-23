package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import ru.javaops.masterjava.web.WebStateException;
import javax.activation.DataHandler;
import java.io.File;
import java.net.MalformedURLException;

import static com.google.common.collect.ImmutableList.of;

@Slf4j
public class MailWSClientMain {
    public static void main(String[] args) throws WebStateException, MalformedURLException {
        String state = MailWSClient.sendToGroup(
                ImmutableSet.of(new Addressee("To <herokuboot@gmail.com>")),
                ImmutableSet.of(new Addressee("Copy <herokuboot@gmail.com>")), "Subject", "Body",
                of(new Attachment("version.html",
                        new DataHandler(new File("config_templates/version.html").toURI().toURL()))
                ));
        System.out.println(state);
    }
}