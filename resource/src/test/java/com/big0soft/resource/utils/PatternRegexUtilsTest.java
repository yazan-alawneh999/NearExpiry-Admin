package com.big0soft.resource.utils;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class PatternRegexUtilsTest extends TestCase {

    @Test
    public void matches_unit() {
        Assert.assertTrue(PatternRegexUtils.number.matcher("1").matches());
    }
    @Test
    public void not_matches_unit() {
        Assert.assertTrue("is matches",PatternRegexUtils.number.matcher("a1").matches());
    }

}