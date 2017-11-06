package transaction

import compte.Compte
import java.util.*
class TransactionUnique(date: Date = Date(), compteADebiter: Compte, compteACrediter: Compte, montant: Float) : Transaction(date, compteADebiter, compteACrediter, montant) {


}
