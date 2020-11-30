package domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Combinacio Class. Cretes a HashMap with the combinations of numbers for each concurrent-white-cell lenghts
 */
public class Combination {

    /**
     * This attribute is the HashMap of combinations.
     */
    private final HashMap<Integer, int[]> comb;

    /**
     * Combinacio constructor generates the HashMap with the combinations
     */
    public Combination() {
        this.comb = new HashMap<>();
        comb.put(203, new int[]{1, 2});
        comb.put(204, new int[]{1, 3});
        comb.put(205, new int[]{1, 2, 3, 4});
        comb.put(206, new int[]{1, 2, 4, 5});
        comb.put(207, new int[]{1, 2, 3, 4, 5, 6});
        comb.put(208, new int[]{1, 2, 3, 5, 6, 7});
        comb.put(209, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(210, new int[]{1, 2, 3, 4, 6, 7, 8, 9});
        comb.put(211, new int[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(212, new int[]{3, 4, 5, 7, 8, 9});
        comb.put(213, new int[]{4, 5, 6, 7, 8, 9});
        comb.put(214, new int[]{5, 6, 8, 9});
        comb.put(215, new int[]{6, 7, 8, 9});
        comb.put(216, new int[]{7, 9});
        comb.put(217, new int[]{8, 9});

        comb.put(306, new int[]{1, 2, 3});
        comb.put(307, new int[]{1, 2, 4});
        comb.put(308, new int[]{1, 2, 3, 4, 5});
        comb.put(309, new int[]{1, 2, 3, 4, 5, 6});
        comb.put(310, new int[]{1, 2, 3, 4, 5, 6, 7});
        comb.put(311, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(312, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(313, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(314, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(315, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(316, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(317, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(318, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(319, new int[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(320, new int[]{3, 4, 5, 6, 7, 8, 9});
        comb.put(321, new int[]{4, 5, 6, 7, 8, 9});
        comb.put(322, new int[]{5, 6, 7, 8, 9});
        comb.put(323, new int[]{6, 8, 9});
        comb.put(324, new int[]{7, 8, 9});

        comb.put(410, new int[]{1, 2, 3, 4});
        comb.put(411, new int[]{1, 2, 3, 5});
        comb.put(412, new int[]{1, 2, 3, 4, 5, 6});
        comb.put(413, new int[]{1, 2, 3, 4, 5, 6, 7});
        comb.put(414, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(415, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(416, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(417, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(418, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(419, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(420, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(421, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(422, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(423, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(424, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(425, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(426, new int[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(427, new int[]{3, 4, 5, 6, 7, 8, 9});
        comb.put(428, new int[]{4, 5, 6, 7, 8, 9});
        comb.put(429, new int[]{5, 7, 8, 9});
        comb.put(430, new int[]{6, 7, 8, 9});

        comb.put(515, new int[]{1, 2, 3, 4, 5});
        comb.put(516, new int[]{1, 2, 3, 4, 6});
        comb.put(517, new int[]{1, 2, 3, 4, 5, 6, 7});
        comb.put(518, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(519, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(520, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(521, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(522, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(523, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(524, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(525, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(526, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(527, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(528, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(529, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(530, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(531, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(532, new int[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(533, new int[]{3, 4, 5, 6, 7, 8, 9});
        comb.put(534, new int[]{4, 6, 7, 8, 9});
        comb.put(535, new int[]{5, 6, 7, 8, 9});

        comb.put(621, new int[]{1, 2, 3, 4, 5, 6});
        comb.put(622, new int[]{1, 2, 3, 4, 5, 7});
        comb.put(623, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(624, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(625, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(626, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(627, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(628, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(629, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(630, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(631, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(632, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(633, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(634, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(635, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(636, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(637, new int[]{2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(638, new int[]{3, 5, 6, 7, 8, 9});
        comb.put(639, new int[]{4, 5, 6, 7, 8, 9});

        comb.put(728, new int[]{1, 2, 3, 4, 5, 6, 7});
        comb.put(729, new int[]{1, 2, 3, 4, 5, 6, 8});
        comb.put(730, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(731, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(732, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(733, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(734, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(735, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(736, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(737, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(738, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(739, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(740, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        comb.put(741, new int[]{2, 4, 5, 6, 7, 8, 9});
        comb.put(742, new int[]{3, 4, 5, 6, 7, 8, 9});

        comb.put(836, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        comb.put(837, new int[]{1, 2, 3, 4, 5, 6, 7, 9});
        comb.put(838, new int[]{1, 2, 3, 4, 5, 6, 8, 9});
        comb.put(839, new int[]{1, 2, 3, 4, 5, 7, 8, 9});
        comb.put(840, new int[]{1, 2, 3, 4, 6, 7, 8, 9});
        comb.put(841, new int[]{1, 2, 3, 5, 6, 7, 8, 9});
        comb.put(842, new int[]{1, 2, 4, 5, 6, 7, 8, 9});
        comb.put(843, new int[]{1, 3, 4, 5, 6, 7, 8, 9});
        comb.put(844, new int[]{2, 3, 4, 5, 6, 7, 8, 9});

        comb.put(945, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    /**
     * Getter function of full HashMap
     * @return HashMap comb
     */
    public HashMap<Integer, int [] > getCombination(){
        return comb;
    }
}
