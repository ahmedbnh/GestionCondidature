package com.candidature.application.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.candidature.application.entities.UserModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection="candidature")
public class Candidature {
	
	@Id
	private String id_cand;
	
	private String cv_cand;
	
	private String other_cand;
	
	private String letter_aff_cand;
	
	private Object user_model;
	
	private Object emploi_cand;

	public String getId_cand() {
		return id_cand;
	}

	public void setId_cand(String id_cand) {
		this.id_cand = id_cand;
	}

	public String getCv_cand() {
		return cv_cand;
	}

	public void setCv_cand(String cv_cand) {
		this.cv_cand = cv_cand;
	}

	public String getOther_cand() {
		return other_cand;
	}

	public void setOther_cand(String other_cand) {
		this.other_cand = other_cand;
	}

	public String getLetter_aff_cand() {
		return letter_aff_cand;
	}

	public void setLetter_aff_cand(String letter_aff_cand) {
		this.letter_aff_cand = letter_aff_cand;
	}

	public Object getUser_model() {
		return user_model;
	}

	public void setUser_model(Object user_model) {
		this.user_model = user_model;
	}

	public Object getEmploi_cand() {
		return emploi_cand;
	}

	public void setEmploi_cand(Object emploi_cand) {
		this.emploi_cand = emploi_cand;
	}
}
