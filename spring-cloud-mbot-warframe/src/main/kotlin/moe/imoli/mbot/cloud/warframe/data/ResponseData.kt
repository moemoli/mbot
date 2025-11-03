package moe.imoli.mbot.cloud.warframe.data

data class ResponseData(
    val code: Int,
    val data: Any,
) {
    companion object {
        fun list(list: List<Any>): ResponseData {

            return ResponseData(0, list)
        }

        fun successOrEmpty(data: Any?): ResponseData {
            return ResponseData(0, data ?: hashMapOf<String, Any>())
        }

        fun success(data: Any): ResponseData {
            return ResponseData(0, data)
        }
    }
}
