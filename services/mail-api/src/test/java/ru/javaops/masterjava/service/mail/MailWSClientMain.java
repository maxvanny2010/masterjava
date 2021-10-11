package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;

public class MailWSClientMain {
    public static void main(String[] args) {
        MailWSClient.sendToGroup(
                ImmutableSet.of(new Addressee("To <herokuboot@gmail.com>")),
                ImmutableSet.of(new Addressee("Copy <herokuboot@gmail.com>")), "Subject", "Body");
    }
}