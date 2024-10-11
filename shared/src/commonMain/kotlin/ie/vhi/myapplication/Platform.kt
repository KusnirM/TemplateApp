package ie.vhi.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform