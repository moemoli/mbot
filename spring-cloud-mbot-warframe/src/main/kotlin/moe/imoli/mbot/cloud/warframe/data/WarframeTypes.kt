package moe.imoli.mbot.cloud.warframe.data

class WarframeTypes {


    enum class Faction(val key: String, val index: Int) {
        FC_CORPUS("Corpus", 1),
        FC_GRINEER("Grineer", 0),
        FC_INFESTATION("Infested", 2),
        FC_OROKIN("Orokin", 3),
        FC_RED_VEIL("red_veil", 4),
        FC_SENTIENT("Sentient", 5),
        FC_NARMER("narmer", 6),
        FC_MITW("murmur", 7),
        FC_TENNO("Tenno", 8);

        fun getName(): String {
            return key
        }
    }

    enum class Mission(val key: String) {
        MT_ASSASSINATION("assassination"),
        MT_EXTERMINATION("exterminate"),
        MT_SURVIVAL("survival"),
        MT_RESCUE("rescue"),
        MT_SABOTAGE("sabotage"),
        MT_CAPTURE("capture"),
        MT_COUNTER_INTEL("deception"),
        MT_INTEL("spy"),
        MT_DEFENSE("defense"),
        MT_MOBILE_DEFENSE("mobile_defense"),
        MT_PVP("pvp"),
        MT_MASTERY("mastery"),
        MT_RECOVERY("recovery"),
        MT_TERRITORY("interception"),
        MT_RETRIEVAL("hijack"),
        MT_HIVE("hive"),
        MT_SALVAGE("salvage"),
        MT_EXCAVATE("excavation"),
        MT_RAID("raid"),
        MT_PURGE("purge"),
        MT_GENERIC("generic"),
        MT_PURIFY("purify"),
        MT_ARENA("arena"),
        MT_JUNCTION("junction"),
        MT_PURSUIT("pursuit"),
        MT_RACE("race"),
        MT_ASSAULT("assault"),
        MT_EVACUATION("defection"),
        MT_LANDSCAPE("free_roam"),
        MT_ARTIFACT("disruption"),
        MT_RESOURCE_THEFT("resourceTheft"),
        MT_ENDLESS_EXTERMINATION("sanctuary_onslaught"),
        MT_ENDLESS_DUVIRI("circuit"),
        MT_RAILJACK("skirmish"),
        MT_CORRUPTION("corruption"),
        MT_VOID_CASCADE("voidCascade"),
        MT_ARMAGEDDON("armageddon"),
        MT_VAULTS("netracell"),
        MT_ALCHEMY("alchemy"),
        MT_ASCENSION("ascension"),
        MT_ANY("unknown");


        fun getName(): String {
            return key
        }
    }
}

