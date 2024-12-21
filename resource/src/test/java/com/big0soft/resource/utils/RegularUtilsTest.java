package com.big0soft.resource.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegularUtilsTest {
    @Test
    public void testUsernameRegex1() {
        assertTrue("abc123".matches(RegularUtils.REGEX_USERNAME_1));
        assertTrue("Abc123".matches(RegularUtils.REGEX_USERNAME_1));
        assertTrue("username123".matches(RegularUtils.REGEX_USERNAME_1));
        assertFalse("123abc".matches(RegularUtils.REGEX_USERNAME_1));
        assertFalse("_abc123".matches(RegularUtils.REGEX_USERNAME_1));
    }

    @Test
    public void testUsernameRegex2() {
        assertTrue("abc123".matches(RegularUtils.REGEX_USERNAME_2));
        assertTrue("Abc123".matches(RegularUtils.REGEX_USERNAME_2));
        assertTrue("username123".matches(RegularUtils.REGEX_USERNAME_2));
        assertFalse("123abc".matches(RegularUtils.REGEX_USERNAME_2));
        assertFalse("_abc123".matches(RegularUtils.REGEX_USERNAME_2));
    }

    @Test
    public void testUsernameRegex3() {
        assertTrue("abc123".matches(RegularUtils.REGEX_USERNAME_3));
        assertTrue("أهلا123".matches(RegularUtils.REGEX_USERNAME_3));
        assertFalse("123 !".matches(RegularUtils.REGEX_USERNAME_3));
        assertFalse("_abc123".matches(RegularUtils.REGEX_USERNAME_3));
    }

    @Test
    public void testUsernameRegex4() {
        assertTrue("abc123".matches(RegularUtils.REGEX_USERNAME_4));
        assertTrue("أهلا 123".matches(RegularUtils.REGEX_USERNAME_4));
        assertFalse("123 !".matches(RegularUtils.REGEX_USERNAME_4));
        assertFalse("_abc123".matches(RegularUtils.REGEX_USERNAME_4));
        assertFalse("._abc123".matches(RegularUtils.REGEX_USERNAME_4));
        assertFalse(". _abc123".matches(RegularUtils.REGEX_USERNAME_4));
        assertFalse(" _ab22.c123".matches(RegularUtils.REGEX_USERNAME_4));
    }

    @Test
    public void testPasswordRegex() {
        assertTrue("abc123".matches(RegularUtils.REGEX_PASSWORD));
        assertTrue("Abc123".matches(RegularUtils.REGEX_PASSWORD));
        assertTrue("password123".matches(RegularUtils.REGEX_PASSWORD));
        assertFalse("12ab".matches(RegularUtils.REGEX_PASSWORD));
        assertFalse("123456789012345678901".matches(RegularUtils.REGEX_PASSWORD));
    }

    @Test
    public void testParseUsernameToEmail() {
        assertEquals("ahmad@" + domain + ".com", RegularUtils.parseUsernameToEmail("ahmad"));
        assertThrows(RuntimeException.class, () -> RegularUtils.parseUsernameToEmail("ahmad@"));
    }

    @Test
    public void testParseUsernameToEmail_DefaultDomain() {
        String username = "john";
        String expectedEmail = "john@" + domain + ".com";

        String result = RegularUtils.parseUsernameToEmail(username);

        assertEquals(expectedEmail, result);
    }

    String domain = "big0soft";

    @Test
    public void testParseUsernameToEmail_CustomDomain() {
        String username = "mary";

        String expectedEmail = "mary@" + domain + ".com";

        String result = RegularUtils.parseUsernameToEmail(username, domain);

        assertEquals(expectedEmail, result);
    }

    @Test
    public void testParseUsernameToEmail_InvalidEmail() {
        String username = "invalid@gmail";

        assertThrows(RuntimeException.class, () -> RegularUtils.parseUsernameToEmail(username));
    }

    @Test
    public void testParseUsernameToEmail_CustomDomain_InvalidEmail() {
        String username = "invalid";
        domain = domain + ".";

        assertThrows(RuntimeException.class, () -> RegularUtils.parseUsernameToEmail(username, domain));
    }

    @Test
    public void testParseUsernameToEmail_MockedIsEmail() {
        String username = "mocked";
        String expectedEmail = "mocked@" + domain + ".com";

        String result = RegularUtils.parseUsernameToEmail(username, domain);

        assertEquals(expectedEmail, result);
    }
}