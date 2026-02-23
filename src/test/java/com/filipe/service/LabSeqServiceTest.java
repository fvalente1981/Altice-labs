package com.filipe.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LabSeqService class.
 *
 * These tests validate:
 *  - Base cases of the labseq sequence
 *  - Recursive values
 *  - Performance for large inputs
 *  - Error handling for invalid input
 */
@QuarkusTest
class LabSeqServiceTest {

    // Injects the service directly from the Quarkus context.
    @Inject
    LabSeqService service;

    /**
     * Tests the four base values of the labseq sequence:
     * l(0) = 0
     * l(1) = 1
     * l(2) = 0
     * l(3) = 1
     */
    @Test
    void testBaseCases() {
        assertEquals(BigInteger.ZERO, service.labseq(0));
        assertEquals(BigInteger.ONE, service.labseq(1));
        assertEquals(BigInteger.ZERO, service.labseq(2));
        assertEquals(BigInteger.ONE, service.labseq(3));
    }

    /**
     * Tests several recursive values using the formula:
     * l(n) = l(n-4) + l(n-3)
     */
    @Test
    void testRecursiveCases() {
        assertEquals(BigInteger.ONE, service.labseq(4)); // l(0) + l(1)
        assertEquals(BigInteger.ONE, service.labseq(5)); // l(1) + l(2)
        assertEquals(BigInteger.ONE, service.labseq(6)); // l(2) + l(3)
        assertEquals(BigInteger.TWO, service.labseq(7)); // l(3) + l(4)
    }

    /**
     * Ensures that the service can compute large values efficiently.
     * The requirement states that l(100000) must be computed under 10 seconds.
     * This test only checks that the result is produced and not null.
     */
    @Test
    void testLargeValuePerformance() {
        BigInteger result = service.labseq(100000);
        assertNotNull(result);
    }

    /**
     * Ensures that negative input throws an IllegalArgumentException,
     * since the sequence is only defined for non-negative integers.
     */
    @Test
    void testNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> service.labseq(-1));
    }
}