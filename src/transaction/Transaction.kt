package transaction

import compte.Compte
import java.util.*

open abstract class Transaction(var date: Date, var compteADebiter: Compte, var compteACrediter: Compte, var montant: Float){

}
