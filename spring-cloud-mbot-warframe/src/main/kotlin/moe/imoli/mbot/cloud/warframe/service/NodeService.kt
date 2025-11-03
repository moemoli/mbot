package moe.imoli.mbot.cloud.warframe.service

import com.fasterxml.jackson.databind.ObjectMapper
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeNode
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeNodeRepository
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeResource
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeResourceRepository
import moe.imoli.mbot.cloud.warframe.webclient.WarframeContent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NodeService {


    @Autowired
    lateinit var content: WarframeContent

    @Autowired
    lateinit var nodeRepository: WarframeNodeRepository

    private val mapper = ObjectMapper()

    fun init() {
        val manifest = content.index()!!
        val resourceList = arrayListOf<WarframeNode>()
        mapper.readTree(
            content.manifest(
                manifest.replace("\r", "")
                    .split("\n")
                    .find { it.startsWith("ExportRegions") }
            )
        ).get("ExportRegions")
            .forEach { node ->
                resourceList.add(WarframeNode().apply {
                    this.uniqueName = node.get("uniqueName").asText()
                    this.name = node.get("name").asText()
                    this.systemName = node.get("systemName").asText()
                    this.nodeType = node.get("nodeType").asInt()
                    this.masteryReq = node.get("masteryReq").asInt()
                    this.missionIndex = node.get("missionIndex").asInt()
                    this.factionIndex = node.get("factionIndex").asInt()
                    this.minEnemyLevel = node.get("minEnemyLevel").asInt()
                    this.maxEnemyLevel = node.get("maxEnemyLevel").asInt()
                })
            }

        nodeRepository.saveOrUpdateBatch(resourceList, resourceList.size)


    }

    fun find(uniqueName: String): WarframeNode? {
        return nodeRepository.query().eq("unique_name", uniqueName).one()
    }


}