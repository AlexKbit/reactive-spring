package com.alexkbit.intro.reactivespring.reader;

import com.alexkbit.intro.reactivespring.client.MessageClient;

import reactor.core.Disposable;

/**
 * Message reader.
 */
public class MessageReader {

    private static final long DEFAULT_LIMIT = 100L;
    private static final int DEFAULT_BUFFER = 2;

    public static void main(String[] args) {
        long limit = args.length >= 1 ? Long.valueOf(args[0]) : DEFAULT_LIMIT;
        int buffer = args.length >= 2 ? Integer.valueOf(args[1]) : DEFAULT_BUFFER;

        MessageClient client = new MessageClient();
        Disposable disposable = client
                .getAll()
                .limitRequest(limit)
                .buffer(buffer)
                .subscribe(System.out::println);

        while (!disposable.isDisposed()) {
            // Wait result
        }
    }
}
