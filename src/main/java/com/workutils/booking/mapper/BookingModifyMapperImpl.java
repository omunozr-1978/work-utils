package com.workutils.booking.mapper;

import com.workutils.booking.mapper.vo.AmendCostCostVO;
import com.workutils.booking.mapper.vo.AmendCostSupplementVO;
import com.workutils.booking.mapper.vo.ModifyCostVO;
import com.workutils.booking.mapper.vo.ModifySupplementVO;

/**
 * Default implementation of the BookingModifyMapper interface.
 * Provides mapping logic between modification and amended cost value objects.
 */
public class BookingModifyMapperImpl implements BookingModifyMapper {

    @Override
    public AmendCostSupplementVO mapModifiedSupplement(ModifyCostVO modifiedCost, String internalCode) {
        AmendCostSupplementVO result = new AmendCostSupplementVO();
        result.setInternalCode(internalCode);
        result.setCostCode(modifiedCost.getCostCode());
        result.setAmount(modifiedCost.getAmount());
        result.setCurrency(modifiedCost.getCurrency());
        result.setDescription(modifiedCost.getDescription());
        result.setUpdated("1");
        return result;
    }

    @Override
    public AmendCostSupplementVO mapUnmodifiedSupplement(ModifySupplementVO supplement, ModifyCostVO originalCost) {
        AmendCostSupplementVO result = new AmendCostSupplementVO();
        result.setSupplementCode(supplement.getSupplementCode());
        result.setSupplementName(supplement.getSupplementName());
        result.setCostCode(originalCost.getCostCode());
        result.setAmount(originalCost.getAmount());
        result.setCurrency(originalCost.getCurrency());
        result.setDescription(originalCost.getDescription());
        result.setUpdated("0");
        return result;
    }

    @Override
    public AmendCostCostVO mapCost(ModifyCostVO modifiedCost) {
        AmendCostCostVO result = new AmendCostCostVO();
        result.setCostCode(modifiedCost.getCostCode());
        result.setAmount(modifiedCost.getAmount());
        result.setCurrency(modifiedCost.getCurrency());
        result.setDescription(modifiedCost.getDescription());
        result.setUpdated("1");
        return result;
    }
}
