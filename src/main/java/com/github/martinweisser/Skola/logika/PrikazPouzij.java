package com.github.martinweisser.Skola.logika;


/**
 *  Třída PrikazPouzij - obsahuje příkaz pro použití předmětu
 *  k určité činnosti.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */
public class PrikazPouzij implements IPrikaz
{
    private static final String NAZEV = "pouzij";
    private HerniPlan hPlan;
    private Hra hra;
    
    
    /*********************
     *  Konstruktor třídy
     ********************/
    public PrikazPouzij(HerniPlan hPlan, Hra hra) {
        this.hPlan = hPlan;
        this.hra = hra;
           }
    
    /**
     * Metoda pro provedení příkazu - použití předmětu.
     * Nejdříve zkontroluje validního zadání příkazu
     * a poté zda je předmět v aktuální lokaci nebo ho má postava u sebe.
     * Následně na základě určitých podmínek provede požadované použití.
     *
     * @param parametry pole parametrů na příkazové řádce.
     * @result výsledek zpracování - text, který se vypíše na konzoli
     */
    
    public String proved(String... parametry) {
        
        if(parametry.length < 1) {
           return "Nevim, co pouzit!";
        }
        
        if(parametry.length > 1) {
           return "Neumim pouzit vic predmetu najednou!";
        }
        
        String nazevPredmetu = parametry[0];
              
        
        Lokace aktLokace = hPlan.getAktualniLokace();
        aktLokace.obsahujePredmet(nazevPredmetu);
        Uloziste ruka = hPlan.getRuka();
        Uloziste ledvinka = hPlan.getLedvinka();
        
                
        Predmet p = aktLokace.najdiPredmet(nazevPredmetu);
        
        if(!aktLokace.obsahujePredmet(nazevPredmetu) && !ledvinka.obsahujePredmet(nazevPredmetu) && !ruka.obsahujePredmet(nazevPredmetu)) 
        {
            return "Predmet " + nazevPredmetu + " neni v mistnosti, v ledvince ani v tve ruce";
        }
        
        if(nazevPredmetu.equals("kyblik") && ruka.obsahujePredmet(nazevPredmetu) && hPlan.kybl() && aktLokace.getNazev().equals("ucebna") && !hPlan.hua())
        {
            hPlan.setHori();
            hPlan.naplneny = false;
            return "Uhasil jsi ohen!";
        }
        
        if(nazevPredmetu.equals("kyblik") && ruka.obsahujePredmet(nazevPredmetu) && hPlan.kybl() && aktLokace.getNazev().equals("ucebna") && hPlan.hua())
        {
            hra.setKonecHry(true);
            return "Nesmis uhasit ohen, pokud neni vypnuty HUE!";
                    }
        
        if(nazevPredmetu.equals("kyblik") && ruka.obsahujePredmet(nazevPredmetu) && aktLokace.getNazev().equals("ucebna"))
        {
            return "Musis kyblik napnit vodou!";
        }
                
        if(p.isJedly() && ledvinka.obsahujePredmet("desetikoruna"))
        {
            hPlan.setPruchod();
            ledvinka.zahodPredmet("desetikoruna");
            aktLokace.vezmiPredmet(nazevPredmetu);
            return "Snedl jsi tycinku. Tva energie je doplnena!";
        }
        
        if(p.isJedly() && !ledvinka.obsahujePredmet("desetikoruna"))
        {
            aktLokace.vlozPredmet(p);
            return "Nemas penize na tycinku!";
        }
                       
        if(p.getNazev().equals("kohoutek") && ruka.obsahujePredmet("kyblik") && aktLokace.getNazev().equals("wc"))
        {
            hPlan.setNaplneny();
            return "Naplnil jsi kyblik vodou!";
        }
                
        if(p.getNazev().equals("HUE") && aktLokace.getNazev().equals("vratnice"))
        {
            hPlan.setHue();
            return "Vypnul jsi hlavni uzaver elektriny!";
        }
                     
        return "Nepodarilo se pouzit " + nazevPredmetu;
        
    }
    
    /**
     * Metoda vrací název příkazu, který můžeme zadat na konzoli
     *
     * @return    nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
    
}
