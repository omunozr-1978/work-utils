package com.workutils.booking.mapper.vo;

/**
 * Value object representing an amended cost.
 * Contains cost information along with an update indicator.
 */
public class AmendCostCostVO {
    private String costCode;
    private Double amount;
    private String currency;
    private String description;
    private String updated;

    public AmendCostCostVO() {
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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
