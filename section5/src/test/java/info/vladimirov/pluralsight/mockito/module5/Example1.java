package info.vladimirov.pluralsight.mockito.module5;


import info.vladimirov.pluralsight.mockito.module5.model.AccountCurrency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.MockSettings;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Example1 {



    static abstract class SampleExample {
        private final String name;

        private final String description;

        public SampleExample(String name, String abv) {
            this.name = name;
            this.description = abv;
        }

        private SampleExample() {

            this.name = null;
            this.description = null;
        }

        String getName() {
            return name;
        }
        String getDescription() {
            return description;
        }

    }

    @Test
    void mockWithConstructor() {
        final String name = "John Smith";
        final String description = "This is a test example";

        SampleExample bankAuditService = mock(SampleExample.class,
                Mockito.withSettings().useConstructor(name, description).defaultAnswer(CALLS_REAL_METHODS));

        assertEquals(bankAuditService.getName(), name);
        assertEquals(bankAuditService.getDescription(), description);
    }






    record Address(String street, String city, String country) {

        String getFullAddress() {
            return street.concat(city).concat(country);
        }
    }

    static final class Address1 {

        final String address;
        public Address1(String address) {
            this.address = address;
        }
        final String getFullAddress() {
            return address;
        }

        static String getFullAddress1() {
            return "This is a static method returning the address ";
        }

        static String getFullAddress2(String arg) {
            return "This is a static method returning the address " + arg;
        }
    };



    @Test
    void mockFinalTypesAndEnums() {
        AccountCurrency accountCurrency = mock();

        System.out.println(AccountCurrency.USD);
        System.out.println(accountCurrency);

        when(accountCurrency.toString()).thenReturn("This is a mock response");
        System.out.println("After toString being overridden " + accountCurrency);

        Address address = mock();
        when(address.city()).thenReturn("Los Angeles");
        System.out.println(address.city());

        when(address.getFullAddress()).thenReturn("Los Angeles 23, CA");
        System.out.println(address.getFullAddress());

        Address1 address1 = mock(Address1.class);
        when(address1.getFullAddress()).thenReturn("Mock address1 response");
        System.out.println(address1.getFullAddress());

    }


    @Test
    void mockStaticMethodArg() {
        final String expectedOutcome = "This is a mock method";

        Assertions.assertNotEquals(expectedOutcome, Address1.getFullAddress1());
        System.out.println("Static Method outcome " + Address1.getFullAddress1()+ "]");

        try(MockedStatic<Address1> address1 = mockStatic(Address1.class)) {
            address1.when(new MockedStatic.Verification() {
                @Override
                public void apply() throws Throwable {
                    Address1.getFullAddress2("");
                }
            }).thenReturn(expectedOutcome);
            Assertions.assertEquals(expectedOutcome, Address1.getFullAddress2(""));
            System.out.println("Static Method outcome [" + Address1.getFullAddress2("") + "]");
        }
    }


    @Test
    void mockStaticMethod() {
        final String expectedOutcome = "This is a mock method";

        Assertions.assertNotEquals(expectedOutcome, Address1.getFullAddress1());
        System.out.println("Static Method outcome " + Address1.getFullAddress1()+ "]");

        MockedStatic<Address1> address1 = mockStatic(Address1.class);
            address1.when(Address1::getFullAddress1).thenReturn(expectedOutcome);
            Assertions.assertEquals(expectedOutcome, Address1.getFullAddress1());
            System.out.println("Static Method outcome [" + Address1.getFullAddress1() + "]");
        //}
        address1.close();
    }

    @Test
    void postStaticMethod() {
        System.out.println(Address1.getFullAddress1());
    }






    @Test
    void onConstructionInvocation() {
        try(MockedConstruction mock = mockConstruction(Address.class)) {
            Address address = new Address("No name street", "New York", "US");
            System.out.println("before mocking the method call " + address.city());
            when(address.city()).thenReturn("Miami");

            System.out.println("After mocking the method call " + address.city());

        }

    }
























}
