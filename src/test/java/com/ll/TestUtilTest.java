package com.ll;

import com.ll.standard.testUtil.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtilTest {
    @Test
    @DisplayName("TestUtil.genScanner() 테스트")
    void t1() {
        Scanner scanner = TestUtil.genScanner("""
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                """.stripIndent());
        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        String authorName = scanner.nextLine();

        assertThat(cmd).isEqualTo("등록");
        assertThat(content).isEqualTo("나의 죽음을 적들에게 알리지 말라!");
        assertThat(authorName).isEqualTo("이순신");
    }

    @Test
    @DisplayName("TestUtil.setOutToByteArray() 테스트")
    void t2() {
        // 모니터로 출력을 끄는 것
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        System.out.println("2 / 이순신 / 나의 죽음을 적들에게 알리지 말라!");

        String out = byteArrayOutputStream.toString().trim(); //개행 제거를 위해 사용

        assertThat(out).isEqualTo("2 / 이순신 / 나의 죽음을 적들에게 알리지 말라!");

        // 모니터로 출력을 다시 켜는 것
        TestUtil.clearSetOutToByteArray(byteArrayOutputStream);
        System.out.println("이제는 화면에 출력됩니다.");
    }
}