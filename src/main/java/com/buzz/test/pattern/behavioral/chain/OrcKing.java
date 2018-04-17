package com.buzz.test.pattern.behavioral.chain;

import com.buzz.util.bean.*;

public class OrcKing {
    RequestHandler chain;

    public OrcKing() {
        buildChain();
    }

    private void buildChain() {
        chain = new OrcCommander(new OrcOfficer(new OrcSolder(null)));
    }

    public void makeRequest(Request req) {
        chain.handleRequest(req);
    }
}
