package com.coda.test.util;

import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternMatchingTest {

    private final static PatternMatching patternMatching = ParserUtil.getPatternMatchingInstance();

    @Test
    void testMatchEmailWithCorrectEmailAddress() {

        patternMatching.matchEmail("osephine_darakjy@darakjy.org");
        Assert.check(patternMatching.matchEmail("osephine_darakjy@darakjy.org"));

    }

    @Test
    void testMatchEmailWithIncorrectEmailAddress() {
    }

    @Test
    void matchPhoneNumberWithCorrectPhoneNumber() {
    }

    @Test
    void matchPhoneNumberWithInCorrectPhoneNumber() {
    }
}