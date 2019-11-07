package com.scm.model.github.commit;
import com.scm.model.github.commit.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tree implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("url")
    private String url;

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

    @Override
    public String toString()
    {
        return "ClassPojo [sha = "+sha+", url = "+url+"]";
    }
}
