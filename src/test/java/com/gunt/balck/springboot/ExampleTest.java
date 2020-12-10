package com.gunt.balck.springboot;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ExampleTest {

    @Test
    public void exampleTest() {
        String str = "!!!!!!!!!!!!!!!!!!!";
        System.out.println(str);
        assertThat(str, is(str));
    }
}
