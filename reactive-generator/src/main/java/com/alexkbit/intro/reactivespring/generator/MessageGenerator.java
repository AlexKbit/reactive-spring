package com.alexkbit.intro.reactivespring.generator;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.alexkbit.intro.reactivespring.client.MessageClient;
import com.alexkbit.intro.reactivespring.common.dto.MessageDto;

import reactor.core.Disposable;

/**
 * Simple message generator for server.
 */
public class MessageGenerator {

    public static final int MESSAGE_SIZE = 128;
    private static final int DEFAULT_COUNT = 1;
    private static final Random rd = new Random();

    public static void main(String[] args) {
        int count = args.length >= 1 ? Integer.valueOf(args[0]) : DEFAULT_COUNT;
        MessageClient client = new MessageClient();

        List<Disposable> disposables = IntStream.range(0, count)
                .mapToObj(i -> generateMessage())
                .map(msg -> client.save(msg).subscribe(System.out::println))
                .collect(Collectors.toList());

        // Wait result
        Optional<Boolean> res;
        do {
            res = disposables.stream().map(Disposable::isDisposed).reduce(Boolean::logicalAnd);
        } while (res.isPresent() && !res.get());
        System.out.println("Generated: " + count + " messages.");
    }

    private static MessageDto generateMessage() {
        byte[] msg = new byte[MESSAGE_SIZE];
        rd.nextBytes(msg);
        return new MessageDto()
                .setReceiverId(UUID.randomUUID().toString())
                .setSenderId(UUID.randomUUID().toString())
                .setMessage(new String(msg));
    }
}