package com.workutils.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

/**
 * Test class for BookingProviderResolver.
 * Tests constructor initialization and resolveByCode method functionality.
 */
@ExtendWith(MockitoExtension.class)
class BookingProviderResolverTest {

    @Mock
    private BookingProviderService provider1;

    @Mock
    private BookingProviderService provider2;

    @Mock
    private BookingProviderService provider3;

    private BookingProviderResolver resolver;

    @BeforeEach
    void setUp() {
        // Configure mock providers with different codes (lenient to avoid unnecessary stubbing warnings)
        lenient().when(provider1.getProviderCode()).thenReturn("PROVIDER_A");
        lenient().when(provider2.getProviderCode()).thenReturn("PROVIDER_B");
        lenient().when(provider3.getProviderCode()).thenReturn("PROVIDER_C");
    }

    @Test
    @DisplayName("Constructor should properly initialize provider map with valid providers")
    void testConstructorInitializesMapCorrectly() {
        // Arrange
        List<BookingProviderService> providers = Arrays.asList(provider1, provider2, provider3);

        // Act
        resolver = new BookingProviderResolver(providers);

        // Assert - Verify all providers are accessible by their codes
        Optional<BookingProviderService> resultA = resolver.resolveByCode("PROVIDER_A");
        Optional<BookingProviderService> resultB = resolver.resolveByCode("PROVIDER_B");
        Optional<BookingProviderService> resultC = resolver.resolveByCode("PROVIDER_C");

        assertTrue(resultA.isPresent(), "PROVIDER_A should be present");
        assertTrue(resultB.isPresent(), "PROVIDER_B should be present");
        assertTrue(resultC.isPresent(), "PROVIDER_C should be present");
        assertEquals(provider1, resultA.get(), "PROVIDER_A should map to provider1");
        assertEquals(provider2, resultB.get(), "PROVIDER_B should map to provider2");
        assertEquals(provider3, resultC.get(), "PROVIDER_C should map to provider3");
    }

    @Test
    @DisplayName("Constructor should handle empty provider list")
    void testConstructorWithEmptyList() {
        // Arrange
        List<BookingProviderService> providers = new ArrayList<>();

        // Act
        resolver = new BookingProviderResolver(providers);

        // Assert - Any code should return empty
        Optional<BookingProviderService> result = resolver.resolveByCode("ANY_CODE");
        assertFalse(result.isPresent(), "Should not resolve any provider when initialized with empty list");
    }

    @Test
    @DisplayName("Constructor should handle null provider list")
    void testConstructorWithNullList() {
        // Act
        resolver = new BookingProviderResolver(null);

        // Assert - Any code should return empty
        Optional<BookingProviderService> result = resolver.resolveByCode("ANY_CODE");
        assertFalse(result.isPresent(), "Should not resolve any provider when initialized with null list");
    }

    @Test
    @DisplayName("Constructor should skip null providers in the list")
    void testConstructorWithNullProviderInList() {
        // Arrange
        List<BookingProviderService> providers = Arrays.asList(provider1, null, provider2);

        // Act
        resolver = new BookingProviderResolver(providers);

        // Assert - Only non-null providers should be accessible
        Optional<BookingProviderService> resultA = resolver.resolveByCode("PROVIDER_A");
        Optional<BookingProviderService> resultB = resolver.resolveByCode("PROVIDER_B");

        assertTrue(resultA.isPresent(), "PROVIDER_A should be present");
        assertTrue(resultB.isPresent(), "PROVIDER_B should be present");
        assertEquals(provider1, resultA.get(), "PROVIDER_A should map to provider1");
        assertEquals(provider2, resultB.get(), "PROVIDER_B should map to provider2");
    }

    @Test
    @DisplayName("Constructor should skip providers with null codes")
    void testConstructorWithNullProviderCode() {
        // Arrange
        lenient().when(provider3.getProviderCode()).thenReturn(null);
        List<BookingProviderService> providers = Arrays.asList(provider1, provider2, provider3);

        // Act
        resolver = new BookingProviderResolver(providers);

        // Assert - Only providers with non-null codes should be accessible
        Optional<BookingProviderService> resultA = resolver.resolveByCode("PROVIDER_A");
        Optional<BookingProviderService> resultB = resolver.resolveByCode("PROVIDER_B");
        Optional<BookingProviderService> resultNull = resolver.resolveByCode(null);

        assertTrue(resultA.isPresent(), "PROVIDER_A should be present");
        assertTrue(resultB.isPresent(), "PROVIDER_B should be present");
        assertFalse(resultNull.isPresent(), "Null code should not resolve to any provider");
    }

    @Test
    @DisplayName("resolveByCode should return correct provider for valid code")
    void testResolveByCodeWithValidCode() {
        // Arrange
        List<BookingProviderService> providers = Arrays.asList(provider1, provider2, provider3);
        resolver = new BookingProviderResolver(providers);

        // Act
        Optional<BookingProviderService> result = resolver.resolveByCode("PROVIDER_B");

        // Assert
        assertTrue(result.isPresent(), "Result should be present for valid code");
        assertEquals(provider2, result.get(), "Should return provider2 for PROVIDER_B");
    }

    @Test
    @DisplayName("resolveByCode should return empty Optional for invalid code")
    void testResolveByCodeWithInvalidCode() {
        // Arrange
        List<BookingProviderService> providers = Arrays.asList(provider1, provider2, provider3);
        resolver = new BookingProviderResolver(providers);

        // Act
        Optional<BookingProviderService> result = resolver.resolveByCode("INVALID_CODE");

        // Assert
        assertFalse(result.isPresent(), "Result should be empty for invalid code");
    }

    @Test
    @DisplayName("resolveByCode should return empty Optional for null code")
    void testResolveByCodeWithNullCode() {
        // Arrange
        List<BookingProviderService> providers = Arrays.asList(provider1, provider2, provider3);
        resolver = new BookingProviderResolver(providers);

        // Act
        Optional<BookingProviderService> result = resolver.resolveByCode(null);

        // Assert
        assertFalse(result.isPresent(), "Result should be empty for null code");
    }

    @Test
    @DisplayName("resolveByCode should return empty Optional for empty string code")
    void testResolveByCodeWithEmptyStringCode() {
        // Arrange
        List<BookingProviderService> providers = Arrays.asList(provider1, provider2, provider3);
        resolver = new BookingProviderResolver(providers);

        // Act
        Optional<BookingProviderService> result = resolver.resolveByCode("");

        // Assert
        assertFalse(result.isPresent(), "Result should be empty for empty string code");
    }

    @Test
    @DisplayName("resolveByCode should handle case-sensitive codes")
    void testResolveByCodeIsCaseSensitive() {
        // Arrange
        List<BookingProviderService> providers = Arrays.asList(provider1, provider2, provider3);
        resolver = new BookingProviderResolver(providers);

        // Act
        Optional<BookingProviderService> result = resolver.resolveByCode("provider_a");

        // Assert
        assertFalse(result.isPresent(), "Result should be empty for incorrect case");
    }

    @Test
    @DisplayName("resolveByCode should work correctly with single provider")
    void testResolveByCodeWithSingleProvider() {
        // Arrange
        List<BookingProviderService> providers = Arrays.asList(provider1);
        resolver = new BookingProviderResolver(providers);

        // Act
        Optional<BookingProviderService> result = resolver.resolveByCode("PROVIDER_A");

        // Assert
        assertTrue(result.isPresent(), "Result should be present for valid code");
        assertEquals(provider1, result.get(), "Should return provider1");
    }

    @Test
    @DisplayName("resolveByCode should return empty Optional when no providers initialized")
    void testResolveByCodeWithNoProviders() {
        // Arrange
        resolver = new BookingProviderResolver(new ArrayList<>());

        // Act
        Optional<BookingProviderService> result = resolver.resolveByCode("PROVIDER_A");

        // Assert
        assertFalse(result.isPresent(), "Result should be empty when no providers exist");
    }
}
