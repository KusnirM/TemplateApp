package mk.templateApp.home.data.exceptions

import mk.templateApp.commonDomain.exceptions.BaseException

class GetChatHistoryException(
    cause: Throwable,
    message: String = "Unable to load chat history",
) : BaseException(message, "2", cause)
