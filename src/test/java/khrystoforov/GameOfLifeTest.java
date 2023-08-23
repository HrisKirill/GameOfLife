package khrystoforov;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameOfLifeTest {

    GameOfLife game = new GameOfLife();

    public boolean equalsFile(String expected, String result) {
        Stream<String> gameStreamInput = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(expected))).lines();
        List<String> gameListExpected = gameStreamInput.collect(Collectors.toList());
        Stream<String> gameStreamResult = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(result))).lines();
        List<String> gameListResult = gameStreamResult.collect(Collectors.toList());
        return gameListExpected.equals(gameListResult);
    }

    private static Stream<Arguments> provideFilesNames() {
        return Stream.of(
                Arguments.of("input1.txt","expectedInput1.txt", "output1.txt"),
                Arguments.of("inputGlider.txt","expectedGlider.txt", "outputGlider.txt"),
                Arguments.of("inputOscillator.txt","expectedOscillator.txt", "outputOscillator.txt")
        );
    }

    @ParameterizedTest
    @MethodSource("provideFilesNames")
    void testEquals(String input,String expected, String output) {
        game.game(input,output);
        assertTrue(equalsFile(expected, output));
    }

}