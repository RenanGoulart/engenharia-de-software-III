package com.clientcrud.domain;

public class CreditCard extends DomainEntity {
    private String number;
    private String holderName;
    private String cvv;
    private boolean isMain;
    private CardFlag cardFlag;

    public CreditCard() {}
    
    public CreditCard(String number, String holderName, String cvv, boolean isMain, CardFlag cardFlag) {
    	setNumber(number);
    	setHolderName(holderName);
    	setCvv(cvv);
    	setIsMain(isMain);
    	setCardFlag(cardFlag);    	
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(boolean main) {
        this.isMain = main;
    }

    public CardFlag getCardFlag() {
        return cardFlag;
    }

    public void setCardFlag(CardFlag cardFlag) {
        this.cardFlag = cardFlag;
    }
}
