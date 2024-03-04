package info.vladimirov.pluralsight.mockito.module5;



import info.vladimirov.pluralsight.mockito.module5.model.AccountCurrency;

import java.math.BigDecimal;

public interface ConversionRateService {

    BigDecimal getConvertedRate(AccountCurrency currency, BigDecimal amoung);
}
