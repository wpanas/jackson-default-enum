package com.github.wpanas

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import com.fasterxml.jackson.module.kotlin.readValue

object App {
	private val objectMapper = jacksonMapperBuilder()
			.enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
			.build()

	fun handle(jsonString: String): Int = when (objectMapper.readValue<Pixel>(jsonString).color) {
		Color.RED -> 0
		Color.GREEN -> 1
		Color.BLUE -> 2
		Color.UNKNOWN -> -1
	}
}

data class Pixel(@JsonSetter(nulls = Nulls.AS_EMPTY) val color: Color = Color.UNKNOWN)

enum class Color {
	RED, GREEN, BLUE,

	@JsonEnumDefaultValue
	UNKNOWN
}

fun main() {
	print(App.handle("""{"color": "YELLOW"}"""))
}
