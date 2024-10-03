package com.nibroc.xrpl.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"forwarded",
"id",
"result",
"status",
"type",
"warnings"
})
public class AccountInfo {

@JsonProperty("forwarded")
private Boolean forwarded;
@JsonProperty("id")
private Integer id;
@JsonProperty("result")
private Result result;
@JsonProperty("status")
private String status;
@JsonProperty("type")
private String type;
@JsonProperty("warnings")
private List<Warning> warnings = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("forwarded")
public Boolean getForwarded() {
return forwarded;
}

@JsonProperty("forwarded")
public void setForwarded(Boolean forwarded) {
this.forwarded = forwarded;
}

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("result")
public Result getResult() {
return result;
}

@JsonProperty("result")
public void setResult(Result result) {
this.result = result;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("type")
public String getType() {
return type;
}

@JsonProperty("type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("warnings")
public List<Warning> getWarnings() {
return warnings;
}

@JsonProperty("warnings")
public void setWarnings(List<Warning> warnings) {
this.warnings = warnings;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}