package mk.templateApp.commonData.base

suspend fun <T> lazyLoad(element: ElementInMemoryLocalStore<T>, force: Boolean = false, request: suspend () -> T): T {
    if (!element.hasElement() || force) {
        element.cacheElement(request())
    }
    return element.getElement()!!
}
