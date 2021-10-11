package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableList;

public class MailWSClientMain {
    public static void main(String[] args) {
        MailWSClient.sendMail(
                ImmutableList.of(new Addressee("To <herokuboot@gmail.com>")),
                ImmutableList.of(new Addressee("Copy <herokuboot@gmail.com>")), "Subject", "Body");
    }
}