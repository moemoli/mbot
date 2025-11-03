package moe.imoli.mbot.cloud.warframe.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import moe.imoli.mbot.cloud.warframe.data.WarframeTypes
import moe.imoli.mbot.cloud.warframe.data.world.AlertData
import moe.imoli.mbot.cloud.warframe.data.world.AlertReward
import moe.imoli.mbot.cloud.warframe.data.world.AlertRewardItem
import moe.imoli.mbot.cloud.warframe.data.world.InvasionData
import moe.imoli.mbot.cloud.warframe.data.world.InvasionMissionInfo
import moe.imoli.mbot.cloud.warframe.data.world.InvasionRewardItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class WorldService {


    @Autowired
    lateinit var localeService: LocaleService

    @Autowired
    lateinit var resourceService: ResourceService

    @Autowired
    lateinit var nodeService: NodeService

    private val mapper = ObjectMapper()
    private val worldState = mapper.readTree(
        WebClient.create("https://api.warframe.com/cdn/worldState.php")
            .get()
            .retrieve()
            .bodyToMono(String::class.java)
            .block()
    )

    fun all(): JsonNode = worldState

    fun invasions(): List<InvasionData> {
        val invasions = worldState.get("Invasions")
        val data = arrayListOf<InvasionData>()
        invasions.forEach { node ->
            val location = nodeService.find(node.get("Node").asText())!!
            data.add(
                InvasionData(
                    node.get("Activation").get("\$date").get("\$numberLong").asLong(),
                    location.name,
                    location.systemName,
                    attack = InvasionMissionInfo(
                        localeService.find(
                            WarframeTypes.Faction
                                .valueOf(
                                    node.get("AttackerMissionInfo").get("faction").asText()
                                ).getName()
                        )?.lvalue ?: node.get("AttackerMissionInfo").get("faction").asText(),
                        node.get("AttackerMissionInfo").get("seed").asLong(),
                        items = arrayListOf()
                    ).apply {
                        val items = arrayListOf<InvasionRewardItem>()
                        if (node.get("AttackerReward").has("countedItems")) {
                            node.get("AttackerReward").get("countedItems").forEach { item ->
                                items.add(
                                    InvasionRewardItem(
                                        resourceService.find(item.get("ItemType").asText())?.name
                                            ?: item.get("ItemType").asText(),
                                        item.get("ItemCount").asInt()
                                    )
                                )
                            }
                        }

                        this.items = items
                    },
                    defend = InvasionMissionInfo(
                        localeService.find(
                            WarframeTypes.Faction
                                .valueOf(
                                    node.get("DefenderMissionInfo").get("faction").asText()
                                ).getName()
                        )?.lvalue ?: node.get("DefenderMissionInfo").get("faction").asText(),
                        node.get("DefenderMissionInfo").get("seed").asLong(),
                        items = arrayListOf()
                    ).apply {
                        val items = arrayListOf<InvasionRewardItem>()
                        if (node.get("DefenderReward").has("countedItems")) {
                            node.get("DefenderReward").get("countedItems").forEach { item ->
                                items.add(
                                    InvasionRewardItem(
                                        resourceService.find(item.get("ItemType").asText())?.name
                                            ?: item.get("ItemType").asText(),
                                        item.get("ItemCount").asInt()
                                    )
                                )
                            }
                        }

                        this.items = items
                    },
                    node.get("Completed").asBoolean(),
                    node.get("Count").asInt(),
                    node.get("Goal").asInt(),
                )
            )
        }
        return data
    }

    fun alert(): List<AlertData> {
        val alerts = worldState.get("Alerts")
        val data = arrayListOf<AlertData>()
        alerts.forEach { node ->
            val location = nodeService.find(node.get("MissionInfo").get("location").asText())!!
            data.add(
                AlertData(
                    node.get("Activation").get("\$date").get("\$numberLong").asLong(),
                    node.get("Expiry").get("\$date").get("\$numberLong").asLong(),
                    node.get("Tag").asText(),
                    location.name,
                    location.systemName,
                    localeService.find(
                        WarframeTypes.Mission
                            .valueOf(
                                node.get("MissionInfo")
                                    .get("missionType")
                                    .asText()
                            ).getName()
                    )!!.lvalue,
                    localeService.find(
                        WarframeTypes.Faction
                            .valueOf(
                                node.get("MissionInfo")
                                    .get("faction")
                                    .asText()
                            ).getName()
                    )!!.lvalue,
                    node.get("MissionInfo").get("difficulty").asInt(),
                    node.get("MissionInfo").get("minEnemyLevel").asInt(),
                    node.get("MissionInfo").get("maxEnemyLevel").asInt(),
                    node.get("MissionInfo").get("maxWaveNum").asInt(),
                    AlertReward(
                        0, arrayListOf()
                    ).apply {
                        val reward = node.get("MissionInfo").get("missionReward")
                        if (reward.has("credits")) {
                            this.credits = reward.get("credits").asLong()
                        }
                        if (reward.has("countedItems")) {
                            val items = arrayListOf<AlertRewardItem>()
                            reward.get("countedItems").forEach { item ->
                                items.add(
                                    AlertRewardItem(
                                        resourceService.find(item.get("ItemType").asText())?.name
                                            ?: item.get("ItemType").asText(),
                                        item.get("ItemCount").asInt()
                                    )
                                )
                            }
                            this.items = items
                        }
                    }


                ))
        }

        return data
    }


}