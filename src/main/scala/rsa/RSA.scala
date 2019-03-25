/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package rsa

import java.math.BigInteger
import java.math.BigInteger._
import java.util.Random
import util.control.Breaks._

class RSA(e: BigInteger, d: BigInteger, n: BigInteger) {

  /**
    * Зашифровать.
    *
    * @param message сообщение
    * @return шифр
    */
  def encrypt(message: Array[Byte]): Array[Byte] = {
    return new BigInteger(message).modPow(e, n).toByteArray
  }

  /**
    * Расшифровать.
    *
    * @param message сообщение
    * @return рисшифрованное сообщение
    */
  def decrypt(message: Array[Byte]): Array[Byte] = {
    return new BigInteger(message).modPow(d, n).toByteArray
  }
}

object RSA {


  /**
    * Сгенерировать открытый и закрытый RSA ключ
    *
    * @param length длинна простых чисел p и q
    * @return кортеэ из чисел e, d, n
    */
  def generateKeys(length: Int): (BigInteger, BigInteger, BigInteger) = {
    val r = new Random()
    val p = millerProbablePrime(length)
    val q = millerProbablePrime(length)
    val n = p.multiply(q)
    val phi = p.subtract(ONE).multiply(q.subtract(ONE))
    val e = millerProbablePrime(length / 2)
    val d = e.modInverse(phi)
    (e, d, n)
  }

  private def millerProbablePrime(len: Int): BigInteger = {
    var result: BigInteger = null
    do {
      result = new BigInteger(len, new Random())
    } while (!isPrime(result))
    result
  }

  private def isPrime(bigInteger: BigInteger): Boolean = {
    val log: Int = Math.log(bigInteger.doubleValue()).toInt
    millerRabinTest(bigInteger, log)
  }

  private def millerRabinTest(number: BigInteger, rounds: Int): Boolean = {
    // если в степени 1 по модулю 2 == 0 зачит четное
    if (number.modPow(ONE, TWO).compareTo(ZERO) == 0) {
      return false
    }

    val (t, s) = step2PowSumnT(number)

    //цикл А по раундам
    for (i <- 0 until rounds) {
      var flagtoCycleA = false
      val a = randomBigInt(number.subtract(ONE).bitLength())
      var x = a.modPow(t, number)
      if (x.compareTo(ONE) == 0 || x.compareTo(number.subtract(ONE)) == 0) {
        // continue
      } else {
        breakable {
          for (k <- 0 until s.subtract(ONE).intValue()) {
            x = x.modPow(TWO, number)
            if (x.compareTo(ONE) == 0) {
              return true
            }
            if (x.compareTo(number.subtract(ONE)) == 0) {
              flagtoCycleA = true
              break
            }
          }
        }
        if (flagtoCycleA) {
          // go continue
        } else {
          return false
        }
      }
    }
    true
  }

  private def randomBigInt(bitLen: Int): BigInteger = {
    new BigInteger(bitLen, new Random())
  }

  private def step2PowSumnT(number: BigInteger): (BigInteger, BigInteger) = {
    var numbMinusOne = number.subtract(ONE)
    var Some2Pow = 0
    do {
      if (numbMinusOne.modPow(ONE, TWO).compareTo(ZERO) == 0) {
        Some2Pow += 1
        numbMinusOne = numbMinusOne.divide(TWO)
      }
      else {
        return (numbMinusOne, valueOf(Some2Pow))
      }

    } while (true)
    (ONE, TWO)
  }
}