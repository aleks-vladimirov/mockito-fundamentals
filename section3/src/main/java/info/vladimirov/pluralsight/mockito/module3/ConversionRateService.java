package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;

import java.math.BigDecimal;

public interface ConversionRateService {

    BigDecimal getConvertedRate(AccountCurrency currency, BigDecimal amoung);
}
