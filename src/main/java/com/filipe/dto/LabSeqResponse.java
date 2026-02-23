package com.filipe.dto;

import java.math.BigInteger;

/**
 * Simple DTO used to return the LabSeq result in the API response.
 */
public class LabSeqResponse {

    private int index;
    private BigInteger value;

    public LabSeqResponse() {
        // Default constructor required for JSON serialization
    }

    public LabSeqResponse(int index, BigInteger value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}

