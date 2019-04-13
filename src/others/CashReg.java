package others;

public class CashReg
{
   private static final double QUARTER_VALUE = 25;
   private static final double DIME_VALUE = 10;
   private static final double NICKEL_VALUE = 5;
   private static final double PENNY_VALUE = 1;
   private static final int DOLLAR_VALUE = 100;

   private double purchase;
   private double payment;

   public CashReg()
   {
      purchase = 0;
      payment = 0;
   }

   public void recordPurchase(double amount)
   {
      purchase = purchase + amount * 100;
   }
   
    public double getTotal() {
       return purchase / 100;
    }; 

   public void receivePayment(Change money)
   {
      payment = money.totalValue();
   }
   
   public Change giveChange()
   {
      int change = (int)(payment - purchase);
	  int dollars = change / DOLLAR_VALUE;
      int quarters = (change %  DOLLAR_VALUE) / (int) QUARTER_VALUE;
      int dimes = (change - dollars * (int) DOLLAR_VALUE -quarters * (int) QUARTER_VALUE) / (int) DIME_VALUE;
      int nickels = (change - dollars * (int) DOLLAR_VALUE -quarters * (int) QUARTER_VALUE - dimes * (int) DIME_VALUE) / (int) NICKEL_VALUE;
      int pennies = change - dollars * (int) DOLLAR_VALUE -quarters * (int) QUARTER_VALUE - dimes * (int) DIME_VALUE - nickels * (int) NICKEL_VALUE;
      purchase = 0;
      payment = 0;
      return new Change(dollars, quarters, dimes, nickels, pennies);
   }
}