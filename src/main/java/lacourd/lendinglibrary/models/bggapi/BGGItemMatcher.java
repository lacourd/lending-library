package lacourd.lendinglibrary.models.bggapi;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.List;

public class BGGItemMatcher {

    private static final LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    public static BGGItem findBestMatch(List<BGGItem> items, String targetTitle) {
        BGGItem bestMatch = null;
        int bestScore = Integer.MAX_VALUE; // Initialize with a high value

        for (BGGItem item : items) {
            String currentItemTitle = item.getNames().get(0).getValue();
            int currentScore = levenshteinDistance.apply(currentItemTitle, targetTitle);

            if (currentScore < bestScore) {
                bestScore = currentScore;
                bestMatch = item;
            }
        }

        return bestMatch;
    }
}

