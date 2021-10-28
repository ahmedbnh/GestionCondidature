package com.candidature.application.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.http.HttpEntity;

import com.candidature.application.entities.Candidature;
import com.candidature.application.repositories.CandidatureRepository;
import com.candidature.application.entities.Emploi;
import com.candidature.application.entities.UserModel;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CandidatureController {
	@Autowired
	private RestTemplateBuilder rtb;
	
	@Autowired
	private CandidatureRepository cand_rep;
	
	public static String getBearerTokenHeader() {
	    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
	  }
	
	@GetMapping("/candidatures")
	public ResponseEntity<?> getAllCandidature() {
		List<Candidature> candidatures = cand_rep.findAll();
		if (candidatures.size() > 0) {
			return new ResponseEntity<List<Candidature>>(candidatures, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Contact not available!", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/postuler/{id_emp}")
	public ResponseEntity<?> addCandidature(@RequestBody Candidature candidature, @PathVariable String id_emp) {
		try {
			Candidature c = new Candidature();
			c.setCv_cand(candidature.getCv_cand());
			c.setLetter_aff_cand(candidature.getLetter_aff_cand());
			c.setOther_cand(candidature.getOther_cand());
			
			//Emploi e = emp_rep.findById(id_emp).get();
			Object e = rtb.build().getForObject("https://jobrecruitement.herokuapp.com/emploibyid/"+id_emp, Object.class);
			/*if (emp_rep.findById(id_emp).isPresent()) {
				c.setEmploi_cand(e);
			}*/
			//UserModel u = user_rep.findById(id_user).get();
			c.setEmploi_cand(e);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", getBearerTokenHeader());
		    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		    ResponseEntity<String> response = rtb.build().exchange("https://authrecruitement.herokuapp.com/getuserconnected", HttpMethod.GET, entity, String.class);
			c.setUser_model(response.getBody());
			
			/*if (user_rep.findById(id_user).isPresent()) {
				c.setUser_model(u);
			} else {
				return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
			}*/
			cand_rep.save(c);
			return new ResponseEntity<>("Candidature added successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/delete_candidature/{id}")
	public ResponseEntity<?> deletecandidature(@PathVariable("id") String id){
	try{
		cand_rep.deleteById(id);
	return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
	}catch(Exception e){
	return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@PutMapping("/update_condidature/{id_con}")
	public ResponseEntity<?> updateCandidature(@RequestBody Candidature candidature, @PathVariable String id_con) {
		try {
			if (cand_rep.findById(id_con).isPresent()) {
				Candidature existedcon = cand_rep.findById(id_con).get();
				if (candidature.getCv_cand().equals("")) {
					existedcon.setCv_cand(existedcon.getCv_cand());
				} else {
					existedcon.setCv_cand(candidature.getCv_cand());
				}
				if (candidature.getOther_cand().equals("")) {
					existedcon.setOther_cand(existedcon.getOther_cand());
				} else {
					existedcon.setOther_cand(candidature.getOther_cand());
				}
				
				if (candidature.getLetter_aff_cand().equals("")) {
					existedcon.setLetter_aff_cand(existedcon.getLetter_aff_cand());
				} else {
					existedcon.setLetter_aff_cand(candidature.getLetter_aff_cand());
				}
				
				cand_rep.save(existedcon);
			} else {
				return new ResponseEntity<>("Candidature not found!", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Candidature>(candidature, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}


	
	
	
	
	