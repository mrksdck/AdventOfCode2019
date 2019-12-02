import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DayOne {
    /**
     * or a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
     * For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
     * For a mass of 1969, the fuel required is 654.
     * For a mass of 100756, the fuel required is 33583.
     */
    @Test
    void testPuzzleOne() {
        assertThat(calculateMassOfModule(12)).isEqualTo(2);
        assertThat(calculateMassOfModule(14)).isEqualTo(2);
        assertThat(calculateMassOfModule(1969)).isEqualTo(654);
        assertThat(calculateMassOfModule(100756)).isEqualTo(33583);
    }

    @Test
    void testAnswerPuzzleOne() throws IOException, URISyntaxException {

        List<Integer> integers = testInput();
        int total = integers.stream()
                .map(this::calculateMassOfModule).mapToInt(i -> i).sum();
        System.out.println(total);
    }

    List<Integer> testInput() throws URISyntaxException, IOException {
        URL inputDayOne = this.getClass().getClassLoader().getResource("inputDayOne");
        return Files.lines(Paths.get(inputDayOne.toURI()))
                .map(Integer::valueOf).collect(Collectors.toList());
    }

    int calculateMassOfModule(int moduleMass) {
        return Double.valueOf(Math.floor(moduleMass / 3)).intValue() - 2;
    }

    /**
     * A module of mass 14 requires 2 fuel. This fuel requires no further fuel (2 divided by 3 and rounded down is 0, which would call for a negative fuel), so the total fuel required is still just 2.
     * At first, a module of mass 1969 requires 654 fuel. Then, this fuel requires 216 more fuel (654 / 3 - 2). 216 then requires 70 more fuel, which requires 21 fuel, which requires 5 fuel, which requires no further fuel. So, the total fuel required for a module of mass 1969 is 654 + 216 + 70 + 21 + 5 = 966.
     * The fuel required by a module of mass 100756 and its fuel is: 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346.
     */

    @Test
    void puzzleTwoTest() {
        assertThat(calculateMassOfModuleWithFuel(14)).isEqualTo(2);
        assertThat(calculateMassOfModuleWithFuel(1969)).isEqualTo(966);
        assertThat(calculateMassOfModuleWithFuel(100756)).isEqualTo(50346);

    }

    @Test
    void testAnswerPuzzleTwo() throws IOException, URISyntaxException {

        List<Integer> integers = testInput();
        int total = integers.stream()
                .map(this::calculateMassOfModuleWithFuel).mapToInt(i -> i).sum();
        System.out.println(total);
    }

    private int calculateMassOfModuleWithFuel(int i) {
        if(i <= 0){
            return 0;
        }
        return calculateMassOfModule(i) + Math.max(0,calculateMassOfModuleWithFuel(calculateMassOfModule(i)));
    }
}
