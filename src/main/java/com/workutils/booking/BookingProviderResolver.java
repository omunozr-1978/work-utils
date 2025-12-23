package com.workutils.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Resolver class for booking provider services.
 * Maintains a map of provider codes to their corresponding service instances.
 */
public class BookingProviderResolver {
    private final Map<String, BookingProviderService> providerMap;

    /**
     * Constructs a BookingProviderResolver with the provided list of providers.
     * Initializes the internal map by using each provider's code as the key.
     *
     * @param providers list of BookingProviderService instances
     */
    public BookingProviderResolver(List<BookingProviderService> providers) {
        this.providerMap = new HashMap<>();
        if (providers != null) {
            for (BookingProviderService provider : providers) {
                if (provider != null && provider.getProviderCode() != null) {
                    providerMap.put(provider.getProviderCode(), provider);
                }
            }
        }
    }

    /**
     * Resolves a BookingProviderService by its provider code.
     *
     * @param code the provider code to look up
     * @return an Optional containing the provider service if found, empty otherwise
     */
    public Optional<BookingProviderService> resolveByCode(String code) {
        return Optional.ofNullable(providerMap.get(code));
    }
}
