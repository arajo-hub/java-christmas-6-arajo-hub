package christmas.event.badge;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 배지
 */
public enum Badge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 30_000);

    private String name;
    private int threshold;

    Badge(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public int getThreshold() {
        return threshold;
    }

    public static Badge getBadge(int totalBenefit) {
        List<Badge> sortedBadges = Arrays.stream(Badge.values()).sorted(Comparator.comparingInt(Badge::getThreshold).reversed()).collect(Collectors.toList());
        for (Badge badge : sortedBadges) {
            if (totalBenefit >= badge.getThreshold()) {
                return badge;
            }
        }
        return null;
    }
}
