package moe.imoli.mbot.cloud.warframe.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeLocale
import moe.imoli.mbot.cloud.warframe.data.entity.WarframeLocaleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File

@Service
class LocaleService {
    @Autowired

    lateinit var localeRepository: WarframeLocaleRepository
    private val mapper = ObjectMapper()


    fun init(lang: String = "zh") {
        val file = File("locale/$lang.json")
        val tree = mapper.readTree(file)
        val list = arrayListOf<WarframeLocale>()
        tree.get("resources").get("string").elements().forEach { node: JsonNode ->
            val key = node.get("_attributes").get("name").textValue()
            val value = node.get("_text").textValue()
            list.add(WarframeLocale().apply {
                this.lkey = key
                this.lvalue = value
            })

        }
        val factions = File("warframe-worldstate-data/data/$lang/factionsData.json")
        val missions = File("warframe-worldstate-data/data/$lang/missionTypes.json")
        val fissures = File("warframe-worldstate-data/data/$lang/fissureModifiers.json")
        val syndicates = File("warframe-worldstate-data/data/$lang/syndicatesData.json")
        mapper.readTree(factions).properties().forEach { (key, value) ->
            list.add(WarframeLocale().apply {
                this.lkey = key
                if (value.has("value"))
                    this.lvalue = value.get("value").asText()
            })
        }
        mapper.readTree(missions).properties().forEach { (key, value) ->
            list.add(WarframeLocale().apply {
                this.lkey = key
                if (value.has("value"))
                    this.lvalue = value.get("value").asText()
            })
        }
        mapper.readTree(fissures).properties().forEach { (key, value) ->
            list.add(WarframeLocale().apply {
                this.lkey = key
                if (value.has("value"))
                    this.lvalue = value.get("value").asText()
            })
        }
        mapper.readTree(syndicates).properties().forEach { (key, value) ->
            list.add(WarframeLocale().apply {
                this.lkey = key
                if (value.has("name"))
                    this.lvalue = value.get("name").asText()
            })
        }

        localeRepository.saveOrUpdateBatch(list, list.size)


    }

    fun find(key: String): WarframeLocale? {
        return localeRepository.query()
            .eq("lkey", key)
            .list()[0]
    }
}