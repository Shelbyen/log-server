package funn.j2k.logserver

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val selectorManager = SelectorManager(Dispatchers.IO)
    val localAddress = InetSocketAddress("127.0.0.1", 54527)
    val socket = aSocket(selectorManager).udp().connect(localAddress)

    while (true) {
        launch {
            val receiveChannel = socket.openReadChannel()

            try {
                while (true) {
                    val newString = receiveChannel.readUTF8Line()
                    println(newString)
                }
            } catch (e: Throwable) {
                println(e.message)
            }
        }
    }
}