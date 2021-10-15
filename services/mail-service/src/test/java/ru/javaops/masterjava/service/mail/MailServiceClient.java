package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class MailServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        Service service = Service.create(
                new URL("http://localhost:8888/mail/mailService?wsdl"),
                new QName("http://mail.javaops.ru/", "MailServiceImplService"));

        MailService mailService = service.getPort(MailService.class);

        String state = mailService.sendToGroup(ImmutableSet.of(new Addressee("herokuboot@gmail.com", null)), null,
                "Group mail subject", "Group mail body");
        System.out.println("Group mail state: " + state);

        GroupResult groupResult = mailService.sendBulk(ImmutableSet.of(
                new Addressee("Heroku Boot <herokuboot@gmail.com>"),
                new Addressee("Bad Email <bad_email.ru>")), "Bulk mail subject", "Bulk mail body");
        System.out.println("\nBulk mail groupResult:\n" + groupResult);
    }
}
