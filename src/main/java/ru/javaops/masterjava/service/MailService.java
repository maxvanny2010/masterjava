package ru.javaops.masterjava.service;

import ru.javaops.masterjava.service.util.GroupResult;
import ru.javaops.masterjava.service.util.MailResult;
import ru.javaops.masterjava.service.util.MeThreadFactory;
import ru.javaops.masterjava.service.util.MeTimer;
import ru.javaops.masterjava.service.util.StoreResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;
import static ru.javaops.masterjava.service.util.GroupResult.getOrException;
import static ru.javaops.masterjava.service.util.MailResult.sendToUser;

public class MailService {

    public static void main(String[] args) {
        MailService service = new MailService();
        Set<String> emails = new HashSet<>(Arrays.asList("a@a.com", "b@a.com", "c@a.com", "d@a.com", "e@a.com", "f@a.com", "j@a.com"));
        service.sendToList("kodo", emails);
    }

    public GroupResult sendToList(final String template, final Set<String> emails) {
        final ExecutorService exec = Executors.newFixedThreadPool(Math.min(emails.size(), 100), new MeThreadFactory());
        Timer timer = new Timer();
        long start = System.nanoTime();
        List<CompletableFuture<MailResult>> emailFutures = emails.stream()
                .map(email -> CompletableFuture
                        .supplyAsync(() -> sendToUser(template, email), exec)
                        .thenApply(mail -> {
                            timer.schedule(new MeTimer(mail), 10);
                            StoreResult.save(mail);
                            return mail;
                        }))
                .collect(toList());
        GroupResult groupResult = emailFutures.stream()
                .map(future -> future.handleAsync((result, e) -> getOrException(emailFutures, e)))
                .map(CompletableFuture::join)
                .peek(System.out::println)
                .findAny()
                .get();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
        return groupResult;

    }
}
