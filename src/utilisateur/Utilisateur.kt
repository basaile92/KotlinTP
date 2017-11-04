package utilisateur

import compte.Compte

data class Utilisateur(var prenom: String, var nom: String, var age: Int, var comptes: ArrayList<Compte>){

    fun linkCompte(compte: Compte){

        this.comptes.add(compte)
    }

    fun unLinkCompte(compte: Compte){

        this.comptes.remove(compte)
    }



}
