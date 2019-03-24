package rsa

import java.util.Random

class BigNumber(value: Array[Byte]) extends Comparable[BigNumber] {

  def modInverse(phi: BigNumber): BigNumber = ???

  def +(number: BigNumber): BigNumber = ???

  def +(number: Int): BigNumber = ???

  def gcd(e: BigNumber): BigNumber = ???

  def -(anotherInt: BigNumber): BigNumber = ???

  def -(anotherInt: Int): BigNumber = ???

  def *(q: BigNumber): BigNumber = ???

  def modPow(e: BigNumber, n: BigNumber): BigNumber = ???

  def toByteArray: Array[Byte] = ???

  override def compareTo(o: BigNumber): Int = ???

  def compareTo(o: Int): Int = ???
}

object BigNumber {

  def probablePrime(length: Int, r: Random): BigNumber = ???
}