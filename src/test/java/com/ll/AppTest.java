package com.ll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

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
}