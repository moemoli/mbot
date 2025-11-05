package moe.imoli.mbot.cloud.warframe.data.world

data class FissureData(
    var start: Long,
    var expire: Long,
    var seed: Long,
    var region: Int,
    var location: String,
    var level: String,
    var type: String,
    var hard: Boolean,
)



