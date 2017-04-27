
import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import scala.collection.mutable.MutableList

import scala.language.implicitConversions
import scala.language.dynamics



class ZombieDSL extends App {
  
   var entities = new mutable.HashMap[String, EntityType]
   var tasks = new mutable.HashMap[String, MutableList[MutableList[Object]]]
   var callStack = new mutable.MutableList[EntityType]
   var currentSummon = false
   var currentTask = false
   var currentTaskStatement : taskStatement = new rememberTask("", new MutableList[Object])
   var currentTaskName : String = ""
   var currentRemember = false
   var statementStack : MutableList[Object] = new MutableList[Object]
   var currentEntity : EntityType = Zombie
  
  
  implicit class EntityName(s : String) {
    
    def is (e : EntityType) {
      if(currentSummon) {
        throw new RuntimeException("");
      }
      entities.put(s, e)
      currentEntity = e
      currentEntity.name = s
      currentSummon = true
    }
  }
   
   def summon {
     if(!currentSummon) {
       throw new RuntimeException("");
     }
     currentTask = true
   }
   
   object task {
     if(!currentSummon) {
       throw new RuntimeException("");
     }
     currentTask = true
     def apply (taskName : String) = {
       currentTaskName = taskName
     }
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
   
   
   object remember {
     
     def start(entityName : String) = {
       currentRemember = true
       //is remember the only thing that uses the task stack??
       statementStack = new MutableList[Object]
       currentTaskStatement = new rememberTask(entityName, statementStack)
       TaskGetter 
     }
     
     def start = {
       statementStack = new MutableList[Object]
       currentTaskStatement = new rememberTask(currentEntity.name, statementStack)
       TaskGetter
     }
      
     def apply(num : Integer) = {
       currentEntity.memInt = num
       statementStack = new MutableList[Object]
       currentTaskStatement = new rememberTask(currentEntity.name, statementStack)
       TaskGetter
     }
     
   }
   
   trait taskStatement
   
   class rememberTask (entityName : String, stack : MutableList[Object]) extends taskStatement {
     
     def this(num : Integer, stack : MutableList[Object]) {
        this(currentEntity.name, stack) 
     }
     
     def apply {
       
     }
     
   }
   
   object moan  {
     
     
     def apply(num : Integer) = {
       statementStack = new MutableList[Object]
       statementStack.+=(num)
       TaskGetter
     }
     
     def start(entityName : String) = {
       statementStack = new MutableList[Object]
       statementStack.+=(entities(entityName).memInt)
       TaskGetter
     }
     
   }
   
   class moanTask() extends taskStatement {
     
   }
   
   object TaskGetter {
     
       def apply = {
         //finish statement stack
         if(currentRemember) {
           //sum ints in the statement stack
           val res = 0
           //val x = statementStack.foreach(res += match
         }
         
         currentRemember = false
       }
     
       def moan(entityName : String) = {
         statementStack.+=(entities(entityName).memInt)
         TaskGetter
       }
       
       def moan(num : Integer) = {
         
         TaskGetter
       }
     
       /*
        * can this be supported??
       def remember(entityName : String) = {
       
         TaskGetter
       }
     		*/
     
       def say(something : String) = {
       
         TaskGetter
       }
     }
   
   object say {
     def apply(something : String) = {
       
     }
   }
 

  

  "tom" is Zombie
  summon
  task ("SayHello")
  moan (5) moan 7
  moan
  moan start "tom" moan 5 moan "tom"
  say ("sorry")
  remember start "tom" moan 5
  say ("Hello World")
  animate
  animate

  
}