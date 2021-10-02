package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.service.model.Addressee;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class MailServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        Service service = Service.create(
                new URL("http://localhost:8888/mail/mailService?wsdl"),
                new QName("http://mail.service.masterjava.javaops.ru/", "MailServiceImplService"));

        MailService mailService = service.getPort(MailService.class);
        mailService.sendMail(ImmutableList.of(new Addressee("maxpraha@gmail.com", null)), null, "Subject", "Body");
    }
}
