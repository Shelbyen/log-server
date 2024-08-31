package funn.j2k.logserver

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.util.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.nio.ByteBuffer
import kotlin.text.toByteArray

fun main() = runBlocking {
    val selectorManager = SelectorManager(Dispatchers.IO)
    val localAddress = InetSocketAddress("127.0.0.1", 54527)
    val socket = aSocket(selectorManager).udp().connect(localAddress)
    println("Start listen")

    while (true) {
        launch {
            val receiveChannel = socket.openReadChannel()
            val dst = ByteBuffer.allocate(1000)
            try {
                while (true) {

                    receiveChannel.readUntilDelimiter(ByteBuffer.wrap("<191>".toByteArray()), dst)
                    println(dst.decodeString())
                    dst.clear()
                }
            } catch (e: Throwable) {
                println(e.message)
            }
        }
    }
}