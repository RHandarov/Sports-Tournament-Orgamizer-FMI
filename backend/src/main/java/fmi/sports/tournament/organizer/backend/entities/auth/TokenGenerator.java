package fmi.sports.tournament.organizer.backend.entities.auth;

import java.util.Random;

public class TokenGenerator {
    private static final int TOKEN_LENGTH = 128;
    private static final Random generator = new Random();

    public static String generateRandomToken() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);

        Random generator = new Random();
        for (int i = 0; i < TOKEN_LENGTH; ++i) {
            token.append(generateNextSymbol());
        }

        return token.toString();
    }

    private static char generateNextSymbol() {
        boolean isDigit = generator.nextBoolean();
        if (isDigit) {
            return generateRandomDigit();
        }

        return generateRandomSmallLetter();
    }

    private static char generateRandomDigit() {
        return (char)(generator.nextInt(9) + '0');
    }

    private static char generateRandomSmallLetter() {
        return (char)(generator.nextInt(26) + 'a');
    }
}
