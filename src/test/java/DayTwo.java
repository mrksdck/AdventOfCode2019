import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DayTwo {
    /**
     * Here are the initial and final states of a few more small programs:
     * <p>
     * 1,0,0,0,99 becomes 2,0,0,0,99 (1 + 1 = 2).
     * 2,3,0,3,99 becomes 2,3,0,6,99 (3 * 2 = 6).
     * 2,4,4,5,99,0 becomes 2,4,4,5,99,9801 (99 * 99 = 9801).
     * 1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99.
     * <p>
     * Opcode 1 addition 4 in length
     * Opcode 2 multiplies 4 n length
     */

    @Test
    void testPuzzleOne() {
        assertThat(opcode(new int[]{1, 0, 0, 0, 99}, 0)).isEqualTo(new int[]{2, 0, 0, 0, 99});
        assertThat(opcode(new int[]{2, 3, 0, 3, 99}, 0)).isEqualTo(new int[]{2, 3, 0, 6, 99});
        assertThat(opcode(new int[]{2, 4, 4, 5, 99, 0}, 0)).isEqualTo(new int[]{2, 4, 4, 5, 99, 9801});
        assertThat(opcode(new int[]{1, 1, 1, 4, 99, 5, 6, 0, 99}, 0)).isEqualTo(new int[]{30, 1, 1, 4, 2, 5, 6, 0, 99});
        assertThat(opcode(new int[]{1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50}, 0)).isEqualTo(new int[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50});
    }

    int[] opcode(int[] opcode, int position) {

        if (opcode.length > position)
            switch (opcode[position]) {
                case 1:
                    opcode[opcode[position + 3]] = opcode[opcode[position + 1]] + opcode[opcode[position + 2]];
                    return opcode(opcode, position + 4);
                case 2:
                    opcode[opcode[position + 3]] = opcode[opcode[position + 1]] * opcode[opcode[position + 2]];
                    return opcode(opcode, position + 4);
                case 99:
                    return opcode;
                default:
                    return opcode(opcode, position + 4);
            }
        return opcode;
    }

    @Test
    void puzzleOne() {
        int[] input = new int[]{1, 12, 2, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 6, 1, 19, 2, 19, 13, 23, 1, 23, 10, 27, 1, 13, 27, 31, 2, 31, 10, 35, 1, 35, 9, 39, 1, 39, 13, 43, 1, 13, 43, 47, 1, 47, 13, 51, 1, 13, 51, 55, 1, 5, 55, 59, 2, 10, 59, 63, 1, 9, 63, 67, 1, 6, 67, 71, 2, 71, 13, 75, 2, 75, 13, 79, 1, 79, 9, 83, 2, 83, 10, 87, 1, 9, 87, 91, 1, 6, 91, 95, 1, 95, 10, 99, 1, 99, 13, 103, 1, 13, 103, 107, 2, 13, 107, 111, 1, 111, 9, 115, 2, 115, 10, 119, 1, 119, 5, 123, 1, 123, 2, 127, 1, 127, 5, 0, 99, 2, 14, 0, 0};
        System.out.println(opcode(input, 0)[0]);
    }

    @Test
    void puzzleTwoTest() {
        int haltingNumber = 19690720;
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                if (findNounAndVerb(noun, verb)[0] == haltingNumber) {
                    System.out.println(String.format("(noun,verb) =  (%d,%d)", noun, verb));
                    System.out.println(String.format("100 * %d + %d = %d", noun, verb, 100 * noun + verb));
                }

            }
        }

    }

    int[] findNounAndVerb(int noun, int verb) {
        int[] input = new int[]{1, noun, verb, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 6, 1, 19, 2, 19, 13, 23, 1, 23, 10, 27, 1, 13, 27, 31, 2, 31, 10, 35, 1, 35, 9, 39, 1, 39, 13, 43, 1, 13, 43, 47, 1, 47, 13, 51, 1, 13, 51, 55, 1, 5, 55, 59, 2, 10, 59, 63, 1, 9, 63, 67, 1, 6, 67, 71, 2, 71, 13, 75, 2, 75, 13, 79, 1, 79, 9, 83, 2, 83, 10, 87, 1, 9, 87, 91, 1, 6, 91, 95, 1, 95, 10, 99, 1, 99, 13, 103, 1, 13, 103, 107, 2, 13, 107, 111, 1, 111, 9, 115, 2, 115, 10, 119, 1, 119, 5, 123, 1, 123, 2, 127, 1, 127, 5, 0, 99, 2, 14, 0, 0};
        return opcode(input, 0);
    }
}