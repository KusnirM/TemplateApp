package mk.templateApp.commonData.dto

interface TransformToDomainModel<out DomainModel> {
    fun transform(): DomainModel
}
