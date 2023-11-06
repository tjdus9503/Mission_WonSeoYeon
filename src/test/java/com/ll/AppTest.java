package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    @Test
    @DisplayName("종료를 입력하면 꺼진다.")
    void t1() {
        Scanner scanner = TestUtil.genScanner("""
                종료
                """.stripIndent());

        new App(scanner).run();

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

        new App(scanner).run();

        scanner.close();
    }

    @Test
    @DisplayName("등록을 완료하면 1번 명언이 등록되었습니다. 출력")
    void t3() {
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        Scanner scanner = TestUtil.genScanner("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """.stripIndent());

        new App(scanner).run();

        scanner.close();

        String output = byteArrayOutputStream.toString();

        assertThat(output).contains("1번 명언이 등록되었습니다.");

        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
    }
}