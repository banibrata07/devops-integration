package com.github.model;
import com.github.model.Commit;
import com.github.model.Parents;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parents implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("sha")
    private String sha;
    
    @JsonProperty("html_url")
    private String htmlUrl;
    
    @JsonProperty("url")
    private String url;
    
     public Parents(){
            
     }

    public String getSha ()
    {
        return sha;
    }

    public void setSha (String sha)
    {
        this.sha = sha;
    }
    
    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }
    
    public String getHtmlUrl ()
    {
        return htmlUrl;
    }

    public void setHtmlUrl (String htmlUrl)
    {
        this.htmlUrl = htmlUrl;
    }
    
    @Override
    public String toString()
    {
        return "ClassPojo [html_url = "+htmlUrl+", sha = "+sha+", url = "+url+"]";
    }
    
}