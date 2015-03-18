package com.translator;

import java.math.BigInteger;

/**
 * @author aamir.talib
 * 
 */
public class NumberTranslator {

  private static final String[] SMALL = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
  private static final String[] TENS  = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
  private static final String[] LARGE = { "thousand", "million", "billion", "trillion", "quadrillion", "quintillion", "sextillion", "septillion", "octillion", "nonillion", "decillion" };

  public static String translate(String number) {
    if("0".equals(number)){
      return SMALL[0];
    }
    BigInteger input = null;

    try{
      input = new BigInteger(number);
    }catch(NumberFormatException e){
      return "Not a valid number";
    }

    String result = (input.signum() < 0 ? "minus " : "") + translateBigNumber(input.abs());
    return result.trim();
  }

  private static String translateBigNumber(BigInteger number){
    String words = null;
    if(number.compareTo(BigInteger.valueOf(999)) > 0){
      for(int bigIdx=0; number.signum() > 0; bigIdx++){
        if (number.remainder(BigInteger.valueOf(1000)).signum() > 0) {
          try{
            words = translateHundreds(number.remainder(BigInteger.valueOf(1000)).intValue()) + (bigIdx > 0 ? " " + LARGE[bigIdx-1] : "") + (words!=null? ", " + words : "");
          }catch(IndexOutOfBoundsException e){
            return "Too long to translate";
          }
        }
        number = number.divide(BigInteger.valueOf(1000));
      }
    }else{
      words = translateHundreds(number.intValue());  
    }
    return words;
  }

  // Range 1-999
  private static String translateHundreds(int number){
    return (number > 99 ? translateHundreds(number/100) + " hundred " : "") + translateTens(number % 100);
  }

  // Range 1-99
  private static String translateTens(int number){
    return number > 19 ? TENS[number / 10 - 2] + (number % 10 != 0 ? "-" + translateSmall(number % 10) : "") : translateSmall(number);
  }

  private static String translateSmall(int number){
    return number > 0  ? SMALL[number] : "";
  }
}