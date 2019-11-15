package com.scm.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "commitEntity")
public class CommitEntity {

    @Id
    public String id;
    public String scmName;
    public String repositoryName;
    public String branchName;
    public String commitOwner;
    public String commitComment;
    public String commitId;
    public String timestamp;

    public CommitEntity() {}

    public CommitEntity(String scmName, String repositoryName,String branchName,String commitOwner,String commitComment,String commitId, String timestamp) {
        this.scmName =scmName;
        this.repositoryName=repositoryName;
        this.branchName=branchName;
        this.commitId=commitId;
        this.timestamp=timestamp;
        this.commitOwner=commitOwner;
        this.commitComment=commitComment;
    }

    @Override
    public String toString() {
        return String.format(
                "commitEntity[id=%s, scmName='%s', repositoryName='%s, branchName='%s, commitId='%s, timestamp='%s']",id, scmName, repositoryName,branchName,commitId,timestamp);
    }

}