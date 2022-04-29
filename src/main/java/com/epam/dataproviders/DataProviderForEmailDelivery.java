package com.epam.dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviderForEmailDelivery {

    @DataProvider(name = "valid-credentials-for-email-delivery")
    public static Object[][] provideValidCredentials() {
        return new Object[][]{
                {"qapersonstudent1@protonmail.com",
                        "epam930542!#$%^",
                        "ViktorPotapov7575@yahoo.com",
                        "epam34534f#%$",
                        "Test subject",
                        "Test email from ProtonMail to YahooMail"}
        };
    }

}
