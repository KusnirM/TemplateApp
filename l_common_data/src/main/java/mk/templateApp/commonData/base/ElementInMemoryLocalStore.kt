package mk.templateApp.commonData.base

class ElementInMemoryLocalStore<T> {
    var store: T? = null

    fun getElement(): T? = store

    fun cacheElement(t: T) {
        store = t
    }

    fun hasElement(): Boolean = store != null

    fun clear() {
        store = null
    }
}
