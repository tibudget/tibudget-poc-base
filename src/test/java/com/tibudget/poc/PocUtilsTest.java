package com.tibudget.poc;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PocUtilsTest {
    @Test
    void getMessageToDisplay() throws Exception {
        assertEquals("Hello ti'budget!", PocUtils.getMessageToDisplay(
                "https://jpresta.com/tibudget/poc.jar",
                new File("C:\\m2\\com\\tibudget\\tibudget-poc-api\\1.3\\tibudget-poc-api-1.3.jar")
        ));
    }
}