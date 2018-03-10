/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk8tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author ssiddiqu
 */
public class MethodReferenceDemo
{
    public static void main(String[] args)
    {
        List numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16);
        /*
         findPrimeNumbers(List list, Predicate predicate)
         Predicate is a functional interface with method: boolean test(T t);
         (number) -> MethodReferenceDemo.isPrime((int) number) is implementation of test(T t). 
        */
        List primeNumbers = MethodReferenceDemo.findPrimeNumbers(numbers,
                (number) -> MethodReferenceDemo.isPrime((int) number));

        System.out.println("Prime Numbers are " + primeNumbers);
    }

    public static boolean isPrime(int number)
    {
        if (number == 1)
        {
            return false;
        }
        for (int i = 2; i < number; i++)
        {
            if (number % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public static List findPrimeNumbers(List list, Predicate predicate)
    {
        List sortedNumbers = new ArrayList();
        list.stream().filter((i) -> (predicate.test(i))).forEach((i) ->
        {
            sortedNumbers.add(i);
        });
        return sortedNumbers;

    }  
}
