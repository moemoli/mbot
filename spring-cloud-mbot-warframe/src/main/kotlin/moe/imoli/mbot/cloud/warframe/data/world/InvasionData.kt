package moe.imoli.mbot.cloud.warframe.data.world

data class InvasionData(

    var start: Long,
    var location: String,
    var system: String,
    var attack: InvasionMissionInfo,
    var defend: InvasionMissionInfo,
    var completed: Boolean,
    var count: Int,
    var goal: Int,
)

data class InvasionMissionInfo(
    var faction: String,
    var seed: Long,
    var items: List<InvasionRewardItem>
)

data class InvasionRewardItem(
    var name: String,
    var count: Int,
)


