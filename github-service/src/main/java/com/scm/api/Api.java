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
	
	

	@RequestMapping(value="/scm/{scmsource}/{user}/{repo}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public List<MyPojo> findCommitInfo(@PathVariable("user") String user,@PathVariable("scmsource") String scmsource,@PathVariable("repo") String repo) {
		String scmsource = scmsource;
		String output="test";
		String outputText="";
		 MyPojo  pojo = null;
		 List<MyPojo> pojoList = null;
		
		try {
			String urlStr = "https://api.github.com/repos/userReplace/repo-replace/commits";
			urlStr = urlStr.replace("userReplace",user).replace("repo-replace",repo);
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
		
			
		//	 output = "[{\"sha\": \"36e5aa66732760ec6c2e615e4b174c2dc2fbfbd1\",\"commit\":{\"author\":{\"name\":\"root\",\"email\":\"root@ip-172-31-25-46.ec2.internal\",\"date\":\"2019-10-31T09:59:29Z\"},\"committer\":{\"name\":\"root\",\"email\":\"root@ip-172-31-25-46.ec2.internal\",\"date\":\"2019-10-31T09:59:29Z\"},\"message\":\"test commit\"}},{\"sha\": \"37e5aa66732760ec6c2e615e4b174c2dc2fbfbd1\",\"commit\":{\"author\":{\"name\":\"root\",\"email\":\"root@ip-172-31-25-46.ec2.internal\",\"date\":\"2019-10-28T08:22:11Z\"},\"committer\":{\"name\":\"root\",\"email\":\"root@ip-172-31-25-46.ec2.internal\",\"date\":\"2019-10-28T08:22:11Z\"},\"message\":\"port change\"}}]";
		      //output= "[{\"sha\": null,\"node_id\": \"MDY6Q29tbWl0MjA4Nzg1NjI2OjM2ZTVhYTY2NzMyNzYwZWM2YzJlNjE1ZTRiMTc0YzJkYzJmYmZiZDE=\",\"commit\": {\"author\": {\"name\": \"root\",\"email\": \"root@ip-172-31-25-46.ec2.internal\",\"date\": \"2019-10-31T09:59:29Z\"},\"committer\": {\"name\": \"root\",\"email\": \"root@ip-172-31-25-46.ec2.internal\",\"date\": \"2019-10-31T09:59:29Z\"},\"message\": \"test commit\",\"tree\": {\"sha\": \"31b0bf91b80022c46fe0d34a97ee5fd0afc95deb\",\"url\": \"https://api.github.com/repos/banibrata07/sample-spring-microservices/git/trees/31b0bf91b80022c46fe0d34a97ee5fd0afc95deb\"},\"url\": \"https://api.github.com/repos/banibrata07/sample-spring-microservices/git/commits/36e5aa66732760ec6c2e615e4b174c2dc2fbfbd1\",\"comment_count\": 0,\"verification\": {\"verified\": false,\"reason\": \"unsigned\",\"signature\": \"abcd\",\"payload\": \"abcd\"}},\"url\": \"https://api.github.com/repos/banibrata07/sample-spring-microservices/commits/36e5aa66732760ec6c2e615e4b174c2dc2fbfbd1\",\"html_url\": \"https://github.com/banibrata07/sample-spring-microservices/commit/36e5aa66732760ec6c2e615e4b174c2dc2fbfbd1\",\"comments_url\": \"https://api.github.com/repos/banibrata07/sample-spring-microservices/commits/36e5aa66732760ec6c2e615e4b174c2dc2fbfbd1/comments\",\"author\": null,\"committer\": null,\"parents\": [{\"sha\": \"5aa35bb28dc2950b2b9d55c0cf9b768e7704e877\",\"url\": \"https://api.github.com/repos/banibrata07/sample-spring-microservices/commits/5aa35bb28dc2950b2b9d55c0cf9b768e7704e877\",\"html_url\": \"https://github.com/banibrata07/sample-spring-microservices/commit/5aa35bb28dc2950b2b9d55c0cf9b768e7704e877\"}]}]";
		    //output="[{\"sha\":\"a31b16437ad5cbf7c7f363382456b3ea4b7025ca\",\"node_id\":\"MDY6Q29tbWl0MTI5NTA1ODg2OmEzMWIxNjQzN2FkNWNiZjdjN2YzNjMzODI0NTZiM2VhNGI3MDI1Y2E=\",\"commit\":{\"author\":{\"name\":\"banibrata07\",\"email\":\"38375317+banibrata07@users.noreply.github.com\",\"date\":\"2018-04-14T10:15:19Z\"},\"committer\":{\"name\":\"GitHub\",\"email\":\"noreply@github.com\",\"date\":\"2018-04-14T10:15:19Z\"},\"message\":\"check\",\"tree\":{\"sha\":\"6c683c382f2bf3929ad7cc9bb4e31a0d71c07072\",\"url\":\"https://api.github.com/repos/banibrata07/workspace-test/git/trees/6c683c382f2bf3929ad7cc9bb4e31a0d71c07072\"},\"url\":\"https://api.github.com/repos/banibrata07/workspace-test/git/commits/a31b16437ad5cbf7c7f363382456b3ea4b7025ca\",\"comment_count\":0,\"verification\":{\"verified\":true,\"reason\":\"valid\",\"signature\":\"BEGIN PGP SIGNATURE\\n\\nwsBcBAABCAAQBQJa0dS3CRBK7hj4Ov3rIwAAdHIIABJ1bWtY0oA/RWUbE25QIXr0\\nFfSjfg08EvC7V4e/UqkCCTTPMu3Y4LPuwH4g6PhLXDHvUuPqZNEVcfMW6y4jRl9j\\ncw5Qf3/5mQxSypqQZBtoQIOkj3xcyXCU3o9dXr43qnPdQcEkkc89biZQ1kPpQ8l5\\nV8RS+GOeh+oSdAaH+5PwAtNuGRS14HUU4cRn+taj2K84d/mWKaYQlJG8zpobSpy9\\nIjFAkPl/fdlJye/faIkxE82dUtEUYo1nuSE1OFofUcUsTO9mAPnNuNv8vjVgxKCv\\n0YQ2YhdjxJNRLjgq7q3C1bs3P0GgmBj0dQlHAXTrtA+jwzcDNqCnciBDp2/tkuA=\\n=pT1h\\nEND PGP SIGNATURE\\n\",\"payload\":\"tree 6c683c382f2bf3929ad7cc9bb4e31a0d71c07072\\nparent 79007e1a694f7671a6e34127f91e067e71a97827\\nauthor banibrata07 <38375317+banibrata07@users.noreply.github.com> 1523700919 +0530\\ncommitter GitHub <noreply@github.com> 1523700919 +0530\\n\\ncheck\"}},\"url\":\"https://api.github.com/repos/banibrata07/workspace-test/commits/a31b16437ad5cbf7c7f363382456b3ea4b7025ca\",\"html_url\":\"https://github.com/banibrata07/workspace-test/commit/a31b16437ad5cbf7c7f363382456b3ea4b7025ca\",\"comments_url\":\"https://api.github.com/repos/banibrata07/workspace-test/commits/a31b16437ad5cbf7c7f363382456b3ea4b7025ca/comments\",\"author\":{\"login\":\"banibrata07\",\"id\":38375317,\"node_id\":\"MDQ6VXNlcjM4Mzc1MzE3\",\"avatar_url\":\"https://avatars3.githubusercontent.com/u/38375317?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/banibrata07\",\"html_url\":\"https://github.com/banibrata07\",\"followers_url\":\"https://api.github.com/users/banibrata07/followers\",\"following_url\":\"https://api.github.com/users/banibrata07/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/banibrata07/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/banibrata07/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/banibrata07/subscriptions\",\"organizations_url\":\"https://api.github.com/users/banibrata07/orgs\",\"repos_url\":\"https://api.github.com/users/banibrata07/repos\",\"events_url\":\"https://api.github.com/users/banibrata07/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/banibrata07/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"web-flow\",\"id\":19864447,\"node_id\":\"MDQ6VXNlcjE5ODY0NDQ3\",\"avatar_url\":\"https://avatars3.githubusercontent.com/u/19864447?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/web-flow\",\"html_url\":\"https://github.com/web-flow\",\"followers_url\":\"https://api.github.com/users/web-flow/followers\",\"following_url\":\"https://api.github.com/users/web-flow/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/web-flow/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/web-flow/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/web-flow/subscriptions\",\"organizations_url\":\"https://api.github.com/users/web-flow/orgs\",\"repos_url\":\"https://api.github.com/users/web-flow/repos\",\"events_url\":\"https://api.github.com/users/web-flow/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/web-flow/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[{\"sha\":\"79007e1a694f7671a6e34127f91e067e71a97827\",\"url\":\"https://api.github.com/repos/banibrata07/workspace-test/commits/79007e1a694f7671a6e34127f91e067e71a97827\",\"html_url\":\"https://github.com/banibrata07/workspace-test/commit/79007e1a694f7671a6e34127f91e067e71a97827\"}]},{\"sha\":\"79007e1a694f7671a6e34127f91e067e71a97827\",\"node_id\":\"MDY6Q29tbWl0MTI5NTA1ODg2Ojc5MDA3ZTFhNjk0Zjc2NzFhNmUzNDEyN2Y5MWUwNjdlNzFhOTc4Mjc=\",\"commit\":{\"author\":{\"name\":\"banibrata07\",\"email\":\"38375317+banibrata07@users.noreply.github.com\",\"date\":\"2018-04-14T10:14:27Z\"},\"committer\":{\"name\":\"GitHub\",\"email\":\"noreply@github.com\",\"date\":\"2018-04-14T10:14:27Z\"},\"message\":\"Initial commit\",\"tree\":{\"sha\":\"0f0f624bb8ec2a5326f6803b220bcd319c62a00a\",\"url\":\"https://api.github.com/repos/banibrata07/workspace-test/git/trees/0f0f624bb8ec2a5326f6803b220bcd319c62a00a\"},\"url\":\"https://api.github.com/repos/banibrata07/workspace-test/git/commits/79007e1a694f7671a6e34127f91e067e71a97827\",\"comment_count\":0,\"verification\":{\"verified\":true,\"reason\":\"valid\",\"signature\":\"BEGIN PGP SIGNATURE\\n\\nwsBcBAABCAAQBQJa0dSDCRBK7hj4Ov3rIwAAdHIIABHnWOT6WIMAHE4QYEGpgGa0\\nYJwJpU6vxq6vthGG77eD5X+JLbKb535JJmgSAPEfD6c+ETORgiF3PSgQ4+kJ3SLL\\n/sJtGT3Qjc1WW+lsnqYeoYoYRuqv8H9KHC72aeUPLqA8nmCnw6d/z76RSizOvMOe\\nMqrxHN7UDpmzqXs0E3s95Wvc9oDDZZRH+f6tB1ty1ewY6v6lOmRBZwkaPWN1cKXO\\nXsJnjjoGd5B/T0FVR0fJdu6cs9lojecwbK95aFSlaElOOhHyTfZwYNgAXZrk+Njn\\nycKyzs9GDvkKSzyMbi0HC0794LUKpL1SWF7RM0XlAUH9H+CYlD7U2H2rZYxatLw=\\n=yFDW\\nEND PGP SIGNATURE\\n\",\"payload\":\"tree 0f0f624bb8ec2a5326f6803b220bcd319c62a00a\\nauthor banibrata07 <38375317+banibrata07@users.noreply.github.com> 1523700867 +0530\\ncommitter GitHub <noreply@github.com> 1523700867 +0530\\n\\nInitial commit\"}},\"url\":\"https://api.github.com/repos/banibrata07/workspace-test/commits/79007e1a694f7671a6e34127f91e067e71a97827\",\"html_url\":\"https://github.com/banibrata07/workspace-test/commit/79007e1a694f7671a6e34127f91e067e71a97827\",\"comments_url\":\"https://api.github.com/repos/banibrata07/workspace-test/commits/79007e1a694f7671a6e34127f91e067e71a97827/comments\",\"author\":{\"login\":\"banibrata07\",\"id\":38375317,\"node_id\":\"MDQ6VXNlcjM4Mzc1MzE3\",\"avatar_url\":\"https://avatars3.githubusercontent.com/u/38375317?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/banibrata07\",\"html_url\":\"https://github.com/banibrata07\",\"followers_url\":\"https://api.github.com/users/banibrata07/followers\",\"following_url\":\"https://api.github.com/users/banibrata07/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/banibrata07/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/banibrata07/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/banibrata07/subscriptions\",\"organizations_url\":\"https://api.github.com/users/banibrata07/orgs\",\"repos_url\":\"https://api.github.com/users/banibrata07/repos\",\"events_url\":\"https://api.github.com/users/banibrata07/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/banibrata07/received_events\",\"type\":\"User\",\"site_admin\":false},\"committer\":{\"login\":\"web-flow\",\"id\":19864447,\"node_id\":\"MDQ6VXNlcjE5ODY0NDQ3\",\"avatar_url\":\"https://avatars3.githubusercontent.com/u/19864447?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/web-flow\",\"html_url\":\"https://github.com/web-flow\",\"followers_url\":\"https://api.github.com/users/web-flow/followers\",\"following_url\":\"https://api.github.com/users/web-flow/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/web-flow/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/web-flow/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/web-flow/subscriptions\",\"organizations_url\":\"https://api.github.com/users/web-flow/orgs\",\"repos_url\":\"https://api.github.com/users/web-flow/repos\",\"events_url\":\"https://api.github.com/users/web-flow/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/web-flow/received_events\",\"type\":\"User\",\"site_admin\":false},\"parents\":[]}]";
		    
	    	output = output.replace("\n","\\n");
	    
	    	System.out.println("----------------------------------------------------------------------------------------------------");
	    	System.out.println(output);
		    
		     ObjectMapper mapper = new ObjectMapper();
		     mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		     mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
		     

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
