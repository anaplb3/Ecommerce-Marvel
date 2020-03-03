package com.example.ecommercemarvel.test

import com.example.ecommercemarvel.model.Comic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ComicTest {
    private var objectUnderTest: Comic? = null;

    @Before
    fun setUp() {
        //given
        objectUnderTest = Comic(0, "homem aranha", 25.5, true);
    }

    @Test
    fun `test get comic name`() {

        //when
        val title = objectUnderTest?.title;
        //then
        assertEquals(title, "homem aranha");

    }

    @Test
    fun testGetPrice() {
        //when
        val price = objectUnderTest?.mockPrice;
        //then
        assertEquals(price, 25.5);
    }
}