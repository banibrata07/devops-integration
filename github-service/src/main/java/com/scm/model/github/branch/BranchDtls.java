package com.scm.model.github.branch;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchDtls  implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("protected")
    private String protectedDtls;

    @JsonProperty("commit")
    private CommitDtls commit;
     
    public BranchDtls(){
     
    }
   
   public BranchDtls(String name,
                     String protectedDtls,
                     CommitDtls commit) {
        this.protectedDtls = protectedDtls;
        this.name = name;
        this.commit = commit;
   }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    

    public String getProtectedDtls ()
    {
        return protectedDtls;
    }

    public void setProtectedDtls (String protectedDtls)
    {
        this.protectedDtls = protectedDtls;
    }
    
    public CommitDtls getCommit ()
    {
        return commit;
    }

    public void setCommit (CommitDtls commit)
    {
        this.commit = commit;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", protectedDtls = "+protectedDtls+", commit = "+commit+"]";
    }
}