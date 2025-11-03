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


    fun init() {

        val file = File("locale/zh.json")
        val tree = ObjectMapper().readTree(file)
        val list = arrayListOf<WarframeLocale>()
        tree.get("resources").get("string").elements().forEach { node: JsonNode ->
            val key = node.get("_attributes").get("name").textValue()
            val value = node.get("_text").textValue()
            list.add(WarframeLocale().apply {
                this.lkey = key
                this.lvalue = value
            })

        }
        localeRepository.saveOrUpdateBatch(list, list.size)


    }

    fun find(key: String): WarframeLocale? {
        return localeRepository.query()
            .eq("lkey", key)
            .one()
    }
}