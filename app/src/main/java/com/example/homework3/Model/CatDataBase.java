package com.example.homework3.Model;

import java.util.HashMap;
import java.util.List;

public class CatDataBase {
   public static HashMap<String,Cats>cat=new HashMap<>();

   public static Cats getCatId(String catID){
       return cat.get(catID);
   }
   public static List<Cats>getAllCats(){
       return (List) cat.values();
   }
   public static void saveCatstoDB(List<Cats> catstoSave){
       for(int i = 0; i < catstoSave.size(); i++) {
           Cats meow = catstoSave.get(i);
           cat.put(meow.getId(),meow);

   }


}
}
