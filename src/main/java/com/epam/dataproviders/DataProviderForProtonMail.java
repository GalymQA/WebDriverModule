package com.epam.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviderForProtonMail {

    @DataProvider(name = "valid-credentials")
    public static Object[][] provideValidCredentials() {
        return new Object[][]{
                {"qapersonstudent1@protonmail.com", "epam930542!#$%^"}
        };
    }

    @DataProvider(name = "invalid-credentials")
    public static Object[][] provideInvalidCredentials() {
        return new Object[][]{
                {"apersonstudent1@protonmail.com", "epam930542!#$%^"},
                {"qapersonstudent1@protonmail.com", "pam930542!#$%^"},
                {"apersonstudent1@protonmail.com", "pam930542!#$%^"},
        };
    }

    @DataProvider(name = "empty-credentials")
    public static Object[][] provideEmptyCredentials() {
        return new Object[][]{
                {"", ""}
        };
    }

}
