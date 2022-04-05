/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg35.java.lab05;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

/**
 *
 * @author Phoen1x
 */
public class DemoMultiThread {
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException {
        System.out.println("В этой практической работе был использован метод проектирования Singletone");
        //Реализация шаблона Singletone.
        Thread threadRules = new Thread(new ThreadRules());
        Thread threadAdmin = new Thread(new ThreadAdmin());
        //Реальзация макрокоманд.
        threadRules.start();
        threadAdmin.start();
        String s1="java";
        String s2="ja"+"va";
        System.out.println(s1==s2);
        final String str1="ja";
        final String str2="va";
        String str3="ja";
        String str4="va";
        String s3=str1+str2;
        String s6=str3+str4;
        System.out.println(s1==s3);
        System.out.println(s1==s6);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        String MassTela;//создать переменную для массы тела
        String SkorostTela;//создать переменную для скорости тела
        Thread.sleep(2000);
        System.out.println("Введите массу тела:");
        MassTela = in.readLine();//присвоим считаное значение пременной
        System.out.println("Введите скорость тела:");
        SkorostTela = in.readLine();//присвоим считаное значение пременной
        //Цикл для управления програмой
        do {
            do {
                System.out.println("\nПожалуйста выберете дейсвие:"
                        + "\nЧтобы выйти из програмы нажмите = 1"
                        + "\nПосмотреть предыдущие результаты нажмите = 2"
                        + "\nВыполнить вычисление из новыми переменными та сохранить их = 3"
                        + "\nПротестировать корректность результатов сериализации/десериализации = 4");
                try {
                    s = in.readLine();////присвоим считаное значение пременной
                } catch (IOException e) {
                    System.out.println("Ошибка: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            switch (s.charAt(0)) {
                case '1':
                    System.out.println("Выход.");
                    break;
                case '2'://используем класс Out для выводу предыдущих  значений 
                    Out.main(MassTela, SkorostTela);
                    break;
                case '3'://используя сериализацию сохраняем данные
                    SerializableClass date = new SerializableClass(MassTela, SkorostTela);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("person.out"));
                    objectOutputStream.writeObject(date);
                    objectOutputStream.close();
                    Calculation.KineticEnergy(MassTela, SkorostTela);
                    System.out.println("Всё усешно сохранено!");
                    break;
                case '4'://используя сериализацию проверяем коректность работы
                    Test.proverka(MassTela, SkorostTela);
                    System.out.println("Проверка прошла успешно!");
                    break;
                default:
                    System.out.print("Неизвестная команда.\n\n ");
            }
        } while (s.charAt(0) != '1');
    }

    
    static class ThreadRules implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("Програма реализует вычисление кинетической энергии. В предложеные поля введите условия задания!");
            System.out.println(singleton.value);
        }
    }

    static class ThreadAdmin implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("Добро пожаловать!");
            System.out.println(singleton.value);
        }
    }
}