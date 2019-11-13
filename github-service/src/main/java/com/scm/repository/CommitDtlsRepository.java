package com.scm.repository;
import java.util.List;
import com.scm.entity.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommitDtlsRepository extends MongoRepository<CommitEntity, String> {

    public CommitEntity findByCommitId(String commitId);
    public List<CommitEntity> findByRepositoryName(String repositoryName);

}