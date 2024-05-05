import js.objects.jso

class KotlinClassInJsMain {
    fun console(text: String) {
        console.log(text)
    }

    fun reverseList(list: List<Int>): List<Int> {
        return list.reversed()
    }

    fun demoCallingANodeLib() {
        val zipCode = "87701"
        val config: AxiosConfigSettings = jso {
            url = "https://ziptasticapi.com/$zipCode"
            timeout = 3000
        }

        var zipResult: ZipResult
        var errorMessage: String

        axios<ZipData>(config).then { response ->
            zipResult = ZipResult(response.data.country, response.data.state, response.data.city)
            errorMessage = ""
            console.log("ZipResult for $zipCode was : $zipResult")
        }.catch { error ->
            zipResult = ZipResult("", "", "")
            errorMessage = error.message ?: ""
            console.log(errorMessage)
        }
    }
}