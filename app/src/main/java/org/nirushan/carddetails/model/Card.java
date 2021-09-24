package org.nirushan.carddetails.model;

public class Card {
    private int cardId;
    private String CARD_NUMBER;
    private String EXPIRY_DATE;
    private String CV_CODE;
    private String CARD_OWNER;
    private String CARD_CUSTOMER_ID;
    private String created;
    private double serviceCharge;
    private double total;
    private double qty;

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    private String paymentStatus;

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCARD_NUMBER() {
        return CARD_NUMBER;
    }

    public void setCARD_NUMBER(String CARD_NUMBER) {
        this.CARD_NUMBER = CARD_NUMBER;
    }

    public String getEXPIRY_DATE() {
        return EXPIRY_DATE;
    }

    public void setEXPIRY_DATE(String EXPIRY_DATE) {
        this.EXPIRY_DATE = EXPIRY_DATE;
    }

    public String getCV_CODE() {
        return CV_CODE;
    }

    public void setCV_CODE(String CV_CODE) {
        this.CV_CODE = CV_CODE;
    }

    public String getCARD_OWNER() {
        return CARD_OWNER;
    }

    public void setCARD_OWNER(String CARD_OWNER) {
        this.CARD_OWNER = CARD_OWNER;
    }

    public String getCARD_CUSTOMER_ID() {
        return CARD_CUSTOMER_ID;
    }

    public void setCARD_CUSTOMER_ID(String CARD_CUSTOMER_ID) {
        this.CARD_CUSTOMER_ID = CARD_CUSTOMER_ID;
    }
}
