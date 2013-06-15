package org.cloudsdale.responses;

import org.cloudsdale.models.Session;

public class SessionResponse extends Response {
	
	private Session result;

    public Session getResult() {
        return result;
    }

    public void setResult(Session result) {
        this.result = result;
    }
}
