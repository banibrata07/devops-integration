package com.scm.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.List;



public class Repo {
    private boolean monorepo;
    private boolean multirepo ;
    private String name;
    private List<Project> projects = new ArrayList<Project>();
    
    public void setMonorepo(boolean monorepo){
        this.monorepo = monorepo;
    }
    
    public boolean getMonorepo(){
        return monorepo;
    }
    
    public void setMultirepo(boolean multirepo){
        this.multirepo = multirepo;
    }
    
    public boolean getMultirepo(){
        return multirepo;
    }
    
     public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
     public void setProjects(List<Project> projects){
        this.projects = projects;
    }
    
    public List<Project> getProjects(){
        return projects;
    }
    
    
}