package com.intershop.adapter.payment.partnerpay.capi.luckynumbers;

import java.util.List;

/**
 * A generator for lucky numbers. The purpose of a lucky number is to bring lots of joy and happiness once chosen.
 */
public interface LuckyNumberGenerator
{
    /**
     * Generates a list with lucky numbers.
     * 
     * @return a list with lucky numbers.
     */
    public List<Integer> getLuckyNumbers();
}
