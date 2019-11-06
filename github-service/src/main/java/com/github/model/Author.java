package com.github.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author  implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("date")
    private String date;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;
    public Author(){
        
    }

   public Author(String date,
                     String name,
                     String email) {
        this.date = date;
        this.name = name;
        this.email = email;
   }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [date = "+date+", name = "+name+", email = "+email+"]";
    }
}