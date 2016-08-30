package com.intershop.adapter.payment.partnerpay.internal.luckynumbers;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.intershop.adapter.payment.partnerpay.capi.luckynumbers.LuckyNumberGenerator;

public class LuckyNumberGeneratorImpl implements LuckyNumberGenerator
{
    private static final int NUMBER = 3;
    
    @Override
    public List<Integer> getLuckyNumbers()
    {
        Random rand = new Random();
        
        return IntStream.generate(() -> rand.nextInt(100) + 1).limit(NUMBER).boxed().collect(Collectors.toList());
    }
}
