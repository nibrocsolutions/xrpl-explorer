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
"Account",
"AccountTxnID",
"Balance",
"Domain",
"EmailHash",
"Flags",
"LedgerEntryType",
"MessageKey",
"OwnerCount",
"PreviousTxnID",
"PreviousTxnLgrSeq",
"RegularKey",
"Sequence",
"TransferRate",
"index",
"urlgravatar"
})
public class AccountData {

@JsonProperty("Account")
private String account;
@JsonProperty("AccountTxnID")
private String accountTxnID;
@JsonProperty("Balance")
private String balance;
@JsonProperty("Domain")
private String domain;
@JsonProperty("EmailHash")
private String emailHash;
@JsonProperty("Flags")
private Integer flags;
@JsonProperty("LedgerEntryType")
private String ledgerEntryType;
@JsonProperty("MessageKey")
private String messageKey;
@JsonProperty("OwnerCount")
private Integer ownerCount;
@JsonProperty("PreviousTxnID")
private String previousTxnID;
@JsonProperty("PreviousTxnLgrSeq")
private Integer previousTxnLgrSeq;
@JsonProperty("RegularKey")
private String regularKey;
@JsonProperty("Sequence")
private Integer sequence;
@JsonProperty("TransferRate")
private Long transferRate;
@JsonProperty("index")
private String index;
@JsonProperty("urlgravatar")
private String urlgravatar;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("Account")
public String getAccount() {
return account;
}

@JsonProperty("Account")
public void setAccount(String account) {
this.account = account;
}

@JsonProperty("AccountTxnID")
public String getAccountTxnID() {
return accountTxnID;
}

@JsonProperty("AccountTxnID")
public void setAccountTxnID(String accountTxnID) {
this.accountTxnID = accountTxnID;
}

@JsonProperty("Balance")
public String getBalance() {
return balance;
}

@JsonProperty("Balance")
public void setBalance(String balance) {
this.balance = balance;
}

@JsonProperty("Domain")
public String getDomain() {
return domain;
}

@JsonProperty("Domain")
public void setDomain(String domain) {
this.domain = domain;
}

@JsonProperty("EmailHash")
public String getEmailHash() {
return emailHash;
}

@JsonProperty("EmailHash")
public void setEmailHash(String emailHash) {
this.emailHash = emailHash;
}

@JsonProperty("Flags")
public Integer getFlags() {
return flags;
}

@JsonProperty("Flags")
public void setFlags(Integer flags) {
this.flags = flags;
}

@JsonProperty("LedgerEntryType")
public String getLedgerEntryType() {
return ledgerEntryType;
}

@JsonProperty("LedgerEntryType")
public void setLedgerEntryType(String ledgerEntryType) {
this.ledgerEntryType = ledgerEntryType;
}

@JsonProperty("MessageKey")
public String getMessageKey() {
return messageKey;
}

@JsonProperty("MessageKey")
public void setMessageKey(String messageKey) {
this.messageKey = messageKey;
}

@JsonProperty("OwnerCount")
public Integer getOwnerCount() {
return ownerCount;
}

@JsonProperty("OwnerCount")
public void setOwnerCount(Integer ownerCount) {
this.ownerCount = ownerCount;
}

@JsonProperty("PreviousTxnID")
public String getPreviousTxnID() {
return previousTxnID;
}

@JsonProperty("PreviousTxnID")
public void setPreviousTxnID(String previousTxnID) {
this.previousTxnID = previousTxnID;
}

@JsonProperty("PreviousTxnLgrSeq")
public Integer getPreviousTxnLgrSeq() {
return previousTxnLgrSeq;
}

@JsonProperty("PreviousTxnLgrSeq")
public void setPreviousTxnLgrSeq(Integer previousTxnLgrSeq) {
this.previousTxnLgrSeq = previousTxnLgrSeq;
}

@JsonProperty("RegularKey")
public String getRegularKey() {
return regularKey;
}

@JsonProperty("RegularKey")
public void setRegularKey(String regularKey) {
this.regularKey = regularKey;
}

@JsonProperty("Sequence")
public Integer getSequence() {
return sequence;
}

@JsonProperty("Sequence")
public void setSequence(Integer sequence) {
this.sequence = sequence;
}

@JsonProperty("TransferRate")
public Long getTransferRate() {
return transferRate;
}

@JsonProperty("TransferRate")
public void setTransferRate(Long transferRate) {
this.transferRate = transferRate;
}

@JsonProperty("index")
public String getIndex() {
return index;
}

@JsonProperty("index")
public void setIndex(String index) {
this.index = index;
}

@JsonProperty("urlgravatar")
public String getUrlgravatar() {
return urlgravatar;
}

@JsonProperty("urlgravatar")
public void setUrlgravatar(String urlgravatar) {
this.urlgravatar = urlgravatar;
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