package com.fortify.cli.common.rest.wait;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

// TODO Add multithreaded tests that emulate actual state changes
public class WaitHelperTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    public void testNoRequests() {
        try {
            WaitHelper.builder().build().waitUntilAll(null, "SomeState");
            fail("WaitHelper didn't throw exception when no requests configured");
        } catch (RuntimeException expected) {}
    }
    
    @Test
    public void testTimeoutWithException() {
        try {
            WaitHelper.builder()
                .request(u->objectMapper.createObjectNode().put("state", "state1"))
                .currentStateProperty("state")
                .timeoutPeriod("1s")
                .build()
                .waitUntilAll(null, "state2")
                .getResult();
            fail("Timeout didn't result in an exception");
        } catch ( Exception expected ) {}
    }
    
    @Test
    public void testTimeoutWithoutException() {
        ArrayNode result = WaitHelper.builder()
            .request(u->objectMapper.createObjectNode().put("state", "state1"))
            .currentStateProperty("state")
            .timeoutPeriod("1s")
            .failOnTimeout(false)
            .build()
            .waitUntilAll(null, "state2")
            .getResult();
        assertTrue(result.size()==1, "Result after timeout doesn't match expected number of records");
    }
    
    @Test
    public void testMultiWait() {
        try {
            WaitHelper.builder()
                .request(u->objectMapper.createObjectNode().put("state", "state1"))
                .currentStateProperty("state")
                .timeoutPeriod("1s")
                .build()
                .waitUntilAll(null, "state2")
                .waitUntilAny(null, "state1")
                .getResult();
            fail("WaitHelper didn't throw exception when multiple wait*() methods called withh non-blank args");
        } catch ( RuntimeException expected ) {}
    }
    
    @Test
    public void testFailOnFailureState() {
        try {
            WaitHelper.builder()
                .request(u->objectMapper.createObjectNode().put("state", "failureState"))
                .currentStateProperty("state")
                .timeoutPeriod("1s")
                .failureStates("failureState")
                .build()
                .waitUntilAny(null, "state1")
                .getResult();
            fail("WaitHelper didn't throw exception on failure state");
        } catch ( RuntimeException expected ) {}
    }
    
    @Test
    public void testFailOnUnknownState() {
        try {
            WaitHelper.builder()
                .request(u->objectMapper.createObjectNode().put("state", "unknownState"))
                .currentStateProperty("state")
                .timeoutPeriod("1s")
                .knownStates("knownState")
                .build()
                .waitUntilAny(null, "state1")
                .getResult();
            fail("WaitHelper didn't throw exception on unknown state");
        } catch ( RuntimeException expected ) {}
    }
    
}
