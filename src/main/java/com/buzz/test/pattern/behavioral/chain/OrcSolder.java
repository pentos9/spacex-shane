package com.buzz.test.pattern.behavioral.chain;

public class OrcSolder extends RequestHandler {

    public OrcSolder(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {
        if (req.getRequestType() == RequestType.COLLECT_TAX) {
            this.printHandling(req);
            req.markHandled();
        } else {
            super.handleRequest(req);
        }
    }

    @Override
    public String toString() {
        return "Orc Solder";
    }
}
