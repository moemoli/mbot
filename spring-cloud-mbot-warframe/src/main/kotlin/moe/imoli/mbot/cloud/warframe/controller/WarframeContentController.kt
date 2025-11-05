package moe.imoli.mbot.cloud.warframe.controller

import com.fasterxml.jackson.databind.ObjectMapper
import moe.imoli.mbot.cloud.warframe.data.ResponseData
import moe.imoli.mbot.cloud.warframe.service.LocaleService
import moe.imoli.mbot.cloud.warframe.webclient.WarframeContent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/content")
class WarframeContentController {

    @Autowired
    lateinit var content: WarframeContent

    private val mapper = ObjectMapper()

    @Autowired
    lateinit var localeService: LocaleService


    @RequestMapping("/", "/index")
    fun index(lang: String = "zh"): ResponseData {

        return ResponseData.list(content.index(lang)!!.replace("\r", "").split("\n"))
    }


    @RequestMapping("/locale")
    fun locale(key: String): ResponseData {
        return ResponseData.successOrEmpty(localeService.find(key))
    }


    @RequestMapping("/manifest/{hash}")
    fun manifest(@PathVariable hash: String?): ResponseData {
        return ResponseData.success(mapper.readTree(content.manifest(hash)))
    }


}