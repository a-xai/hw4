package com.company;
import java.util.Random;
public class Main {
    public static int bossHp = 1800;
    public static int bossDamage = 50;
    public static String bossDefenceType = " ";
    public static int[] heroesHp = {260, 250, 240, 280, 400, 210, 250, 200};
    public static int[] heroesDamage = {25, 20, 15, 0, 5, 10, 20, 15};
    public static String[] namesOfHeroes = {"Varvar", "Mag", "Charls", "Dr.Strange", "Golem", "Lucky", "Berserk", "Serious Thor"};
    public static String[] attackType = {"Physical", "Magic", "Kinetic", "Medical", "Defence", "Luck", "Blocking", "Stunning"};
    public static void bossChooseDefence() {
        Random r = new Random();
        int randomI = r.nextInt(attackType.length);
        bossDefenceType = attackType[randomI];
        System.out.println("Boss choose " + bossDefenceType + " type of attack.");
    }
    public static void main(String[] args) {
        while (!gameOver()) {
            round();
        }
    }
    public static void madicalTreatment() {
        for (int i = 0; i < heroesHp.length; i++) {
            if (heroesHp[i] <= 100 && (heroesHp[i] > 0 && heroesHp[i] != heroesHp[3] && heroesHp[3] > 0)) {

                Random random = new Random();
                int medicsHelp = random.nextInt(15) + 15;
                heroesHp[i] = heroesHp[i] + medicsHelp;
                System.out.println("Dr.Strange cured " + namesOfHeroes[i] + " and give him " + medicsHelp + ". " + namesOfHeroes[i] + " thanksgiving Dr.Strange!!!");

                break;
            }
        }
    }
    public static void round() {
        System.out.println("___________________");
        heroesHits();
        if (bossHp > 0) {
            bossChooseDefence();
            bossHits();
        }
        printStatistics();
        madicalTreatment();
    }
    public static void printStatistics() {
        System.out.println("___________________");
        System.out.println("Boss's health: " + bossHp);
        for (int i = 0; i < heroesHp.length; i++) {
            System.out.println(namesOfHeroes[i] + "'s health: " + heroesHp[i]);
        }
        System.out.println("___________________");
    }
    public static boolean gameOver() {
        if (bossHp <= 0) {
            System.out.println("Heroes win!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHp.length; i++) {
            if (heroesHp[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss win!!!");
        }
        return allHeroesDead;
    }//Добавить n-го игрока, Lucky, имеет шанс уклонения от ударов босса.
    public static void bossHits() {
        Random random = new Random();
        int lucky = random.nextInt(2);
        Random stunning = new Random();
        int stun = stunning.nextInt(3);
        if (heroesHp[5] > 0 && lucky == 1 && stun != 2 ) {
            if (heroesHp[4] > 0) {
                heroesHp[5] = heroesHp[5] + (bossDamage - bossDamage / 5);
                System.out.println("Lucky dodged!!!");
            } else heroesHp[5] += bossDamage;
        }
        Random rand = new Random();
        int madOfBattle = rand.nextInt(10) + 5;
        if (heroesHp[6] > 0 && stun != 2) {
            heroesHp[6] = heroesHp[6] + madOfBattle;
            bossHp -= madOfBattle;
            System.out.println("Berserk blocked and returned: " + madOfBattle);
        }
        for (int i = 0; i < heroesHp.length; i++) {
            if (heroesHp[7] > 0 && stun == 2 ){
                System.out.println("Serious Thor stunned!!!");
                break;}
            if (heroesHp[i] > 0) {
                if (heroesHp[i] - bossDamage < 0) {
                    heroesHp[i] = 0;
                } else {
                    if (heroesHp[4] > 0) {
                        heroesHp[i] = heroesHp[i] - (bossDamage - bossDamage / 5);
                    } else {
                        heroesHp[i] = heroesHp[i] - bossDamage;
                    }
                }
            }
        }
    }
    public static void heroesHits() {
        Random random = new Random();
        int coeff = random.nextInt(10) + 2;
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHp[i] > 0) {
                if (bossHp > 0) {
                    if (bossDefenceType == attackType[i]) {
                        System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                        if (bossHp - heroesDamage[i] * coeff < 0) {
                            bossHp = 0;
                        } else {
                            bossHp = bossHp - heroesDamage[i] * coeff;
                        }
                    } else {

                        if (bossHp - heroesDamage[i] < 0) {
                            bossHp = 0;
                        } else {
                            bossHp = bossHp - heroesDamage[i];
                        }
                    }
                }
            }
        }
    }
}