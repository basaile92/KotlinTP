package compte

import utilisateur.Utilisateur

data class Compte(private var utilisateur: Utilisateur, private var solde: Float = 0f){

    fun debit(montant: Float){

        this.solde -= montant

    }

    fun credit(montant: Float){

        this.solde += montant
    }

}

