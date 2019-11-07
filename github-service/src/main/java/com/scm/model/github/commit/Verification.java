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
public class Verification implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("signature")
    private String signature;

    @JsonProperty("payload")
    private String payload;

    @JsonProperty("verified")
    private String verified;

    public String getReason ()
    {
        return reason;
    }

    public void setReason (String reason)
    {
        this.reason = reason;
    }

    public String getSignature ()
    {
        return signature;
    }

    public void setSignature (String signature)
    {
        this.signature = signature;
    }

    public String getPayload ()
    {
        return payload;
    }

    public void setPayload (String payload)
    {
        this.payload = payload;
    }

    public String getVerified ()
    {
        return verified;
    }

    public void setVerified (String verified)
    {
        this.verified = verified;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [reason = "+reason+", signature = "+signature+", payload = "+payload+", verified = "+verified+"]";
    }
}