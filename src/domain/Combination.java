package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Combinacio Class. Cretes a HashMap with the combinations of numbers for each concurrent-white-cell lenghts
 */
public class Combination {

    /**
     * This attribute is the HashMap of combinations.
     */
    private final HashMap<Integer, Integer[]> comb;

    /**
     * Combinacio constructor generates the HashMap with the combinations
     */
    public Combination() {
        this.comb = new HashMap<>();
        comb.put(203, new Integer[]{1, 2});
        comb.put(204, new Integer[]{1, 3});
        comb.put(205, new Integer[]{1, 2, 3, 4});
        comb.put(206, new Integer[]{1, 2, 4, 5});
        comb.put(207, new Integer[]{1, 2, 3, 4, 5, 6});
        comb.put(208, new Integer[]{1, 2, 3, 5, 6, 7});
        comb.put(209, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(210, new Integer[]{1, 2, 3, 4, 6, 7, 8, 9});
        comb.put(211, new Integer[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(212, new Integer[]{3, 4, 5, 7, 8, 9});
        comb.put(213, new Integer[]{4, 5, 6, 7, 8, 9});
        comb.put(214, new Integer[]{5, 6, 8, 9});
        comb.put(215, new Integer[]{6, 7, 8, 9});
        comb.put(216, new Integer[]{7, 9});
        comb.put(217, new Integer[]{8, 9});

        comb.put(306, new Integer[]{1, 2, 3});
        comb.put(307, new Integer[]{1, 2, 4});
        comb.put(308, new Integer[]{1, 2, 3, 4, 5});
        comb.put(309, new Integer[]{1, 2, 3, 4, 5, 6});
        comb.put(310, new Integer[]{1, 2, 3, 4, 5, 6, 7});
        comb.put(311, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(312, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(313, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(314, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(315, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(316, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(317, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(318, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(319, new Integer[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(320, new Integer[]{3, 4, 5, 6, 7, 8, 9});
        comb.put(321, new Integer[]{4, 5, 6, 7, 8, 9});
        comb.put(322, new Integer[]{5, 6, 7, 8, 9});
        comb.put(323, new Integer[]{6, 8, 9});
        comb.put(324, new Integer[]{7, 8, 9});

        comb.put(410, new Integer[]{1, 2, 3, 4});
        comb.put(411, new Integer[]{1, 2, 3, 5});
        comb.put(412, new Integer[]{1, 2, 3, 4, 5, 6});
        comb.put(413, new Integer[]{1, 2, 3, 4, 5, 6, 7});
        comb.put(414, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(415, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(416, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(417, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(418, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(419, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(420, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(421, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(422, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(423, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(424, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(425, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(426, new Integer[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(427, new Integer[]{3, 4, 5, 6, 7, 8, 9});
        comb.put(428, new Integer[]{4, 5, 6, 7, 8, 9});
        comb.put(429, new Integer[]{5, 7, 8, 9});
        comb.put(430, new Integer[]{6, 7, 8, 9});

        comb.put(515, new Integer[]{1, 2, 3, 4, 5});
        comb.put(516, new Integer[]{1, 2, 3, 4, 6});
        comb.put(517, new Integer[]{1, 2, 3, 4, 5, 6, 7});
        comb.put(518, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(519, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(520, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(521, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(522, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(523, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(524, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(525, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(526, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(527, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(528, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(529, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(530, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(531, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(532, new Integer[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(533, new Integer[]{3, 4, 5, 6, 7, 8, 9});
        comb.put(534, new Integer[]{4, 6, 7, 8, 9});
        comb.put(535, new Integer[]{5, 6, 7, 8, 9});

        comb.put(621, new Integer[]{1, 2, 3, 4, 5, 6});
        comb.put(622, new Integer[]{1, 2, 3, 4, 5, 7});
        comb.put(623, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(624, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(625, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(626, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(627, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(628, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(629, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(630, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(631, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(632, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(633, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(634, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(635, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(636, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(637, new Integer[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(638, new Integer[]{3, 5, 6, 7, 8, 9});
        comb.put(639, new Integer[]{4, 5, 6, 7, 8, 9});

        comb.put(728, new Integer[]{1, 2, 3, 4, 5, 6, 7});
        comb.put(729, new Integer[]{1, 2, 3, 4, 5, 6, 8});
        comb.put(730, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(731, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(732, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(733, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(734, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(735, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(736, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(737, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(738, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(739, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(740, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(741, new Integer[]{2, 4, 5, 6, 7, 8, 9});
        comb.put(742, new Integer[]{3, 4, 5, 6, 7, 8, 9});

        comb.put(836, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(837, new Integer[]{1, 2, 3, 4, 5, 6, 7, 9});
        comb.put(838, new Integer[]{1, 2, 3, 4, 5, 6, 8, 9});
        comb.put(839, new Integer[]{1, 2, 3, 4, 5, 7, 8, 9});
        comb.put(840, new Integer[]{1, 2, 3, 4, 6, 7, 8, 9});
        comb.put(841, new Integer[]{1, 2, 3, 5, 6, 7, 8, 9});
        comb.put(842, new Integer[]{1, 2, 4, 5, 6, 7, 8, 9});
        comb.put(843, new Integer[]{1, 3, 4, 5, 6, 7, 8, 9});
        comb.put(844, new Integer[]{2, 3, 4, 5, 6, 7, 8, 9});

        comb.put(945, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    /**
     * Getter function of full HashMap
     * @return HashMap comb
     */
    public ArrayList<Integer> getCombination(int num1, int w1, int num2, int w2){
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(comb.get(w1*100+num1)));
        ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(comb.get(w2*100+num2)));

        c1.retainAll(c2);
        return c1;
    }
}
