package io.datalbry.testcontainers.bigtable

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
internal class AlxndriaContainerTest {

    companion object {
        @Container private val alxndria = AlxndriaContainer()
    }

    @Test
    fun test() {
        assertTrue(alxndria.isRunning)
    }

}
