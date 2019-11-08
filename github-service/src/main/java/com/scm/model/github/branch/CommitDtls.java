package com.scm.model.github.branch;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitDtls implements Serializable{

    private static final long serialVersionUID = 1L;
 
   
    @JsonProperty("url")
    private String url;


    @JsonProperty("sha")
    private String sha;
    
    public CommitDtls(){
     
    }
     public CommitDtls( String sha,
                     String url) {
        this.sha =sha;
        this.url = url;
       
      }
 
    

    
    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

   

    public String getSha ()
    {
        return sha;
    }

    public void setSha (String sha)
    {
        this.sha = sha;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [url = "+url+", sha = "+sha+"]";
    }
}
			
			