package org.cloudsdale.responses;

import org.cloudsdale.models.Cloud;

import java.util.List;

public class CloudResponse extends Response {

	private Cloud		result;
	private List<Cloud>	results;

	public Cloud getResult() {
		return result;
	}

	public void setResult(Cloud result) {
		this.result = result;
	}

	public List<Cloud> getResults() {
		return results;
	}

	public void setResults(List<Cloud> results) {
		this.results = results;
	}
}
