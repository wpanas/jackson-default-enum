package com.github.wpanas

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class AppTest {

    @TestFactory
    fun deserialization() = listOf(
            "RED" to 0,
            "GREEN" to 1,
            "BLUE" to 2,
            "UNKNOWN" to -1,
            "MISSING" to -1,
            "YELLOW" to -1
    ).map { (color, expected) ->
        DynamicTest.dynamicTest("when I deserialize '$color', then I get $expected") {
            Assertions.assertEquals(expected, App.handle("""{"color": "$color"}"""))
        }
    }
}
