package currencyConverter;

public class CurrencyConverter
{
      

   // Converts currency1 to usd then usd to currency2
	public double convert(Currencies currency1, Currencies currency2, String amount)
	{
		double number = Double.parseDouble(amount);
		
		number = number * 1 / currency1.USDTOCURRENCYMULTIPLIER;//usd 113.04, currency1 accessing mutiplier, number 50  
		
		number = number * currency2.USDTOCURRENCYMULTIPLIER;
		
		number = Math.round(number*100)/100;
		
			
		return number;
		
		
		
				
	}  
}   



