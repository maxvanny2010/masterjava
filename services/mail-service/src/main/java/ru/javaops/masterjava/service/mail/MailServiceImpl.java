package ru.javaops.masterjava.service.mail;

import ru.javaops.masterjava.service.model.Addressee;
import javax.jws.WebService;
import java.util.*;

@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService")
public class MailServiceImpl implements MailService {
    public void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        MailSender.sendMail(to, cc, subject, body);
    }
}