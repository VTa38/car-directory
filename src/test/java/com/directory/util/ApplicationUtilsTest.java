package com.directory.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationUtilsTest {

    @Test
    void notEmpty() {
        // given
        String test = "someNotNullString";

        // when
        boolean isFind = ApplicationUtils.isEmpty(test);

        // then
        assertThat(isFind).isFalse();
    }

    @Test
    void isEmpty() {
        // given
        String test = null;

        // when
        boolean isFind = ApplicationUtils.isEmpty(test);

        // then
        assertThat(isFind).isTrue();
    }
}