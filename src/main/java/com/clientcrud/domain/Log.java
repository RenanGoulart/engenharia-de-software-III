package com.clientcrud.domain;

import java.time.LocalDateTime;

public class Log extends DomainEntity {
    private LocalDateTime dateTime;
    private String changedData;
    private DomainEntity entity;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getChangedData() {
        return changedData;
    }

    public void setChangedData(String changedData) {
        this.changedData = changedData;
    }

	public DomainEntity getEntity() {
		return entity;
	}

	public void setEntity(DomainEntity entity) {
		this.entity = entity;
	}
    
    
}
