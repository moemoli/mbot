package moe.imoli.mbot.cloud.warframe.controller

import moe.imoli.mbot.cloud.warframe.data.ResponseData
import moe.imoli.mbot.cloud.warframe.service.LocaleService
import moe.imoli.mbot.cloud.warframe.service.NodeService
import moe.imoli.mbot.cloud.warframe.service.ResourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/init")
class WarframeInitController {

    @Autowired
    lateinit var localeService: LocaleService

    @Autowired
    lateinit var resourceService: ResourceService

    @Autowired
    lateinit var nodeService: NodeService

    @RequestMapping("/locale")
    fun locale(): ResponseData {
        localeService.init()
        return ResponseData.success("")
    }

    @RequestMapping("/resources")
    fun resources(): ResponseData {
        resourceService.init()
        return ResponseData.success("")
    }

    @RequestMapping("/nodes")
    fun nodes(): ResponseData {
        nodeService.init()
        return ResponseData.success("")
    }
}