package transaction

import compte.Compte
import java.util.*
 class TransactionRecurrente(date: Date = Date(), compteADebiter: Compte, compteACrediter: Compte, montant: Float, tempsDeRecurrence : Float) : Transaction(date, compteADebiter, compteACrediter, montant) {

     var tempsDeRecurrence : Float = tempsDeRecurrence

     fun done() {
        this.date.time += this.tempsDeRecurrence.toLong()
    }
}
