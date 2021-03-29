package io.datalbry.testcontainers.bigtable

import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy

/**
 * [AlxndriaContainer] is a simple testcontainer implementation based on the Google Cloud SDK.
 * The underlying BigTable emulator implementation is running in-memory. Therefore it has some limitations
 *
 * @author timo gruen - 2021-03-19
 */
class AlxndriaContainer(
    version: String = "0.0.1-SNAPSHOT",
    private val port: Int = 8080
)
    : GenericContainer<AlxndriaContainer>("datalbry/alxndria-platform-emulator:$version")
{

    init {
        this.withExposedPorts(port)
        this.setWaitStrategy(LogMessageWaitStrategy().withRegEx("(?s).*Started AlxndriaEmulatorKt.*$"));
    }

    fun getPort(): Int {
        return getMappedPort(port)
    }
}
