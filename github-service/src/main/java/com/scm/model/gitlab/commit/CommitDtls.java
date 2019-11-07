package com.scm.model.gitlab.commit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommitDtls {
	private String name;
	Commit commit;
	private boolean merged;
	private boolean developers_can_push;
	private boolean developers_can_merge;
	private boolean can_push;
	@JsonProperty("protected")
	private boolean protectedValue;
	@JsonProperty("default")
	private boolean defaultValue;

	public String getName() {
		return name;
	}

	public void setName(String name1) {
		this.name = name1;
	}

	public Commit getCommit() {
		return commit;
	}

	public void setCommit(Commit commit) {
		this.commit = commit;
	}

	public boolean isMerged() {
		return merged;
	}

	public void setMerged(boolean merged) {
		this.merged = merged;
	}

	public boolean isDevelopers_can_push() {
		return developers_can_push;
	}

	public void setDevelopers_can_push(boolean developers_can_push) {
		this.developers_can_push = developers_can_push;
	}

	public boolean isDevelopers_can_merge() {
		return developers_can_merge;
	}

	public void setDevelopers_can_merge(boolean developers_can_merge) {
		this.developers_can_merge = developers_can_merge;
	}

	public boolean isCan_push() {
		return can_push;
	}

	public void setCan_push(boolean can_push) {
		this.can_push = can_push;
	}

	public boolean isProtectedValue() {
		return protectedValue;
	}

	public void setProtectedValue(boolean protectedValue) {
		this.protectedValue = protectedValue;
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
}