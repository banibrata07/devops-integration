package com.scm.model.gitlab.commit;
public class Commit {
	private String id;
	private String short_id;
	private String created_at;
	private String[] parent_ids;
	private String title;
	private String message;
	private String author_name;
	private String author_email;
	private String author_date;
	private String committer_name;
	private String committed_date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShort_id() {
		return short_id;
	}

	public void setShort_id(String short_id) {
		this.short_id = short_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String[] getParent_ids() {
		return parent_ids;
	}

	public void setParent_ids(String[] parent_ids) {
		this.parent_ids = parent_ids;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_email() {
		return author_email;
	}

	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
	}

	public String getAuthor_date() {
		return author_date;
	}

	public void setAuthor_date(String author_date) {
		this.author_date = author_date;
	}

	public String getCommitter_name() {
		return committer_name;
	}

	public void setCommitter_name(String committer_name) {
		this.committer_name = committer_name;
	}

	public String getCommitted_date() {
		return committed_date;
	}

	public void setCommitted_date(String committed_date) {
		this.committed_date = committed_date;
	}

}