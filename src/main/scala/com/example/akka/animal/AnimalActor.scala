package com.example.akka.animal

import akka.actor.{Actor, ActorLogging}

object AnimalActor {
  case object AnimalRequest

  sealed abstract class Animal(val animalType: String) {
    val name: String
  }

  case class Dog(name: String) extends Animal("Dog")

  case class Cat(name: String, age: Int) extends Animal("Cat")
}

class AnimalActor extends Actor with ActorLogging {
  import AnimalActor._

  def receive: Receive = {
    case AnimalRequest => sender ! Cat("Felix", 5)
  }
}