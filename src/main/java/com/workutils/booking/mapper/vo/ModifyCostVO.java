package com.workutils.booking.mapper.vo;

/**
 * Value object representing a modified cost.
 * Contains cost information that has been modified.
 */
public class ModifyCostVO {
    private String costCode;
    private Double amount;
    private String currency;
    private String description;

    public ModifyCostVO() {
    }

    public ModifyCostVO(String costCode, Double amount, String currency, String description) {
        this.costCode = costCode;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    public String getCostCode() {
        return costCode;
    }

    public void setCostCode(String costCode) {
        this.costCode = costCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
