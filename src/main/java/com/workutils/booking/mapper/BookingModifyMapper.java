package com.workutils.booking.mapper;

import com.workutils.booking.mapper.vo.AmendCostCostVO;
import com.workutils.booking.mapper.vo.AmendCostSupplementVO;
import com.workutils.booking.mapper.vo.ModifyCostVO;
import com.workutils.booking.mapper.vo.ModifySupplementVO;

/**
 * Mapper interface for booking modification operations.
 * Provides methods to map between modification value objects and amended cost value objects.
 */
public interface BookingModifyMapper {

    /**
     * Maps a modified supplement to an amended cost supplement.
     * This method is used when a supplement has been modified, mapping the cost data
     * from the change along with an internal code.
     * 
     * @param modifiedCost the modified cost data from the change
     * @param internalCode the internal code for the supplement
     * @return an AmendCostSupplementVO with the updated field set to "1"
     */
    AmendCostSupplementVO mapModifiedSupplement(ModifyCostVO modifiedCost, String internalCode);

    /**
     * Maps an unmodified supplement to an amended cost supplement.
     * This method is used when a supplement has not been modified, preserving
     * the original supplement data and its associated cost.
     * 
     * @param supplement the unmodified supplement data
     * @param originalCost the original cost data for the supplement
     * @return an AmendCostSupplementVO with the updated field set to "0"
     */
    AmendCostSupplementVO mapUnmodifiedSupplement(ModifySupplementVO supplement, ModifyCostVO originalCost);

    /**
     * Maps a cost to an amended cost.
     * This method is used to map cost data that has been modified.
     * 
     * @param modifiedCost the modified cost data
     * @return an AmendCostCostVO with the updated field set to "1"
     */
    AmendCostCostVO mapCost(ModifyCostVO modifiedCost);
}
