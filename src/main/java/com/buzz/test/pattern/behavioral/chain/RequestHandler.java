package com.buzz.test.pattern.behavioral.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private RequestHandler next;

    public RequestHandler(RequestHandler next) {
        this.next = next;
    }

    public void handleRequest(Request req) {
        if (next != null) {
            next.handleRequest(req);
        }
    }

    protected void printHandling(Request req) {
        logger.info("{} handling request \"{}\"", this, req);
    }

    @Override
    public abstract String toString();
}
