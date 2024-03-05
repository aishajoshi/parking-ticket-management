package com.pascal.ptm.examples;

public class AmountServiceExample {
    public static void main(String[] args) {
        long totalTimeSec = 3960;
        System.out.println("Total amount= " + totalAmountCalculateExample(totalTimeSec));
    }


    public static double totalAmountCalculateExample(long totalTime) {
        System.out.println("Calculate Total amount example");
        double fiveMinuteRate=0.0;
        double moreThanFIveMinuteRate=15.0;
        double hourlyRate=25.0;

        double totalCost=0.0;

        if (totalTime <= 300) { // 5 minutes (300 seconds)
            totalCost = fiveMinuteRate;
        } else if (totalTime <= 1800) { // 30 minutes (1800 seconds)
            totalCost = moreThanFIveMinuteRate;
        } else if (totalTime <= 3600) { // 1 hour (3600 seconds)
            totalCost = hourlyRate;
        } else { // More than 1 hour
            long remainingTime = totalTime - 3600; // Subtract 1 hour //360
            totalCost = 25 + (Math.floor( (remainingTime / 3600.0) * hourlyRate));
        }
        return totalCost;
    }


//test

//    public static double totalAmountCalculateExample(long totalTime) {
//        System.out.println("Calculate Total amount example");
//
//        double totalCost;
//
//        if (totalTime <= 300) {
//            totalCost = 0;
//        } else {
//            totalCost = (double) (25 * totalTime) / (3600);
//        }
//        return totalCost;
//    }


}

