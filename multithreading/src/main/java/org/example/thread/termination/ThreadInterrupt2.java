package org.example.thread.termination;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.BitSet;

public class ThreadInterrupt2 {

    /*
    * As this calculation will take a lot of time so we can interrupt.
    * */

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("100000000000")));
        thread.start();
        thread.interrupt();
    }

    private static class LongComputationTask implements Runnable {
    private BigInteger base;
    private BigInteger power;

    public LongComputationTask(BigInteger base, BigInteger power) {
        this.base = base;
        this.power = power;
    }
        @Override
        public void run() {

            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }


    }
}
