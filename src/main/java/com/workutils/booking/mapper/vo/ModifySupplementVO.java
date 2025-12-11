package com.workutils.booking.mapper.vo;

/**
 * Value object representing a supplement that may or may not have been modified.
 * Contains supplement information and a reference to its original cost.
 */
public class ModifySupplementVO {
    private String supplementCode;
    private String supplementName;
    private ModifyCostVO cost;

    public ModifySupplementVO() {
    }

    public ModifySupplementVO(String supplementCode, String supplementName, ModifyCostVO cost) {
        this.supplementCode = supplementCode;
        this.supplementName = supplementName;
        this.cost = cost;
    }

    public String getSupplementCode() {
        return supplementCode;
    }

    public void setSupplementCode(String supplementCode) {
        this.supplementCode = supplementCode;
    }

    public String getSupplementName() {
        return supplementName;
    }

    public void setSupplementName(String supplementName) {
        this.supplementName = supplementName;
    }

    public ModifyCostVO getCost() {
        return cost;
    }

    public void setCost(ModifyCostVO cost) {
        this.cost = cost;
    }
}
