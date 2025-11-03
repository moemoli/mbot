package moe.imoli.mbot.cloud.warframe.webclient

import moe.imoli.mbot.cloud.warframe.utils.lzma
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import kotlin.math.floor

@Service
class WarframeContent() {
    private val webClient: WebClient =
        WebClient.builder()
            .baseUrl("https://content.warframe.com/PublicExport/")
            .codecs {
                it.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)
            }
            .build()


    fun index(lang: String = "zh"): String? {
        return webClient.get()
            .uri("/index_$lang.txt.lzma")
            .retrieve()
            .bodyToMono(ByteArray::class.java).block()!!
            .inputStream()
            .lzma()
    }

    fun manifest(hash: String?): String? {
        if (hash.isNullOrBlank()) return null
        return webClient.get()
            .uri("/Manifest/$hash")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

    }

    private fun randomIndex(): String {
        val currentTime = floor(System.currentTimeMillis() / 1e3)
        val roundedTime = currentTime - (currentTime % 300)
        val randomPrefix = ("00000000" + roundedTime.toULong().toString(16))
            .uppercase()

        return randomPrefix.substring(randomPrefix.length - 8)
    }
}