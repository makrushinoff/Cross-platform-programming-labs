package org.example.lab1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EndianConverterTest {

    private final EndianConverter endianConverter = new EndianConverter();

    private static final List<Integer> LOW_ENDIAN = List.of(
            1375993856,
            1983643648,
            27940198,
            618393615,
            829122304,
            2140575744,
            2136246528,
            2094596352,
            789905408,
            1796734976
    );

    private static final List<Integer> BIG_ENDIAN = List.of(
            1106,
            15478,
            1716890113,
            267442980,
            6777649,
            9999999,
            9000063,
            121212,
            5423,
            6251
    );

    private static List<Arguments> bigEndianToLowEndianSource() {
        List<Arguments> arguments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arguments.add(Arguments.of(BIG_ENDIAN.get(i), LOW_ENDIAN.get(i)));
        }
        return arguments;
    }

    private static List<Arguments> lowEndianToBigEndianSource() {
        List<Arguments> arguments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arguments.add(Arguments.of(LOW_ENDIAN.get(i), BIG_ENDIAN.get(i)));
        }
        return arguments;
    }

    @ParameterizedTest
    @MethodSource("lowEndianToBigEndianSource")
    void shouldConvertLowEndianToBigEndianCustomImplementation(Integer lowEndian, Integer bigEndian) {
        int actual = endianConverter.lowToBigEndianMy(lowEndian);
        assertEquals(bigEndian, actual);
    }

    @ParameterizedTest
    @MethodSource("bigEndianToLowEndianSource")
    void shouldConvertBigEndianToLowEndianCustomImplementation(Integer bigEndian, Integer lowEndian) {
        int actual = endianConverter.bigToLowEndianMy(bigEndian);
        assertEquals(lowEndian, actual);
    }

    @ParameterizedTest
    @MethodSource("lowEndianToBigEndianSource")
    void shouldConvertLowEndianToBigEndianLibrary(Integer lowEndian, Integer bigEndian) {
        int actual = endianConverter.swapEndian(lowEndian);
        assertEquals(bigEndian, actual);
    }

    @ParameterizedTest
    @MethodSource("bigEndianToLowEndianSource")
    void shouldConvertBigEndianToLowEndianLibrary(Integer bigEndian, Integer lowEndian) {
        int actual = endianConverter.swapEndian(bigEndian);
        assertEquals(lowEndian, actual);
    }
}