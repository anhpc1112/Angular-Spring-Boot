package com.example.be.demo.impl;

import com.example.be.demo.IDemo;

import java.util.*;
import java.util.stream.Collectors;

public class DemoServiceImpl {




    private static IDemo getIDemo(){
        return new IDemo(){
            @Override
            public String demo() {
                return "IDEMO";
            }
        };
    }

    private static Integer getSum(Integer a1, Integer a2, List<String> list, Integer sum){
        list.add(a1.toString() + a2.toString());
        sum = Math.max(a1, a2);
        return sum;
    }

    private static Integer testSomething(List<String> list){
        list.add("test SomeThing");
        return 1;
    }

    public static void main(String[] args){
      //
//        IDemo iDemo = getIDemo();
//        System.out.println(iDemo.demo());
        Integer sum = 0;
        Integer MAX = 15;
        List<List<String>> result = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(5, "C");
        map.put(8, "D");

        List<Integer> numbers = map.keySet().stream().collect(Collectors.toList());
        for (Integer number1 : numbers){
            sum = 0;
            List<String> res = new ArrayList<>();
            // remove person1
            List<Integer> list2 = new ArrayList<>(numbers);
            list2.remove(number1);
            for (Integer number2 : list2){
                sum = sum + getSum(number1, number2, res, sum);
                
            }
        }






    }
}
