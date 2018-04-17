package com.buzz.test.pattern.behavioral.chain;

public class OrcCommander extends RequestHandler {

    public OrcCommander(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {

        if (req.getRequestType() == RequestType.DEFEND_CASTLE) {
            this.printHandling(req);
            req.markHandled();
        } else {
            super.handleRequest(req);
        }

    }

    @Override
    public String toString() {
        return "Orc Commander";
    }
}
