package org.cba.loan;

public class ListOfPairs {
    protected String l;
    protected String r;
    public ListOfPairs(){
        }
    public ListOfPairs(String l, String r){
        this.l = l;
        this.r = r;
    }
    public String getL(){ return l; }
    public String getR(){ return r; }
    public void setL(String l){ this.l = l; }
    public void setR(String r){ this.r = r; }
}