package com.game.password_strength_calculator;

import android.graphics.Color;

public enum PasswordStrength {

    WEEK(R.string.week, Color.parseColor("#61ad85")),
    MEDIUM(R.string.medium, Color.parseColor("#4d8a6a")),
    STRONG(R.string.strong, Color.parseColor("#3a674f")),
    VERY_STRONG(R.string.very_strong, Color.parseColor("#264535"));

    private static int MIN_LENGTH = 8;
    private static int MAX_LENGTH = 15;
    public int msg;
    public int color;

    PasswordStrength(int msg, int color) {
        this.color = color;
        this.msg = msg;
    }

    public static PasswordStrength calculate(String password) {
        int score = 0;
        boolean upper = false; //булевый индикатор больших букв
        boolean lower = false; //булевый индикатор малых букв
        boolean digit = false; //проверка на цифры
        boolean specialChar = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (specialChar) {
                Character.isLetterOrDigit(c);
            }
            if (!digit && Character.isDigit(c)) {
                score++;
                digit = true;
            } else {
                if (!upper || !lower) {
                    if (Character.isUpperCase(c)) {
                        upper = true;
                    } else {
                        lower = true;
                    }

                    if (upper && lower) {
                        score++;
                    }
                }

            }
        }

        int length = password.length();

        if (length > MAX_LENGTH) {
            score++;
        } else if (length < MIN_LENGTH) {
            score = 0;
        }

        switch (score){
            case 0: return WEEK;
            case 1: return MEDIUM;
            case 2: return STRONG;
            case 3: return VERY_STRONG;
            default:
        }

        return VERY_STRONG;
    }
}
