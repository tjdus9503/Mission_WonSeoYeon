package com.ll;

import com.ll.domain.quotation.Quotation;
import com.ll.domain.quotation.QuotationController;

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

        QuotationController quotationController = new QuotationController(scanner, quotations);

        System.out.println("== 명언 앱 ==");

        boolean isClosed = false;

        while (!isClosed) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    isClosed = true;
                    break;
                case "등록":
                    quotationController.actionRegist();
                    break;
                case "목록":
                    quotationController.actionPrintList();
                    break;
                case "삭제":
                    quotationController.actionDelete(rq);
                    break;
                case "수정":
                    quotationController.actionModify(rq);
                    break;
            }
        }
    }
}