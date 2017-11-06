package banque

import compte.Compte
import transaction.Transaction
import transaction.TransactionUnique
import java.util.*

 class Banque(val transactions: ArrayList<TransactionUnique>){



     fun main(args: Array<String>) {

     }

    fun addTransaction(transaction: TransactionUnique){

        this.transactions.add(transaction);
    }

     fun updateTransactions(){

         for(transaction in transactions)
             if(transaction.date.time < Date().time) {

                 transfert(transaction.compteADebiter, transaction.compteACrediter, transaction.montant);
                 this.transactions.remove(transaction);
             }
     }

     fun transfert(compteDebite: Compte, compteCredite: Compte, montant: Float){

         compteDebite.debit(montant)
         compteCredite.credit(montant)


     }
}
