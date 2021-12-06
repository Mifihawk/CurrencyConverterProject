package currencyConverter;

public enum Currencies
{
	//enums
	//creating enums and storing name currency sign and value
	USD("US Dollar","$",1.0),
	YEN("Yen","¥",112.97),
	RENMINBI("Renminbi","元",6.37),
	EURO("Euro","€",0.88),
	KRONE("Krone","kr",9.10),
	POUND("Pound","£",0.75),
	RAND("Rand","R",16.05),
	ROUBLE("Rouble","₽",74.17);
	
	

	//fields

	public final String NAME;
	public final String CURRENCYUNIT;
	public final double USDTOCURRENCYMULTIPLIER;

	//constructors 
	Currencies(String name, String currencyUnit, double USDToCurrencyMutiplier)
	{
		this.NAME = name;
		this.CURRENCYUNIT = currencyUnit;
		this.USDTOCURRENCYMULTIPLIER = USDToCurrencyMutiplier;     
	}

}
