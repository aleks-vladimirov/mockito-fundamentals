package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;

import java.math.BigDecimal;

public class ConversionRateServiceImpl implements ConversionRateService{
    @Override
    public BigDecimal getConvertedRate(AccountCurrency currency, BigDecimal amoung) {
        return null;
    }
}
