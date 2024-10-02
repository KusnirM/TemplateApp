package mk.templateApp.commonDomain.connectivity

enum class ConnectionType {
    CONNECTED,
    NOT_CONNECTED
}

interface NetworkObserver {
    val connectType: ConnectionType
}
