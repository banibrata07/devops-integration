package com.scm.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.scm.service.JmsReceiver;
import com.scm.model.github.commit.*;
import com.scm.model.gitlab.commit.*;
import com.scm.model.github.branch.*;
import org.springframework.context.annotation.Bean;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class Api {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${scm.github.commit.url}")
	String githubCommitUrl;
	
	@Value("${scm.github.branch.url}")
	String githubBranchUrl;
	

	@RequestMapping(value="/scm/{scmsource}/{user}/{repo}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public List<MyPojo> findCommitInfo(@PathVariable("user") String user,@PathVariable("scmsource") String scmsource,@PathVariable("repo") String repo) {
		String sourceScm = scmsource;
		String output="test";
		String outputText="";
		 MyPojo  pojo = null;
		 List<MyPojo> pojoList = null;
		 List<BranchDtls> branchDtlsList = null;
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		 mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
		
		try {
			String urlBranchStr = githubBranchUrl.replace("userReplace",user).replace("repo-replace",repo);
			URL url = new URL(urlBranchStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader brBranch = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			
			output = ReadBigStringIn(brBranch);
				
			conn.disconnect();
			
			output = output.replace("\n","\\n");
			
			try {
        		branchDtlsList = mapper.readValue(output, new TypeReference<List<BranchDtls>>(){});
				}catch (IOException e) {
			          e.printStackTrace();
			    }
			    
			    
		        String urlStr = githubCommitUrl.replace("userReplace",user).replace("repo-replace",repo);
				
				for(BranchDtls branch : branchDtlsList){
					System.out.println(branch);
					String newUrl = urlStr.replace("shaReplace",branch.getCommit().getSha());
					url = new URL(newUrl);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
		
					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}
		
					BufferedReader br = new BufferedReader(new InputStreamReader(
							(conn.getInputStream())));
		
					System.out.println("Output from Server .... \n");
					
					output = ReadBigStringIn(br);
						
					conn.disconnect();
				
					
				
			    	 output = output.replace("\n","\\n");
			    
			    	 System.out.println("----------------------------------------------------------------------------------------------------");
			    	 System.out.println(output);
		    
		    
		     

		        	try {
		        		pojoList = mapper.readValue(output, new TypeReference<List<MyPojo>>(){});
						/*for(MyPojo commit : pojoList){
							System.out.println(commit);
						}*/
		
						System.out.println("---------------------------Send Message------------------------------------------");
						jmsTemplate.convertAndSend("inbound.topic", pojoList);
						System.out.println("---------------------------Send Message successful------------------------------------------");
			        } catch (IOException e) {
			    		  e.printStackTrace();
			        }
				}
			
		
			
	        
	        
				 /*   String developerJson="{\"name\":\"test\",\"commit\":{\"id\":\"5464654\",\"short_id\":\"898979\",\"created_at\":\"2019-05-09\",\"parent_ids\":[\"ghg465dvdv\"],\"title\":\"test\",\"message\":\"test message\",\"author_name\":\"test\",\"author_email\":\"test@gmail.com\",\"author_date\":\"2019-05-08\",\"committer_name\":\"test\",\"committed_date\":\"2019-06-07\"},\"merged\":true,\"protected\":true,\"developers_can_push\":false,\"developers_can_merge\":false,\"can_push\":false,\"default\":false}";
					String commitJson="{\"id\":\"5464655\",\"short_id\":\"898979\",\"created_at\":\"2019-05-09\",\"parent_ids\":[\"ghg465dvdv\"],\"title\":\"test\",\"message\":\"test message\",\"author_name\":\"test\",\"author_email\":\"test@gmail.com\",\"author_date\":\"2019-05-08\",\"committer_name\":\"test\",\"committed_date\":\"2019-06-07\"}";
					
					CommitDtls developer=mapper.readValue(developerJson, CommitDtls.class);
					System.out.println(developer);
					com.scm.model.gitlab.commit.Commit commit=mapper.readValue(commitJson, com.scm.model.gitlab.commit.Commit.class);
					System.out.println(commit);*/
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	
	
		 return pojoList;
	}
	
	public String ReadBigStringIn(BufferedReader buffIn) throws IOException {
	    StringBuilder everything = new StringBuilder();
	    String line;
	    while( (line = buffIn.readLine()) != null) {
	       everything.append(line);
	    }
	    return everything.toString();
	}



	
}
