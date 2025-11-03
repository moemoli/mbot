package moe.imoli.mbot.cloud.warframe.utils

import sevenzip.Compression.LZMA.Decoder
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset

class ExtraFunctions {
}

fun InputStream.lzma(): String {
    val decoder = Decoder()
    val ous = ByteArrayOutputStream()
    val properties = ByteArray(5)
    this.read(properties, 0, properties.size)
    decoder.SetDecoderProperties(properties)
    var outSize: Long = 0
    for (i in 0..7) {
        val v: Int = this.read()
        if (v < 0) throw Exception("Can't read stream size")
        outSize = outSize or ((v.toLong()) shl (8 * i))
    }
    decoder.Code(this, ous, outSize)

    return ous.toString(Charset.defaultCharset())
}