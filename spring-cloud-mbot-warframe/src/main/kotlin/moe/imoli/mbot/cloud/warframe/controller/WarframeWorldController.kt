package moe.imoli.mbot.cloud.warframe.controller

import com.fasterxml.jackson.databind.ObjectMapper
import moe.imoli.mbot.cloud.warframe.data.ResponseData
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeLocale
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeLocaleRepository
import moe.imoli.mbot.cloud.warframe.service.LocaleService
import moe.imoli.mbot.cloud.warframe.service.WorldService
import moe.imoli.mbot.cloud.warframe.webclient.WarframeContent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("/world")
class WarframeWorldController {


    @Autowired
    lateinit var worldService: WorldService



    @RequestMapping("/", "/index")
    fun index(lang: String = "zh"): ResponseData {
        return ResponseData.success(
            worldService.all()
        )
    }

    @RequestMapping("/alert")
    fun alert(): ResponseData {
        return ResponseData.success(worldService.alert())
    }
}