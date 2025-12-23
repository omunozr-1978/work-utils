package com.workutils.booking;

/**
 * Interface representing a booking provider service.
 * Each provider has a unique code identifier.
 */
public interface BookingProviderService {
    /**
     * Returns the unique provider code for this service.
     * 
     * @return the provider code
     */
    String getProviderCode();
}
