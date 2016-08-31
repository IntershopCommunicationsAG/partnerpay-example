package com.intershop.adapter.payment.partnerpay.internal.luckynumbers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.intershop.adapter.payment.partnerpay.capi.luckynumbers.LuckyNumberGenerator;

public class LuckyNumberGeneratorImpl implements LuckyNumberGenerator
{
    private static final int NUMBER = 3;
    
    @Override
    public List<Integer> getLuckyNumbers()
    {
        return new Random().
                        ints(1, 101).
                        distinct().
                        limit(NUMBER).
                        boxed().
                        collect(Collectors.toList());
    }
}
