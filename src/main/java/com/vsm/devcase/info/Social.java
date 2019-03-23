package com.vsm.devcase.info;

public class Social {

	private String github;
	private String email;
	private String site;
	private String youtube;
	
	public Social(String github, String email, String site, String youtube) {
		this.github = github;
		this.email = email;
		this.site = site;
		this.youtube = youtube;
	}
	
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

}
