package com.ll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Quotation> quotations = new ArrayList<>();
        new App(scanner, quotations).run();
    }
}