package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.api.ConversionRateService;
import info.vladimirov.pluralsight.mockito.module3.api.DbConnector;
import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;
import info.vladimirov.pluralsight.mockito.module3.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class Example4_7 {


    /**
     * Change default return value
     */
    @Test
    void defaultReturnValue() {

        ConversionRateService conversionRateService = mock();

        Double conversionRate =
                conversionRateService.getConvertedRate(new AccountCurrency(null), null);

        System.out.println("The value of conversion rate is " + conversionRate);
        Assertions.assertNotNull(conversionRate);

        HashMap map = mock(HashMap.class);
        Assertions.assertNotNull(map.entrySet());

        List list = mock();
        Assertions.assertNull(list.iterator());

        MyInterface myInterface = mock();
        Assertions.assertNotNull(myInterface.getList());

    }


    interface MyInterface {
        List getList();
    }


    /**
     * {@link org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues}
     */

    @Test
    void defaultReturnDefaultsExplicitCall() {

        ConversionRateService conversionRateService = mock(ConversionRateService.class,
                Mockito.RETURNS_DEFAULTS);

        Double conversionRate =
                conversionRateService.getConvertedRate(new AccountCurrency(null), null);

        System.out.println("The value of conversion rate is " + conversionRate);
        Assertions.assertNotNull(conversionRate);
    }


    @Test
    void defaultSmartNulls() {

        ConversionRateService conversionRateService = mock(ConversionRateService.class,
                Mockito.RETURNS_SMART_NULLS);
        Double conversionRate =
                conversionRateService.getConvertedRate(new AccountCurrency(null), null);

        System.out.println("The value of conversion rate is " + conversionRate);
        Assertions.assertNotNull(conversionRate);

        DbConnector dbConnector = mock(DbConnector.class, Mockito.RETURNS_SMART_NULLS);

        UserAccount userAccount = dbConnector.getBankAccount("Some bank account");

        //Uncomment the below call to receive the SmartNullPointerException exception
        //userAccount.setName("Some name");


    }















    @Test
    void defaultDeepStubs() {
        UserAccount userAccount = mock(UserAccount.class,
                Mockito.RETURNS_DEEP_STUBS);
        final double returnedValue = 100D;
        when(userAccount.getAccountBalance().get(any())).thenReturn(returnedValue);

        Assertions.assertEquals(returnedValue, userAccount.getAccountBalance().get(new AccountCurrency("EUR")));

        System.out.println("Deep stubs " +
                userAccount.getAccountBalance().get(new AccountCurrency("EUR")));

        UserAccount userAccount1 = mock(UserAccount.class);
        Map<AccountCurrency, Double> mockMap = mock();
        when(userAccount1.getAccountBalance()).thenReturn(mockMap);
        when(mockMap.get(any())).thenReturn(returnedValue);


        Assertions.assertEquals(returnedValue, userAccount1.getAccountBalance().get(new AccountCurrency("EUR")));

        System.out.println("Normal mock " +
                userAccount1.getAccountBalance().get(new AccountCurrency("EUR")));
    }


    @Test
    void returnSelfMocks() {
        UserAccount userAccount = mock(UserAccount.class,
                RETURNS_MOCKS);

        System.out.println(userAccount);
        Mockito.when(userAccount.getName()).thenReturn("John Smith");
        System.out.println("Self obj hash code " + userAccount.getSelf());

        UserAccount secondMock = userAccount.getSelf();
        System.out.println("Get self mock to configure  " + secondMock);
        when(secondMock.getName()).thenReturn("Patrik Smith");
        System.out.println("Self mock configured and original mock return self hash code " + userAccount.getSelf());

        Assertions.assertNotEquals(userAccount, userAccount.getSelf());

        System.out.println(userAccount.getAccountBalance());

        System.out.println("User account1 returned name " + userAccount.getName());

        System.out.println("User account1 self returned name " + userAccount.getSelf());


    }


    static class TestBuilder {

        private String testName;
        private Integer testExecutionTimes;

        public String getTestName() {
            return testName;
        }

        public Integer getTestExecutionTimes() {
            return testExecutionTimes;
        }


        public static class Builder {
            private String testName;
            private Integer testExecutionTimes;


            Builder testName(String testName) {
                this.testName = testName;
                return this;
            }

            Builder testExecutionTimes(Integer testExecutionTimes) {
                this.testExecutionTimes = testExecutionTimes;
                return this;
            }


            TestBuilder builder() {
                TestBuilder testBuilder = new TestBuilder();
                testBuilder.testName = this.testName;
                testBuilder.testExecutionTimes = testExecutionTimes;
                return testBuilder;
            }

        }

    }



    @Test
    void returnSelf() {
        TestBuilder.Builder testBuilder = mock(TestBuilder.Builder.class,
                RETURNS_SELF);

        when(testBuilder.builder()).thenReturn(mock());

        testBuilder.testName("John").testExecutionTimes(100);
        TestBuilder test = testBuilder.testName("someTest")
                .testExecutionTimes(1000).builder();
        verify(testBuilder).testName(Mockito.eq("John"));
        verify(testBuilder).testExecutionTimes(Mockito.eq(100));
        System.out.println("Mock obj after build: " + test);

    }





    @Test
    void returnSelfUserAccount() {
        UserAccount userAccount = mock(UserAccount.class,
                RETURNS_SELF);

        Mockito.when(userAccount.getName()).thenReturn("John Smith");
        System.out.println("Self obj hash code " + userAccount.getSelf().getName());

        UserAccount secondMock = userAccount.getSelf();
        when(secondMock.getName()).thenReturn("Patrik Smith");

        System.out.println("User account1 returned name " + userAccount.getName());

        System.out.println("User account1 self returned name " + userAccount.getSelf().getName());

    }







    @Test
    void partialMock() {
        UserAccount userAccount = mock(UserAccount.class,
                CALLS_REAL_METHODS);
        System.out.println("Username before configure mock " + userAccount.getName());

        userAccount.setName("John Smith");

        System.out.println("Username after configuring the mock " + userAccount.getName());
        System.out.println("Configuring the doReturn/when pattern");
        doReturn("Mr Peterson").when(userAccount).getName();

        System.out.println("Username after being configured " + userAccount.getName());

        //WARNING
        System.out.println("Configuring the doReturn/when pattern");
       // when(userAccount.isNameSet()).thenReturn(false);
        doReturn(false).when(userAccount).isNameSet();


        System.out.println("Method isNameSet after configuration " + userAccount.isNameSet());
    }


















    @Test
    void customAnswer() {
        UserAccount userAccount = mock(UserAccount.class, invocation -> "This is just a mock");
        System.out.println("When calling the mock " + userAccount);
        System.out.println("When calling a mock method " + userAccount.getAddress());
        UserAccount userAccountBetter = mock(UserAccount.class, invocation ->
                {
                    if (invocation.getMethod().getName().contains("getAddress")) {
                        return "Called getAddress method";
                    }
                    return "default response";
                }
        );

        System.out.println("When calling a userAccountBetter method getAddress:  " + userAccountBetter.getAddress());

    }
}
