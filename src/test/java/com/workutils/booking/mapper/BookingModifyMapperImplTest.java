package com.workutils.booking.mapper;

import com.workutils.booking.mapper.vo.AmendCostCostVO;
import com.workutils.booking.mapper.vo.AmendCostSupplementVO;
import com.workutils.booking.mapper.vo.ModifyCostVO;
import com.workutils.booking.mapper.vo.ModifySupplementVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for BookingModifyMapperImpl.
 * Verifies that the mapper correctly transforms value objects and sets the appropriate update flags.
 */
class BookingModifyMapperImplTest {

    private BookingModifyMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new BookingModifyMapperImpl();
    }

    @Test
    void testMapModifiedSupplement() {
        // Arrange
        ModifyCostVO modifiedCost = new ModifyCostVO("COST001", 150.00, "EUR", "Modified cost");
        String internalCode = "INT123";

        // Act
        AmendCostSupplementVO result = mapper.mapModifiedSupplement(modifiedCost, internalCode);

        // Assert
        assertNotNull(result);
        assertEquals(internalCode, result.getInternalCode());
        assertEquals("COST001", result.getCostCode());
        assertEquals(150.00, result.getAmount());
        assertEquals("EUR", result.getCurrency());
        assertEquals("Modified cost", result.getDescription());
        assertEquals("1", result.getUpdated(), "Updated flag should be '1' for modified supplements");
    }

    @Test
    void testMapUnmodifiedSupplement() {
        // Arrange
        ModifyCostVO originalCost = new ModifyCostVO("COST002", 100.00, "USD", "Original cost");
        ModifySupplementVO supplement = new ModifySupplementVO("SUPP001", "Breakfast", originalCost);

        // Act
        AmendCostSupplementVO result = mapper.mapUnmodifiedSupplement(supplement, originalCost);

        // Assert
        assertNotNull(result);
        assertEquals("SUPP001", result.getSupplementCode());
        assertEquals("Breakfast", result.getSupplementName());
        assertEquals("COST002", result.getCostCode());
        assertEquals(100.00, result.getAmount());
        assertEquals("USD", result.getCurrency());
        assertEquals("Original cost", result.getDescription());
        assertEquals("0", result.getUpdated(), "Updated flag should be '0' for unmodified supplements");
    }

    @Test
    void testMapCost() {
        // Arrange
        ModifyCostVO modifiedCost = new ModifyCostVO("COST003", 200.50, "GBP", "Service charge");

        // Act
        AmendCostCostVO result = mapper.mapCost(modifiedCost);

        // Assert
        assertNotNull(result);
        assertEquals("COST003", result.getCostCode());
        assertEquals(200.50, result.getAmount());
        assertEquals("GBP", result.getCurrency());
        assertEquals("Service charge", result.getDescription());
        assertEquals("1", result.getUpdated(), "Updated flag should be '1' for modified costs");
    }

    @Test
    void testMapModifiedSupplementWithNullValues() {
        // Arrange
        ModifyCostVO modifiedCost = new ModifyCostVO(null, null, null, null);
        String internalCode = null;

        // Act
        AmendCostSupplementVO result = mapper.mapModifiedSupplement(modifiedCost, internalCode);

        // Assert
        assertNotNull(result);
        assertNull(result.getInternalCode());
        assertNull(result.getCostCode());
        assertNull(result.getAmount());
        assertNull(result.getCurrency());
        assertNull(result.getDescription());
        assertEquals("1", result.getUpdated(), "Updated flag should still be '1'");
    }

    @Test
    void testMapUnmodifiedSupplementWithNullValues() {
        // Arrange
        ModifyCostVO originalCost = new ModifyCostVO(null, null, null, null);
        ModifySupplementVO supplement = new ModifySupplementVO(null, null, originalCost);

        // Act
        AmendCostSupplementVO result = mapper.mapUnmodifiedSupplement(supplement, originalCost);

        // Assert
        assertNotNull(result);
        assertNull(result.getSupplementCode());
        assertNull(result.getSupplementName());
        assertNull(result.getCostCode());
        assertNull(result.getAmount());
        assertNull(result.getCurrency());
        assertNull(result.getDescription());
        assertEquals("0", result.getUpdated(), "Updated flag should still be '0'");
    }

    @Test
    void testMapCostWithNullValues() {
        // Arrange
        ModifyCostVO modifiedCost = new ModifyCostVO(null, null, null, null);

        // Act
        AmendCostCostVO result = mapper.mapCost(modifiedCost);

        // Assert
        assertNotNull(result);
        assertNull(result.getCostCode());
        assertNull(result.getAmount());
        assertNull(result.getCurrency());
        assertNull(result.getDescription());
        assertEquals("1", result.getUpdated(), "Updated flag should still be '1'");
    }
}
