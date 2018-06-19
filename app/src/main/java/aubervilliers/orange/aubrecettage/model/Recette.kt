package aubervilliers.orange.aubrecettage.model
import aubervilliers.orange.aubrecettage.model.Question

data class Recette(
        var recetteType: RecetteType,
        var ticketNumber: String?,
        var ticketWriter: String?,
        var roomName: String?,
        var baieCall: String?,
        var equipNumber: Int?,
        val tabQuestion: List<Question>)
{

}