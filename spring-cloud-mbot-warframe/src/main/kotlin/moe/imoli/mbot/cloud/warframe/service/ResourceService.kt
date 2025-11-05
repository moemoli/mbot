package moe.imoli.mbot.cloud.warframe.service

import com.fasterxml.jackson.databind.ObjectMapper
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeResource
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeResourceRepository
import moe.imoli.mbot.cloud.warframe.webclient.WarframeContent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourceService {


    @Autowired
    lateinit var content: WarframeContent

    @Autowired
    lateinit var resourceRepository: WarframeResourceRepository

    private val mapper = ObjectMapper()

    fun init() {
        val manifest = content.index()!!
        val resourceList = arrayListOf<WarframeResource>()
        mapper.readTree(
            content.manifest(
                manifest.replace("\r", "")
                    .split("\n")
                    .find { it.startsWith("ExportResources") }
            )
        ).get("ExportResources")
            .forEach { node ->
                resourceList.add(WarframeResource().apply {
                    this.name = node.get("name").asText()
                    this.uniqueName = node.get("uniqueName").asText()
                    if (node.has("description")) {
                        this.description = node.get("description").asText()
                    }
                    if (node.has("parentName")) {
                        this.parentName = node.get("parentName").asText()
                    }
                })
            }

        resourceRepository.saveOrUpdateBatch(resourceList, resourceList.size)


    }

    fun find(uniqueName: String): WarframeResource? {
        return resourceRepository.query().eq("unique_name", uniqueName).one()
    }


}