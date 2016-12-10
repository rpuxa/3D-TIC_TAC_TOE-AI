package ai.difficulty;

import ai.DifficultyLevel;
import util.ArrayUtils;
import util.StringUtils;

class DifficultyLevelImpl implements DifficultyLevel {

    private final String name;
    private final String[] descriptionStrings;
    private final String[] usedTechiqueStrings;

    DifficultyLevelImpl(String name, String description, String... usedTechiqueStrings) {
        this(name, ArrayUtils.toArray(description), usedTechiqueStrings);
    }

    DifficultyLevelImpl(String name, String[] descriptionStrings, String... usedTechiqueStrings) {
        this.name = name;
        this.descriptionStrings = descriptionStrings;
        this.usedTechiqueStrings = usedTechiqueStrings;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return StringUtils.toString(descriptionStrings);
    }

    @Override
    public String getUsedTechniqueDescription() {
        return StringUtils.toString(usedTechiqueStrings);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        StringUtils.appendLine(stringBuilder, getName() + ":");

        for (String descriptionString : descriptionStrings) {
            StringUtils.appendLine(stringBuilder, StringUtils.tabulated(descriptionString));
        }

        for (String usedTechniqueString : usedTechiqueStrings) {
            StringUtils.appendLine(stringBuilder, StringUtils.tabulated(usedTechniqueString));
        }

        return stringBuilder.toString();
    }
}
