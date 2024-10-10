package mk.templateApp.commonTest

suspend fun <Actual> test(
    given: () -> Unit = {},
    whenAction: suspend () -> Actual,
    then: (Actual) -> Unit
) {
    given()
    val actual = whenAction()
    then(actual)
}
