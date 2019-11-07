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
public class MyPojo implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("sha")
    private String sha;
    
    @JsonProperty("html_url")
    private String htmlUrl;
    
    @JsonProperty("comments_url")
    private String commentsUrl;
    
    @JsonProperty("url")
    private String url;
    
    @JsonProperty("node_id")
    private String nodeId;
     
    @JsonProperty("parents")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Parents> parents = new ArrayList();

    @JsonProperty("committer")
    private ParentCommitter parentCommitter;

    @JsonProperty("author")
    private ParentAuthor parentAuthor;

    @JsonProperty("commit")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Commit> commit;

    public MyPojo(){
        
    }

    public MyPojo(List<Commit> commit){
        this.commit=commit;
    }

    public List<Commit> getCommit ()
    {
        return commit;
    }

    public void setCommit (List<Commit> commit)
    {
        this.commit = commit;
    }
    
    
    public String getCommentsUrl ()
    {
        return commentsUrl;
    }

    public void setCommentsUrl (String commentsUrl)
    {
        this.commentsUrl = commentsUrl;
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

    public String getNodeId ()
    {
        return nodeId;
    }

    public void setNodeId (String nodeId)
    {
        this.nodeId = nodeId;
    }

    public List<Parents> getParents ()
    {
        return parents;
    }

    public void setParents (List<Parents> parents)
    {
        this.parents = parents;
    }
    
    
    public String getHtmlUrl ()
    {
        return htmlUrl;
    }

    public void setHtmlUrl (String htmlUrl)
    {
        this.htmlUrl = htmlUrl;
    }

    public ParentCommitter getParentCommitter ()
    {
        return parentCommitter;
    }

    public void setParentCommitter (ParentCommitter parentCommitter)
    {
        this.parentCommitter = parentCommitter;
    }

    public ParentAuthor getParentAuthor ()
    {
        return parentAuthor;
    }

    public void setParentAuthor (ParentAuthor parentAuthor)
    {
        this.parentAuthor = parentAuthor;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [committer = "+parentCommitter+", author = "+parentAuthor+", html_url = "+htmlUrl+", commit = "+commit+", comments_url = "+commentsUrl+", sha = "+sha+", url = "+url+", node_id = "+nodeId+", parents = "+parents+"]";
    }
}
