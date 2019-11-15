package com.scm.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("repos")
public class ScmConfiguration {
    private String environment;
    private List<Repo> repo = new ArrayList<Repo>();
    
    public void setEnvironment(String environment){
        this.environment = environment;
    }
    
    public String getEnvironment(){
        return environment;
    }
    
    public void setRepo(List<Repo> repo){
        this.repo = repo;
    }
    
    public List<Repo> getRepo(){
        return repo;
    }
    
    
}
