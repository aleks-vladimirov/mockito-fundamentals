package info.vladimirov.pluralsight.mockito.module3;

import info.vladimirov.pluralsight.mockito.module3.api.ConversionRateService;
import info.vladimirov.pluralsight.mockito.module3.model.AccountCurrency;

public class ConversionRateServiceImpl implements ConversionRateService {
    @Override
    public Double getConvertedRate(AccountCurrency currency, Double amoung) {
        return null;
    }
}
