package mk.templateApp.commonDomain.base

interface TransformToDomain<DomainModel> {

    fun transform(): DomainModel
}
