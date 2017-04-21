
import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import scala.collection.mutable.MutableList

import scala.language.implicitConversions
import scala.language.dynamics



class ZombieDSL extends App {
  
   var entities = new mutable.HashMap[String, EntityType]
   var callStack = new mutable.MutableList[EntityType]
   var currentSummon = false
   var currentTask = false
   var currentEntity : EntityType = Zombie
  
  
  implicit class EntityName(s : String) {
    
    def is (e : EntityType) {
      if(currentSummon) {
        throw new RuntimeException("");
      }
      entities.put(s, e)
      currentEntity = e
      currentSummon = true
    }
  }
   
   def summon {
     if(!currentSummon) {
       throw new RuntimeException("");
     }
     currentTask = true
   }
   
   def task {
     if(!currentSummon) {
       throw new RuntimeException("");
     }
     currentTask = true
   }
   
   def animate {
     if(!currentTask) {
       throw new RuntimeException("");
     }
     currentEntity match {
       case Zombie =>
       case _ => throw new RuntimeException("");
       
     }
   
     
   }
    
   def bind {
     
   }
   
   def disturb {
     
   }

   
   

  "tom" is Zombie
  summon

  
}