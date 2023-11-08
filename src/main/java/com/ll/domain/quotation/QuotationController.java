package com.ll.domain.quotation;

import com.ll.base.Rq;
import com.ll.standard.util.Ut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class QuotationController {

    private Scanner scanner;
    private List<Quotation> quotations;
    private int quotationId;

    public QuotationController (Scanner scanner, List<Quotation> quotations) {
        this.scanner = scanner;
        this.quotations = quotations;
    }

    public void actionLoad() {
//        (1)파일 로드 기능
        try {
            BufferedReader reader = new BufferedReader(new FileReader("quotations.txt"));
            String line;
            boolean isStartLine = true;

            while ((line = reader.readLine()) != null) {

                if (isStartLine) {
                    quotationId = Ut.Str.parseInt(line, 0);
                    isStartLine = false;
                } else {
                    String[] lineBits = line.split(",");

                    if (lineBits.length != 3) continue; // 필드 개수 체크

                    int id = Ut.Str.parseInt(lineBits[0], 0);
                    String authorName = lineBits[1];
                    String content = lineBits[2];

                    if (id == 0) continue; // id가 0이면 오류가 발생했거나, 잘못된 데이터

                    Quotation quotation = new Quotation(id, authorName, content);

                    quotations.add(quotation);
                }
            }

            reader.close();

            System.out.println("파일 로드 성공");
        }
        catch (IOException e) {
            System.out.println("파일 읽기 오류");
        }
    }

    public void actionSave() {

        try {
            PrintWriter writer = new PrintWriter("quotations.txt", "UTF-8");

            writer.println(quotationId);

            for (Quotation q : quotations) {
                writer.printf("%d,%s,%s\n", q.getId(), q.getAuthorName(), q.getContent());
            }

            writer.close();
            
            System.out.println("파일 저장 성공");
        }
        catch (IOException e) {
            System.out.println("파일 쓰기 오류");
        }
    }

    public void actionRegist() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        quotationId++;

        Quotation quotation = new Quotation(quotationId, authorName, content);
        quotations.add(quotation);

        System.out.println(quotationId + "번 명언이 등록되었습니다.");
    }

    public void actionPrintList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("------------------------");

        if (!isQuotationExist()) return;

        for (int i = quotations.size() - 1; i >= 0 ; i--) {
            int id = quotations.get(i).getId();
            String authorName = quotations.get(i).getAuthorName();
            String quotation = quotations.get(i).getContent();

            System.out.printf("%d / %s / %s\n", id, authorName, quotation);
        }
    }

    public void actionDelete(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (!isQuotationExist() || isWrongId(id)) return;

        boolean isExistingId = false;

        for (int i = 0; i < quotations.size(); i++) {
            if (quotations.get(i).getId() == id) {

                isExistingId = true;
                quotations.remove(i);

                System.out.println(id + "번 명언이 삭제되었습니다.");

                break;
            }
        }

        printIfNonExistingId(isExistingId, id);
    }

    public void actionDeleteAll() {
        quotations.clear();
    }

    public void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (!isQuotationExist() || isWrongId(id)) return;

        boolean isExistingId = false;

        for (Quotation quotation : quotations) {
            if (quotation.getId() == id) {

                isExistingId = true;

                System.out.println("명언(기존) : " + quotation.getContent());
                System.out.print("명언 : ");
                String newContent = scanner.nextLine();

                System.out.println("작가(기존) : " + quotation.getAuthorName());
                System.out.print("작가 : ");
                String newAuthorName = scanner.nextLine();

                quotation.modifyQuotation(newAuthorName, newContent);

                System.out.println(id + "번 명언이 삭제되었습니다.");

                break;
            }
        }

        printIfNonExistingId(isExistingId, id);
    }

    private boolean isQuotationExist() {
        if (quotations.size() == 0) {
            System.out.println("등록된 명언이 없습니다.");
            return false;
        }
        else {
            return true;
        }
    }

    private boolean isWrongId(int id) {
        if (id == 0) {
            System.out.println("명언ID를 다시 확인해주세요.");
            return true;
        }
        else {
            return false;
        }
    }

    private void printIfNonExistingId(boolean isExist, int id) {
        if (!isExist) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }
}
