package com.ll.base;

import com.ll.domain.quotation.Quotation;
import com.ll.domain.quotation.QuotationController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner scanner;
    private List<Quotation> quotations;

    public App() {
        this.scanner = new Scanner(System.in);
        this.quotations = new ArrayList<>();
    }

    public App(Scanner scanner, List<Quotation> quotations) {
        // 테스트를 위한 App 생성자 (quotations를 밖에서 관리한다.)
        this.scanner = scanner;
        this.quotations = quotations;

        this.quotations.clear();
    }

    public void run() {

        QuotationController quotationController = new QuotationController(scanner, quotations);

        boolean isClosed = false;

        System.out.println("== 명언 앱 ==");

        quotationController.actionLoad();

        while (!isClosed) {
            System.out.print("명령) ");

            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);

            switch (rq.getAction()) {
                case "종료":
                    isClosed = true;
                    quotationController.actionSave();
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
                case "전체삭제":
                    quotationController.actionDeleteAll();
                    break;
                case "수정":
                    quotationController.actionModify(rq);
                    break;
                case "빌드":
                    quotationController.actionBuild();
                    break;
            }
        }
    }
}