package com.ll;

import java.util.Scanner;

public class App {
    private Scanner scanner;
    public App(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        int quotationId = 0;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String quotation = scanner.nextLine();
                System.out.print("작가 : ");
                String authorName = scanner.nextLine();

                quotationId++;
                System.out.println(quotationId + "번 명언이 등록되었습니다.");
            }
        }

    }
}