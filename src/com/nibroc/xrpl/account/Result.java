package com.nibroc.xrpl.account;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"account_data",
"ledger_current_index",
"queue_data",
"validated"
})
public class Result {

@JsonProperty("account_data")
private AccountData accountData;
@JsonProperty("ledger_current_index")
private Integer ledgerCurrentIndex;
@JsonProperty("queue_data")
private QueueData queueData;
@JsonProperty("validated")
private Boolean validated;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("account_data")
public AccountData getAccountData() {
return accountData;
}

@JsonProperty("account_data")
public void setAccountData(AccountData accountData) {
this.accountData = accountData;
}

@JsonProperty("ledger_current_index")
public Integer getLedgerCurrentIndex() {
return ledgerCurrentIndex;
}

@JsonProperty("ledger_current_index")
public void setLedgerCurrentIndex(Integer ledgerCurrentIndex) {
this.ledgerCurrentIndex = ledgerCurrentIndex;
}

@JsonProperty("queue_data")
public QueueData getQueueData() {
return queueData;
}

@JsonProperty("queue_data")
public void setQueueData(QueueData queueData) {
this.queueData = queueData;
}

@JsonProperty("validated")
public Boolean getValidated() {
return validated;
}

@JsonProperty("validated")
public void setValidated(Boolean validated) {
this.validated = validated;
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