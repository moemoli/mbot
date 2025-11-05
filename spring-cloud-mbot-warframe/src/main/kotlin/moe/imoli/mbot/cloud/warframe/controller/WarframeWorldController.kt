package moe.imoli.mbot.cloud.warframe.controller

import moe.imoli.mbot.cloud.warframe.data.ResponseData
import moe.imoli.mbot.cloud.warframe.service.WorldService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

    @RequestMapping("/invasions")
    fun invasions(): ResponseData {
        return ResponseData.success(worldService.invasions())
    }

    @RequestMapping("fissure")
    fun fissure(): ResponseData {
        return ResponseData.success(worldService.fissure())
    }

    @RequestMapping("voidStorms")
    fun voidStorms(): ResponseData {
        return ResponseData.success(worldService.voidStorms())
    }
}