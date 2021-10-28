package com.candidature.application.entities;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="emploi")
public class Emploi {

@Id
private String id;

@NotNull(message="Job title cannot be null")
private String titre_emploi;


@NotEmpty(message="This Field must be not empty")
@NotNull(message="Entreprise name cannot be null")
private String nom_entreprise;


@NotNull(message="description cannot be null")
private String description;

private int nbr_candidatures;

private String category;

private String adresse;

@NotEmpty(message="This Field must be not empty")
@NotNull(message="Job type cannot be null")
private String type_emploi;

private String status;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTitre_emploi() {
return titre_emploi;
}

public void setTitre_emploi(String titre_emploi) {
this.titre_emploi = titre_emploi;
}

public String getNom_entreprise() {
return nom_entreprise;
}

public void setNom_entreprise(String nom_entreprise) {

this.nom_entreprise = nom_entreprise;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public int getNbr_candidatures() {
return nbr_candidatures;
}

public void setNbr_candidatures(int nbr_candidatures) {
this.nbr_candidatures = nbr_candidatures;
}

public String getAdresse() {
return adresse;
}

public void setAdresse(String adresse) {
this.adresse = adresse;
}

public String getType_emploi() {
return type_emploi;
}

public void setType_emploi(String type_emploi) {
this.type_emploi = type_emploi;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}




}