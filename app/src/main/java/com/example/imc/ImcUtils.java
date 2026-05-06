package com.example.imc;

public class ImcUtils {

    public static class Thresholds {
        public final double insuffMax;
        public final double normalMax;
        public final double surpoidsMax;
        public final double obesiteMax;

        public Thresholds(double insuffMax, double normalMax, double surpoidsMax, double obesiteMax) {
            this.insuffMax = insuffMax;
            this.normalMax = normalMax;
            this.surpoidsMax = surpoidsMax;
            this.obesiteMax = obesiteMax;
        }
    }

    public static Thresholds thresholdsForMan(int age) {
        if (age >= 18 && age <= 24) return new Thresholds(20, 25, 30, 40);
        if (age <= 34)             return new Thresholds(21, 26, 31, 41);
        if (age <= 44)             return new Thresholds(22, 27, 32, 42);
        if (age <= 64)             return new Thresholds(23, 28, 33, 43);
        return new Thresholds(25, 30, 35, 45);
    }

    public static Thresholds thresholdsForWoman(int age) {
        if (age >= 18 && age <= 24) return new Thresholds(19, 24, 29, 39);
        if (age <= 34)             return new Thresholds(20, 25, 30, 40);
        if (age <= 44)             return new Thresholds(21, 26, 31, 41);
        if (age <= 64)             return new Thresholds(23, 28, 32, 43);
        return new Thresholds(24, 29, 34, 44);
    }

    public static String categoryForImc(double imc, Thresholds t) {
        if (imc < t.insuffMax) return "Insuffisance pondérale";
        if (imc < t.normalMax) return "Poids normal";
        if (imc < t.surpoidsMax) return "Surpoids";
        if (imc < t.obesiteMax) return "Obésité";
        return "Obésité importante";
    }

    public static String tableResId(String gender) {
        return String.valueOf("Female".equalsIgnoreCase(gender)
                ? R.drawable.imc_tab_femme
                : R.drawable.imc_tab_homme);
    }

    public static int stickerResId(String gender, String category) {
        boolean isFemale = "Female".equalsIgnoreCase(gender);

        if (isFemale) {
            if (category.equals("Insuffisance pondérale")) return R.drawable.woman_sticker_1;
            if (category.equals("Poids normal")) return R.drawable.woman_sticker_2;
            if (category.equals("Surpoids")) return R.drawable.woman_sticker_3;
            if (category.equals("Obésité")) return R.drawable.woman_sticker_5;
            return R.drawable.woman_sticker_6;
        } else {
            if (category.equals("Insuffisance pondérale")) return R.drawable.man_sticker_1;
            if (category.equals("Poids normal")) return R.drawable.man_sticker_2;
            if (category.equals("Surpoids")) return R.drawable.man_sticker_3;
            if (category.equals("Obésité")) return R.drawable.man_sticker_5;
            return R.drawable.man_sticker_6;
        }
    }
}