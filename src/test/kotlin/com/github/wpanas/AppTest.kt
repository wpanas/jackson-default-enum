package com.github.wpanas

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class AppTest {

    @TestFactory
    fun deserialization() = listOf(
            "RED" to 0,
            "GREEN" to 1,
            "BLUE" to 2,
            "UNKNOWN" to -1,
            "MISSING" to -1,
            "YELLOW" to -1
    ).map { (color, expected) ->
        dynamicTest("when I deserialize '$color', then I get $expected") {
            assertEquals(expected, App.handle("""{"color": "$color"}"""))
        }
    }

    @Test
    fun `should deserialize null to -1`() {
        val actual = App.handle("""{"color": null}""")

        assertEquals(-1, actual)
    }

    @Test
    fun `should deserialize no property to -1`() {
        val actual = App.handle("""{}""")

        assertEquals(-1, actual)
    }
}
