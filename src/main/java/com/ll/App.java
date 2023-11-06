package com.ll;

import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner scanner;
    private List<Quotation> quotations;
    public App(Scanner scanner, List<Quotation> quotations) {
        this.scanner = scanner;
        this.quotations = quotations;
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
                Quotation quotation;

                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String authorName = scanner.nextLine();

                quotationId++;

                quotation = new Quotation(quotationId, authorName, content);
                quotations.add(quotation);

                System.out.println(quotationId + "번 명언이 등록되었습니다.");
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("------------------------");

                for (int i = quotations.size() - 1; i >= 0 ; i--) {
                    int id = quotations.get(i).getId();
                    String authorName = quotations.get(i).getAuthorName();
                    String quotation = quotations.get(i).getQuotation();

                    System.out.printf("%d / %s / %s\n", id, authorName, quotation);
                }
            } else if (cmd.startsWith("삭제?")) {
                //cmd : 삭제?id=1
                String[] cmdBits = cmd.split("\\?");
                String query = cmdBits[1];

                String[] queryBits = query.split("=");
                String id = queryBits[1];

                boolean isDeleted = false;

                for (int i = 0; i < quotations.size(); i++) {
                    if (quotations.get(i).getId() == Integer.parseInt(id)) {
                        quotations.remove(i);

                        isDeleted = true;
                        System.out.println(id + "번 명언이 삭제되었습니다.");

                        break;
                    }
                }

                if (!isDeleted) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                }
            }
        }

    }
}