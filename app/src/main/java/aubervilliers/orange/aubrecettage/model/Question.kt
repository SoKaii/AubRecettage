package aubervilliers.orange.aubrecettage.model

data class Question(
        var buttonYes: Boolean,
        var commentary: String?
){
   fun Afficheur() {
       println(buttonYes);

    }
}
