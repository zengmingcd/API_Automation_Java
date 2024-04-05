package testcases.demo;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "demo")
    public Object[][] demo_dataprovider(){
        Object[][] dataset = new Object[][]{
                {"AAA", 111},
                {"BBB", 222}
        };
        return dataset;
    }
}
