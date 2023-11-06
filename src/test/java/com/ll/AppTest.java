package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    private List<Quotation> quotations = new ArrayList<>();
    @Test
    @DisplayName("종료를 입력하면 꺼진다.")
    void t1() {
        Scanner scanner = TestUtil.genScanner("""
                종료
                """.stripIndent());

        new App(scanner, quotations).run();

        scanner.close();
    }

    @Test
    @DisplayName("등록을 입력하면 명언과 작가를 입력받는다.")
    void t2() {
        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """.stripIndent());

        new App(scanner, quotations).run();

        scanner.close();
    }

    @Test
    @DisplayName("등록을 완료하면 \"1번 명언이 등록되었습니다.\" 를 출력")
    void t3() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """.stripIndent());

        new App(scanner, quotations).run();

        scanner.close();

        String output = byteArrayOutputStream.toString();

        assertThat(output).contains("1번 명언이 등록되었습니다.");

        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }

    @Test
    @DisplayName("등록할 때마다 출력되는 메시지에서 명언 번호가 증가")
    void t4() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                너 자신을 알라
                플라톤
                종료
                """.stripIndent());

        new App(scanner, quotations).run();

        scanner.close();

        String output = byteArrayOutputStream.toString();

        assertThat(output).contains("1번 명언이 등록되었습니다.");
        assertThat(output).contains("2번 명언이 등록되었습니다.");

        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }

    @Test
    @DisplayName("목록을 입력하면 등록된 명언들을 출력한다.")
    void t5() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                너 자신을 알라.
                플라톤
                목록
                종료
                """.stripIndent());

        new App(scanner, quotations).run();

        scanner.close();

        String output = byteArrayOutputStream.toString();

        assertThat(output).contains("번호 / 작가 / 명언");
        assertThat(output).contains("------------------------");
        assertThat(output).contains("2 / 플라톤 / 너 자신을 알라.");
        assertThat(output).contains("1 / 작자미상 / 현재를 사랑하라.");

        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }

    @Test
    @DisplayName("삭제를 입력하면 id에 해당하는 명언이 삭제되고 메시지 출력")
    void t6() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                너 자신을 알라.
                플라톤
                목록
                삭제?id=1
                종료
                """.stripIndent());

        new App(scanner, quotations).run();

        scanner.close();

        String output = byteArrayOutputStream.toString();

        boolean isDeleted = true;
        for (Quotation quotation : quotations) {
            if (quotation.getId() == 1) {
                isDeleted = false;
            }
        }
        assertThat(isDeleted).isTrue();
//        assertThat(quotations).extracting("id").asList().doesNotContain(1);

        assertThat(output).contains("1번 명언이 삭제되었습니다.");

        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }

    @Test
    @DisplayName("삭제를 입력할 때 id에 해당하는 명언이 없는 경우의 메시지 출력")
    void t7() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                너 자신을 알라.
                플라톤
                목록
                삭제?id=1
                삭제?id=1
                종료
                """.stripIndent());

        new App(scanner, quotations).run();

        scanner.close();

        String output = byteArrayOutputStream.toString();

        assertThat(output).contains("1번 명언은 존재하지 않습니다.");

        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }

    @Test
    @DisplayName("수정을 입력하면 id에 해당하는 명언의 내용과 작가를 수정한다.")
    void t8() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                너 자신을 알라.
                플라톤
                목록
                삭제?id=1
                삭제?id=1
                수정?id=2
                나의 죽음을 적에게 알리지 말라.
                이순신
                목록
                종료
                """.stripIndent());

        new App(scanner, quotations).run();

        scanner.close();

        String modifiedContent = "";
        String modifiedAuthorName = "";

        for (Quotation quotation : quotations) {
            if (quotation.getId() == 2) {
                modifiedContent = quotation.getContent();
                modifiedAuthorName = quotation.getAuthorName();
            }
        }

        assertThat(modifiedContent).isEqualTo("나의 죽음을 적에게 알리지 말라.");
        assertThat(modifiedAuthorName).isEqualTo("이순신");

        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }
}