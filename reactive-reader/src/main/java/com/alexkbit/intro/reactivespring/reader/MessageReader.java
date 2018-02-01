package com.alexkbit.intro.reactivespring.reader;

import com.alexkbit.intro.reactivespring.client.MessageClient;

import reactor.core.Disposable;

/**
 * Message reader.
 */
public class MessageReader {

    private static final long DEFAULT_LIMIT = 100L;

    public static void main(String[] args) {
        long limit = args.length != 0 ? Long.valueOf(args[0]) : DEFAULT_LIMIT;

        MessageClient client = new MessageClient();
        Disposable disposable = client
                .getAll()
                .limitRequest(limit)
                .buffer(2)
                .subscribe(System.out::println);

        while (!disposable.isDisposed()) {
            // Wait result
        }
    }
}
