package com.postTheProblem;

public class Issue {
	private int issue_id;
	private String reference_id;
	private String petitioner_name;
	private String petitioner_mobile;
	private String petitioner_email;
	private String prob_pic_urls;
	private String prob_statement;
	private String village;
	private int mandal_id;
	private int dist_id;
	private String status;
	private String status_description;

	public int getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}

	public String getReference_id() {
		return reference_id;
	}

	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

	public String getPetitioner_name() {
		return petitioner_name;
	}

	public void setPetitioner_name(String petitioner_name) {
		this.petitioner_name = petitioner_name;
	}

	public String getPetitioner_mobile() {
		return petitioner_mobile;
	}

	public void setPetitioner_mobile(String petitioner_mobile) {
		this.petitioner_mobile = petitioner_mobile;
	}

	public String getPetitioner_email() {
		return petitioner_email;
	}

	public void setPetitioner_email(String petitioner_email) {
		this.petitioner_email = petitioner_email;
	}

	public String getProb_pic_urls() {
		return prob_pic_urls;
	}

	public void setProb_pic_urls(String prob_pic_urls) {
		this.prob_pic_urls = prob_pic_urls;
	}

	public String getProb_statement() {
		return prob_statement;
	}

	public void setProb_statement(String prob_statement) {
		this.prob_statement = prob_statement;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public int getMandal_id() {
		return mandal_id;
	}

	public void setMandal_id(int mandal_id) {
		this.mandal_id = mandal_id;
	}

	public int getDistrict_id() {
		return dist_id;
	}

	public void setDistrict_id(int district_id) {
		this.dist_id = district_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_description() {
		return status_description;
	}

	public void setStatus_description(String status_description) {
		this.status_description = status_description;
	}

}
