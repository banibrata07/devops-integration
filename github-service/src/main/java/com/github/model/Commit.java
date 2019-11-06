package com.github.model;
import com.github.model.Committer;
import com.github.model.Author;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit implements Serializable{

    private static final long serialVersionUID = 1L;
 
    @JsonProperty("tree")
    private Tree tree;
    
     @JsonProperty("comment_count")
    private String comment_count;
    
     @JsonProperty("url")
    private String url;

    @JsonProperty("verification")
    private Verification verification;

    @JsonProperty("committer")
    private Committer committer;

    @JsonProperty("author")
    private Author author;

    @JsonProperty("message")
    private String message;
    
    public Commit(){
     
    }
     public Commit(Tree tree,String comment_count, String url,Verification verification,Committer committer,
                     Author author,
                     String message) {
      
        this.tree =tree;
        this.comment_count = comment_count;
        this.url=url;
        this.verification=verification;
        this.committer = committer;
        this.author = author;
        this.author = author;
      }
 
    
    public String getComment_count ()
    {
        return comment_count;
    }

    public void setComment_count (String comment_count)
    {
        this.comment_count = comment_count;
    }
    
    public Tree getTree ()
    {
        return tree;
    }

    public void setTree (Tree tree)
    {
        this.tree = tree;
    }
    
     public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public Verification getVerification ()
    {
        return verification;
    }

    public void setVerification (Verification verification)
    {
        this.verification = verification;
    }


 
   

    public Committer getCommitter ()
    {
        return committer;
    }

    public void setCommitter (Committer committer)
    {
        this.committer = committer;
    }

    public Author getAuthor ()
    {
        return author;
    }

    public void setAuthor (Author author)
    {
        this.author = author;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [comment_count = "+comment_count+", committer = "+committer+", author = "+author+", tree = "+tree+", message = "+message+", url = "+url+", verification = "+verification+"]";
    }
}
			
			