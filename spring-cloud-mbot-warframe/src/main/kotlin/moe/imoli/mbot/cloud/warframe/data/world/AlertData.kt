package moe.imoli.mbot.cloud.warframe.data.world

data class AlertData(

    var start: Long,
    var expire: Long,
    var tag: String,
    var location: String,
    var system: String,
    var type: String,
    var faction: String,
    var difficulty: Int,
    var minEnemyLevel: Int,
    var maxEnemyLevel: Int,
    var maxWaveNum: Int,
    var reward: AlertReward
)

data class AlertReward(
    var credits: Long,
    var items: List<AlertRewardItem>
)

data class AlertRewardItem(
    var name: String,
    var count: Int,
)


