package com.example.akka.echoenumeratum

import enumeratum.{Enum, EnumEntry}

import scala.collection.immutable

sealed abstract class SizeEnum extends EnumEntry

object SizeEnum extends Enum[SizeEnum] {
  override def values: immutable.IndexedSeq[SizeEnum] = findValues

  case object TALL extends SizeEnum
  case object GRANDE extends SizeEnum
  case object VENTI extends SizeEnum
}
