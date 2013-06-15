package org.cloudsdale.responses;

import org.cloudsdale.models.User;

import java.util.ArrayList;

public class UserResponse extends Response {

	private User result;
	private ArrayList<User> results;

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }

    public ArrayList<User> getResults() {
        return results;
    }

    public void setResults(ArrayList<User> results) {
        this.results = results;
    }
}
