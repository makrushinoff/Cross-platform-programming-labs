package org.example.lab1;

public class EndianConverter {

    public int lowToBigEndianMy(int number) {
        return number << 24 |
                number >> 8 & 0xff00 |
                number << 8 & 0xff0000 |
                number >>> 24;
    }

    public int bigToLowEndianMy(int number) {
        return number << 8 & 0xff0000 |
                number >>> 24 |
                number << 24 |
                number >> 8 & 0xff00;
    }

    public int swapEndian(int number) {
        return Integer.reverseBytes(number);
    }

}