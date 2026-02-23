package com.filipe.service;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

@ApplicationScoped
public class LabSeqService {

    // Volatile array used as an in-memory cache for computed labseq values.
    // 'volatile' ensures visibility across threads.
    private volatile BigInteger[] cache = initBaseCache();

    // Lock used to synchronize cache expansion and computation to ensure thread safety.
    private final ReentrantLock lock = new ReentrantLock();

    // Initializes the first four values of the labseq sequence.
    // These are the base cases defined in the problem statement.
    private static BigInteger[] initBaseCache() {
        BigInteger[] base = new BigInteger[4];
        base[0] = BigInteger.ZERO;          // l(0) = 0
        base[1] = BigInteger.ONE;           // l(1) = 1
        base[2] = BigInteger.ZERO;          // l(2) = 0
        base[3] = BigInteger.ONE;           // l(3) = 1
        return base;
    }

    // Computes the labseq value for a given index n.
    // Uses caching to avoid recomputing previously calculated values.
    public BigInteger labseq(int n) {

        // The sequence is only defined for non-negative integers.
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        // Local reference for performance (avoids repeated volatile reads).
        BigInteger[] localCache = cache;

        // Fast path: if the value is already cached, return it immediately.
        if (n < localCache.length && localCache[n] != null) {
            return localCache[n];
        }

        // Lock to ensure only one thread expands the cache or computes missing values.
        lock.lock();
        try {
            // If the requested index is outside the current cache size, expand the array.
            if (n >= cache.length) {
                int newSize = Math.max(n + 1, cache.length * 2);
                cache = Arrays.copyOf(cache, newSize);
            }

            // Compute all missing values up to index n.
            for (int i = 0; i <= n; i++) {
                if (cache[i] == null) {

                    // Base cases
                    if (i == 0) {
                        cache[0] = BigInteger.ZERO;
                    } else if (i == 1) {
                        cache[1] = BigInteger.ONE;
                    } else if (i == 2) {
                        cache[2] = BigInteger.ZERO;
                    } else if (i == 3) {
                        cache[3] = BigInteger.ONE;

                        // Recursive case: l(n) = l(n-4) + l(n-3)
                    } else {
                        cache[i] = cache[i - 4].add(cache[i - 3]);
                    }
                }
            }

            // Return the computed (and now cached) value.
            return cache[n];

        } finally {
            // Always release the lock, even if an exception occurs.
            lock.unlock();
        }
    }
}