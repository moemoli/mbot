package moe.imoli.mbot.cloud.warframe.service

import com.fasterxml.jackson.databind.ObjectMapper
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeNode
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeNodeRepository
import moe.imoli.mbot.cloud.warframe.webclient.WarframeContent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File

@Service
class NodeService {


    @Autowired
    lateinit var content: WarframeContent

    @Autowired
    lateinit var nodeRepository: WarframeNodeRepository

    private val mapper = ObjectMapper()

    fun init(lang: String = "zh") {
        val file = File("warframe-worldstate-data/data/$lang/solNodes.json")
        val data = mapper.readTree(file)
        val list = arrayListOf<WarframeNode>()
        data.properties().forEach { property ->
            list.add(WarframeNode().apply {
                this.uniqueName = property.key
                this.name = property.value.get("value").asText()
                if (property.value.has("enemy"))
                    this.enemy = property.value.get("enemy").asText()
                if (property.value.has("type"))
                    this.type = property.value.get("type").asText()
            })
        }

        nodeRepository.saveOrUpdateBatch(list, list.size)

    }

    fun find(uniqueName: String): WarframeNode? {
        return nodeRepository.query().eq("unique_name", uniqueName).one()
    }


}