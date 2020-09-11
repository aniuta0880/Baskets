package com.company;
import java.io.*;
import java.util.*;

public class s21993_p02 {

    public static void main(String[] args) {
        Owoc[][][] table_of_results = new Owoc[3][400][20];
        Owoc[] koszyk = new Owoc[20];
        for (int i = 0; i < koszyk.length; i++) {
            Random rand = new Random();
            Owoc owoc = new Owoc(rand.nextInt(((10 - 1) + 1) + 1), rand.nextInt(((10 - 1) + 1) + 1)); //losuję parametry owoców
            koszyk[i] = owoc;
        }
        System.out.println("====Nieposortowany Koszyk====");
        for (int i = 0; i < koszyk.length; i++) {
            System.out.print("Kaliber to:" + koszyk[i].kaliber);
            System.out.println(" Waga to:" + koszyk[i].waga);
        }
        table_of_results = mySort(koszyk);
    }

    public static Owoc[][][] mySort(Owoc[] koszyk) {
        Owoc[][][] final_tab = new Owoc[3][400][20]; // zarezerwowałam taki zakres pamięci, ponieważ pesymistyczna złożoność obliczneniowa wykorzystanych algorytmów to O(n^2)[20^2=400], może być maksymalnie tyle stanów pośrednich
        bubble_sort(koszyk, final_tab);
        selection_sort(koszyk, final_tab);
        insertion_sort(koszyk, final_tab);
        return final_tab;
    }

    public static void selection_sort(Owoc[] unsorted_koszyk, Owoc[][][] myfinalTab) {
        int index_stanow = 0;
        Owoc[] koszyk = new Owoc[20];
        for (int i = 0; i < koszyk.length; i++) {
            Owoc owoc = new Owoc(unsorted_koszyk[i].kaliber, unsorted_koszyk[i].waga);
            koszyk[i] = owoc;
        }

        for (int i = 0; i < koszyk.length - 1; i++) {

            int index = i;
            for (int j = i + 1; j < koszyk.length; j++) {
                if (koszyk[j].kaliber < koszyk[index].kaliber) {
                    index = j;
                } else if (koszyk[j].kaliber == koszyk[index].kaliber && koszyk[j].waga < koszyk[index].waga) {
                    index = j;
                }
            }
            Owoc min = koszyk[index];
            koszyk[index] = koszyk[i];
            koszyk[i] = min;
            myfinalTab[1][index_stanow] = copy_current_state_of_table(koszyk);
            index_stanow++;
        }
        for (int i = 0; i < koszyk.length; i++) {
            koszyk[i].Wydrukuj();
        }
    }

    public static void bubble_sort(Owoc[] unsorted_koszyk, Owoc[][][] myfinalTab) {
        int index_stanow = 0;
        Owoc[] koszyk = new Owoc[20];
        for (int i = 0; i < koszyk.length; i++) {
            Owoc owoc = new Owoc(unsorted_koszyk[i].kaliber, unsorted_koszyk[i].waga);
            koszyk[i] = owoc;
        }
        for (int i = 0; i < koszyk.length - 1; i++) {
            for (int j = 0; j < koszyk.length - i - 1; j++) {
                if (koszyk[j].kaliber > koszyk[j + 1].kaliber) {
                    Owoc temp = koszyk[j];
                    koszyk[j] = koszyk[j + 1];
                    koszyk[j + 1] = temp;
                    myfinalTab[0][index_stanow] = copy_current_state_of_table(koszyk);
                    index_stanow++;
                } else if (koszyk[j].kaliber == koszyk[j + 1].kaliber && koszyk[j].waga > koszyk[j + 1].waga) {
                    Owoc temp = koszyk[j];
                    koszyk[j] = koszyk[j + 1];
                    koszyk[j + 1] = temp;
                    myfinalTab[0][index_stanow] = copy_current_state_of_table(koszyk);
                    index_stanow++;
                }
            }
        }
        for (int i = 0; i < koszyk.length; i++) {
            koszyk[i].Wydrukuj();
        }
    }

    public static void insertion_sort(Owoc[] unsorted_koszyk, Owoc[][][] myfinalTab) {
        int index_stanow = 0;
        Owoc[] koszyk = new Owoc[20];
        for (int i = 0; i < koszyk.length; i++) {
            Owoc owoc = new Owoc(unsorted_koszyk[i].kaliber, unsorted_koszyk[i].waga);
            koszyk[i] = owoc;
        }


        for (int i = 1; i < koszyk.length; i++) {
            Owoc elem = koszyk[i];
            int j = i - 1;

            while (j >= 0 && koszyk[j].kaliber >= elem.kaliber) {
                if (koszyk[j].kaliber > elem.kaliber) {
                    koszyk[j + 1] = koszyk[j];
                    j--;
                    myfinalTab[2][index_stanow] = copy_current_state_of_table(koszyk);
                    index_stanow++;
                } else {
                    if (koszyk[j].waga >= elem.waga) {
                        koszyk[j + 1] = koszyk[j];
                        j--;
                        myfinalTab[2][index_stanow] = copy_current_state_of_table(koszyk);
                        index_stanow++;
                    } else {
                        break;
                    }
                }
            }
            koszyk[j + 1] = elem;
        }
        for (int i = 0; i < koszyk.length; i++) {
            koszyk[i].Wydrukuj();
        }
    }

    public static void swap(Owoc el, Owoc elem) {
        double temp = elem.waga;
        elem.waga = el.waga;
        el.waga = temp;
    }

    public static Owoc[] copy_current_state_of_table(Owoc[] koszyk) {
        Owoc[] nowa_tablica = new Owoc[koszyk.length];
        for (int i = 0; i < koszyk.length; i++) {
            nowa_tablica[i] = new Owoc(koszyk[i].kaliber, koszyk[i].waga);
        }
        return nowa_tablica;
    }
    public static class Owoc {
        public int kaliber;
        public double waga;

        public Owoc(int kaliber, double waga) {
            this.kaliber = kaliber;
            this.waga = waga;
        }

        public void Wydrukuj() {
            System.out.println("Kaliber to:" + this.kaliber + " Waga to:" + this.waga);
        }
    }

}



