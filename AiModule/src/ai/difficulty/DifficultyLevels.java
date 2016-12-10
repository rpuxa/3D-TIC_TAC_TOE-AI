package ai.difficulty;

import ai.DifficultyLevel;
import util.ArrayUtils;

public interface DifficultyLevels {

    DifficultyLevel VERY_EASY = new DifficultyLevelImpl(
        "Очень легкий",
        "Сложность для начинающих, которая поможет вам понять основные принципы игры.",
        "Использует только очень слабый движок."
    );

    DifficultyLevel EASY = new DifficultyLevelImpl(
            "Легкий",
            "Если вы поняли основные принципы, попробуйте этот тип сложности.",
            "Поначалу будет сложно, но после слишком легко.",
            "Использует только основные возможности движка."
    );

    DifficultyLevel AVERAGE = new DifficultyLevelImpl(
            "Средний",
            "Выработав свою тактику, попробуйте эту сложность.",
            "Использует весь движок."
    );

    DifficultyLevel HARD = new DifficultyLevelImpl(
            "Сложный",
            ArrayUtils.toArray(
                    "Приготовьтесь к тяжкому испытанию!",
                    "Для победы нужно будет не слабо потренироваться!"
            ),
            "Начинает использовать анализ позиций,",
            "полностью использует движок и наполовину древо ходов.",
            "Анализирует примерно 5000 позиций за ход."
    );

    DifficultyLevel MAXIMAL = new DifficultyLevelImpl(
            "Максимальный",
            ArrayUtils.toArray(
                    "Чтобы одолеть максимальную сложность, ",
                    "нужно иметь стратегическое мышление, огромную внимательность",
                    "и быть очень сосредоточенным!"
            ),
            "Использует все возможности по максимуму:",
            "древо ходов, анализ, движок.",
            "Анализирует примерно 60000 позиций за ход!"
    );

    DifficultyLevel[] LEVELS = {
        VERY_EASY, EASY, AVERAGE, HARD, MAXIMAL
    };
}
